# CCDV-F Domain 4: Evaluation, Testing & Debugging — Complete Study Guide
## ~1-2 Questions | 2.6% of Exam (VERY LIGHT)

---

## **OVERVIEW: Four Tasks in Debugging & Evals**

| Task | Focus | Key Concept | Exam Weight |
|------|-------|------------|------------|
| 4.1 | Error Classification | HTTP codes and is_error flag | High |
| 4.2 | Trace Analysis | stop_reason and tool_use_id linking | High |
| 4.3 | Origin Isolation | Console replay test | High |
| 4.4 | Recovery & Evals | Retry/degrade/escalate + SMART criteria | Medium |

---

---

# **TASK 4.1: ERROR CLASSIFICATION**

## **The Status Code Taxonomy**

Every Claude API error returns a structured response with type, message, and request_id.

### **The Core Classification Rule**

```
4xx (except 429) = YOUR FAULT — Fix the request, never retry
429 / 5xx = TRANSIENT — Retry with exponential backoff
```

---

## **The Error Hierarchy**

| Code | Name | Meaning | Action |
|------|------|---------|--------|
| **400** | invalid_request | Malformed request, bad JSON, missing field | Fix request — NEVER retry |
| **401** | authentication_error | Invalid API key or expired OAuth | Fix credentials — NEVER retry |
| **403** | permission_error | Key lacks access to resource | Fix permissions — NEVER retry |
| **404** | not_found_error | Resource doesn't exist | Fix URL/reference — NEVER retry |
| **429** | rate_limit_error | Too many requests or tokens/min | **Backoff & retry** (only 4xx) |
| **500** | api_error | Internal server error | Retry with backoff |
| **504** | gateway_timeout | API timeout | Retry with backoff |
| **529** | overloaded | API temporarily overloaded | Retry with backoff; consider smaller model |

---

## **Mental Model: The Traffic System**

Think of errors like traffic:

- **400** = Wrong turn (going around the block won't help; you need a new route)
- **429** = Red light (wait and it will turn green)
- **500/529** = Temporary road closure (try again shortly)

---

## **SDK Auto-Retry Behavior**

The official SDKs handle this automatically:
- ✅ Retry transient failures (connection errors, 429, 5xx) with exponential backoff
- ✅ Default 2 retries before giving up
- ✅ Honor `retry-after` headers from the API
- ❌ Never retry 4xx errors (except 429)

---

## **is_error Flag: Tool Failure Classification**

### **What It Is**

When a tool fails, you return a `tool_result` with `is_error: true`. This tells Claude the tool encountered an error, not that the tool executed and returned data.

### **The Critical Distinction**

Tool error = **external API/DB failure**  
Model reasoning error = **Claude reasoned incorrectly**  
Integration error = **your code assembled message wrong**

### **The #1 Classification Trap**

❌ **WRONG**: Return error string in content without is_error flag
```json
{
  "type": "tool_result",
  "tool_use_id": "toolu_01abc",
  "content": "Error: DB connection timeout"
}
```

Claude treats this as normal output and may confidently report: "The database says there was a connection timeout" (treating error as data).

✅ **CORRECT**: Always flag tool failures explicitly
```json
{
  "type": "tool_result",
  "tool_use_id": "toolu_01abc",
  "content": "Error: DB connection timeout",
  "is_error": true
}
```

Claude uses the `is_error` flag structurally to decide how to handle the failure (retry, escalate, adjust approach).

---

## **The Case Study: The Swallowed Timeout**

**What happened:**
- Tool caught database timeout exception
- Returned `{ "items": [] }` without `is_error: true`
- Claude interpreted empty array as "zero results found" (valid response)
- Claude confidently told customer: "Your order has no items" (FALSE)

**What should have happened:**
- Return `is_error: true` with `errorCategory: "transient"`
- Claude retries the query
- On success: returns actual results
- On repeated failure: escalates/degrades

---

## **Task 4.1 Key Takeaways**

✅ 4xx (except 429) = fix request, never retry  
✅ 429 = only retryable 4xx, use exponential backoff  
✅ 5xx = retry with backoff  
✅ Always set `is_error: true` on tool failures  
✅ SDKs auto-retry twice with backoff  
✅ Honor `retry-after` headers  

---

---

# **TASK 4.2: TRACE ANALYSIS**

## **Reading Multi-Turn Agent Traces**

A **trace** is a chronological log of every request/response in an agent loop.

When reading a trace, **stop_reason tells you why each turn ended**. It's the first thing to check when a loop misbehaves.

---

## **The Five stop_reason Values**

| Value | Meaning | What to Check |
|-------|---------|--------------|
| **end_turn** | Claude finished naturally | Output quality (was the answer correct?) |
| **tool_use** | Claude wants to call a tool | Your code must send tool_result next |
| **max_tokens** | Response was cut off | Increase max_tokens or reduce input |
| **refusal** | Claude declined | Review content against usage policy |
| **pause_turn** | Server-tool loop paused | Send more context to resume |

---

## **Reading a Trace Like a Script**

A trace is like reading a conversation transcript with stage directions.

```
Turn 1 (User): "What's the status of order ORD-2024-7731?"
  ↓
Turn 2 (Assistant): [calls get_order tool]
  stop_reason: tool_use
  [Claude expects tool_result next]
  ↓
Turn 3 (User): [sends tool_result with order data]
  ↓
Turn 4 (Assistant): "Your order shipped on 2024-01-15"
  stop_reason: end_turn
  [Claude finished naturally]
```

---

## **tool_use_id Linking**

Every `tool_use` block has an `id`. Every `tool_result` must reference it via `tool_use_id`.

### **The Chain**

```json
// Turn 2: Claude responds with stop_reason: "tool_use"
{
  "role": "assistant",
  "content": [{
    "type": "tool_use",
    "id": "toolu_01XYZ",
    "name": "get_order",
    "input": { "order_id": "ORD-2024-7731" }
  }],
  "stop_reason": "tool_use"
}

// Turn 3: You MUST send both messages with matching IDs
[
  ...previous_messages...,
  {
    "role": "assistant",
    "content": [{
      "type": "tool_use",
      "id": "toolu_01XYZ",    // Must match Turn 2
      "name": "get_order",
      "input": { "order_id": "ORD-2024-7731" }
    }]
  },
  {
    "role": "user",
    "content": [{
      "type": "tool_result",
      "tool_use_id": "toolu_01XYZ",  // Must match!
      "content": "{ \"status\": \"shipped\" }"
    }]
  }
]
```

---

## **The Vanishing Result Bug (Most-Tested Scenario)**

### **What Happens**

Agent calls `get_order`. Tool returns valid data. Claude's next response completely ignores the result and makes up an answer.

### **Root Cause**

Developer's code sent the tool_result as a new user message but **forgot to include the previous assistant message (with the tool_use block)** in the message array.

Without the assistant turn, the API can't link the result to the request.

### **The Fix**

```
WRONG message array:
├─ user: "Get order"
├─ user: tool_result (WRONG — no assistant in between!)

CORRECT message array:
├─ user: "Get order"
├─ assistant: tool_use block
├─ user: tool_result (NOW it links!)
```

**Rule**: Roles must alternate: user → assistant → user → assistant

Skipping the assistant message breaks the chain.

---

## **Common Trace Debugging Scenarios**

### **Scenario 1: Runaway Loop (50+ turns)**

**What**: Agent runs 50+ turns without `end_turn`

**Cause**: Usually `tool_choice: "any"` is forced, telling Claude it MUST call a tool every turn.

**Fix**: Use `tool_choice: "auto"` (default) so Claude can choose when to stop.

---

### **Scenario 2: Truncated Output**

**What**: Response cuts off mid-sentence

**Cause**: `max_tokens` is too low

**Fix**: Increase `max_tokens` or reduce input length

---

### **Scenario 3: Wrong Tool Selected**

**What**: Claude called Tool B instead of Tool A

**Cause**: Tool definitions are ambiguous or overlapping

**Fix**: Review tool descriptions; improve clarity and uniqueness

---

## **Trace Analysis Technique**

Read top-to-bottom: Find the first turn where output diverges from expected. That's your root cause — not the turn where the user sees the wrong answer. (Errors propagate across turns.)

---

## **Task 4.2 Key Takeaways**

✅ stop_reason is the first thing to check  
✅ Roles must alternate (user → assistant → user)  
✅ tool_use_id linking must be exact  
✅ Vanishing result = missing assistant message  
✅ Runaway loop = forced tool_choice  
✅ Trace top-to-bottom = find first divergence  

---

---

# **TASK 4.3: ORIGIN ISOLATION**

## **The Console Replay Test**

The **Console replay test** is THE key technique for origin isolation.

**How it works:**
1. Copy the exact request from your trace into Anthropic Console
2. Run it in isolation
3. Compare result with your production output

---

## **The Isolation Verdict**

| Console Result | Origin | Fix Category |
|---|---|---|
| **Same bug reproduced** | Model / Prompt | Improve tool descriptions, add examples, tighten criteria |
| **Bug does NOT reproduce** | Integration Code | Fix message assembly, role alternation, ID linking, parsing |

---

## **Why This Matters**

**Most "model bugs" are actually integration bugs.**

When a developer says "Claude is giving wrong answers," the first step is always: Run the Console replay.

- If Console reproduces the bug → model/prompt issue
- If Console produces correct answer → your code is mangling something

The Console eliminates your code from the equation.

---

## **Syntax vs Semantic Errors**

### **Syntax Error**

```
Malformed JSON, missing closing brace
Origin: Integration or schema not enforced
Fix: Enable JSON mode or tool_use
```

### **Semantic Error**

```
Valid JSON but price is $150 instead of $1,500
Origin: Model reasoning
Fix: Improve prompt, add validation
```

---

## **🔑 The Crucial Distinction**

**Structured outputs guarantee FORMAT only, never meaning.**

```
✅ What JSON mode guarantees:
   - Output is valid JSON
   - All required fields present
   - Correct data types

❌ What JSON mode does NOT guarantee:
   - Values are correct
   - Numbers are accurate
   - Meaning reflects reality
```

A perfectly valid JSON object can contain completely wrong values.

**Schema ≠ Accuracy**

---

## **Origin Isolation Decision Tree**

```
Console reproduces same bug?
├─ YES → Model/Prompt origin
│   └─ Fix: Improve prompt, examples, criteria
│
└─ NO → Integration Code origin
    └─ Fix: Message assembly, role alternation, ID linking
```

---

## **The Wrong Price Case Study**

**Symptom**: Agent returns `"price": 150` but should be `1500`

**Step 1: Console Replay**
- Copy exact request to Console
- Run it
- Console also returns `150` (not `1500`)

**Verdict**: Model origin (not integration)

**Root Cause**: Prompt doesn't specify how to calculate/extract price

**Fixes**:
1. Add explicit instructions in prompt about price calculation
2. Add field-level validation in code that checks price against known bounds
3. Improve tool description to be more specific about expected format

---

## **The Wrong Tool Selected Case Study**

**Symptom**: Agent calls Tool B instead of Tool A for customer lookup

**Step 1: Check Tool Definitions**
- Tool A name: "lookup_customer" → vague
- Tool B name: "get_customer_by_id" → specific

**Problem**: Overlapping or ambiguous descriptions

**Fix**: Run tool evaluation recipe from Claude Cookbooks
- Tests model against each tool independently
- Provides feedback on whether names, parameters, descriptions are clear
- Improves definitions (not the model)

---

## **Task 4.3 Key Takeaways**

✅ Console replay = eliminate your code  
✅ Same bug in Console → model/prompt origin  
✅ Different result in Console → integration origin  
✅ Structured outputs = syntax only, not semantics  
✅ Always check error messages (they name exact field)  
✅ Tool evaluation recipe tests definitions  

---

---

# **TASK 4.4: RECOVERY & EVALS**

## **Matching Recovery Strategies to Error Types**

The exam tests whether you can match the correct recovery strategy to each error type.

### **The Core Rule**

```
Transient errors → Retry
Persistent errors → Degrade or Escalate
Never retry a permanent failure
```

---

## **Recovery Decision Tree**

```
Is the error transient?
├─ YES (429, timeout, 5xx) → Retry with exponential backoff
│
└─ NO (persistent):
    Is it a partial failure?
    ├─ YES → Graceful degradation (return partial results)
    │
    └─ NO (complete failure):
        Is it a capability gap?
        └─ YES → Escalate to human with structured context
```

---

## **The Four Recovery Strategies**

| Strategy | When to Use | Example |
|----------|-------------|---------|
| **Retry with backoff** | Transient: 429, 500, 504, 529, timeout | SDK auto-retries twice; honor retry-after |
| **Graceful degradation** | Partial failure: one tool works, another doesn't | Return search results, report "DB unavailable" |
| **Escalate to human** | Persistent: capability gap, policy ambiguity | Structured handoff with context + partial results |
| **Model fallback** | API overloaded (529) under sustained load | Switch Opus → Sonnet → Haiku to relieve spike |

---

## **Graceful Degradation (The Overlooked Middle Ground)**

This is often the best strategy.

### **The Scenario**

Search works fine. Database is down.

### ❌ **Worst Choices**

- Fail the entire request (user gets nothing)
- Ignore the DB failure silently (user gets incomplete results without knowing)

### ✅ **Best Choice: Graceful Degradation**

Return search results AND explicitly report:
"Order lookup is currently unavailable. Showing search results only."

Customer gets partial value and knows exactly what's missing.

---

## **Evaluation Methodology: SMART Criteria**

### **Three Core Principles**

| Principle | What It Means | Example |
|-----------|-------------|---------|
| **SMART criteria** | Success criteria must be specific and measurable | "F1 ≥ 0.85 on 10,000 tweets" — not "classify well" |
| **Automate grading** | Prefer cheap, fast automated grading | Code-based > LLM-as-judge > human |
| **Volume over quality** | More automated tests beats fewer hand-graded ones | 1,000 auto-graded tests > 50 expert-graded |

---

## **The Grading Hierarchy**

Choose the cheapest method that works (because you re-run evals constantly):

1. **Code-based grading** (fastest, cheapest)
   - Exact match, string match, regex
   - Example: `output == "positive" ? 1.0 : 0.0`

2. **LLM-as-judge** (moderate cost)
   - Claude grades Claude's output
   - Example: "Did this classification make sense?"

3. **Human grading** (gold standard, expensive)
   - Expert review
   - Use for validating other methods

---

## **The Four Parts of an Eval**

```json
{
  "input": "Classify: 'This product is amazing!'",
  "output": "positive",        // Model's response
  "expected": "positive",      // Golden answer
  "score": 1.0                 // Grading result (0 or 1)
}
```

---

## **Eight Quality Dimensions to Test**

Every eval should measure:

1. **Task fidelity** — Did it solve the task?
2. **Consistency** — Does it give same answer to equivalent inputs?
3. **Relevance** — Is the output relevant to the input?
4. **Tone** — Does it match required tone?
5. **Privacy** — Does it avoid exposing sensitive info?
6. **Context use** — Did it reference relevant context?
7. **Latency** — Did it respond in time?
8. **Price** — Did it stay within budget?

---

## **🔑 Regression Testing: The Full Suite Rule**

### **The Scenario**

Developer changes prompt to fix 3 failing test cases.

Result:
- 3 previously-failing cases now pass ✅
- 2 previously-passing cases now break ❌
- Average score ticks up slightly

### **What Should Happen**

**REJECT the change.**

A prompt change that fixes 3 but breaks 2 is a net negative. Those 2 regressions represent real users who will now get wrong answers.

### **The Rule**

Every change must run the **full test suite** including regression cases, not just targeted fixes.

A change that improves targets but breaks previously-passing cases is **always wrong**, even if average score goes up.

---

## **Best Practices for Evals**

1. **Version your prompts** — Keep history of changes
2. **Re-run full suite on every version** — Every change triggers full test
3. **Compare side-by-side in Console** — See exactly what changed
4. **Reject any regressions** — Even if average improves
5. **Use code-based grading first** — Cheapest and most reliable

---

## **The Silent Regression Case Study**

**What happened:**
- Developer adds "always respond in JSON format" to system prompt
- Fixes 5 test cases where output wasn't valid JSON
- Tests only those 5 cases → all pass
- Deploys to production
- 24 hours later: 15% of customer queries return malformed responses

**Root Cause:**
- Only tested 5 targeted cases, not full suite
- Prompt change had side effects on cases that previously worked
- JSON instruction conflicted with free-text response patterns

**Fix:**
- Roll back change
- Re-run full test suite
- Use Console side-by-side comparison
- Consider using `tool_use` for JSON requirement instead of global instruction

---

## **Task 4.4 Key Takeaways**

✅ Retry transient errors (429, 5xx)  
✅ Escalate persistent errors to humans  
✅ Graceful degradation = partial results + transparency  
✅ SMART criteria = specific and measurable  
✅ Code-based grading > LLM-as-judge > human  
✅ Full test suite on every change  
✅ Reject regressions even if average improves  
✅ Version prompts, compare side-by-side  

---

---

# **DOMAIN 4 FINAL EXAM CHECKLIST** ✅

## **Task 4.1: Error Classification**
- [ ] 4xx (except 429) = fix request, never retry
- [ ] 429 = only retryable 4xx
- [ ] 5xx = retry with backoff
- [ ] Always set `is_error: true` on tool failures
- [ ] SDKs auto-retry twice

## **Task 4.2: Trace Analysis**
- [ ] stop_reason is first thing to check
- [ ] Roles must alternate (user/assistant/user)
- [ ] tool_use_id linking must be exact
- [ ] Vanishing result = missing assistant message
- [ ] Runaway loop = forced tool_choice

## **Task 4.3: Origin Isolation**
- [ ] Console replay = eliminate your code
- [ ] Same bug in Console → model/prompt origin
- [ ] Different result → integration origin
- [ ] Structured outputs = syntax only
- [ ] Always check error messages

## **Task 4.4: Recovery & Evals**
- [ ] Transient → retry, Persistent → degrade/escalate
- [ ] Graceful degradation = partial + transparent
- [ ] SMART criteria = specific and measurable
- [ ] Code-based > LLM-as-judge > human grading
- [ ] Full suite on every change
- [ ] Reject regressions

---

## **Rapid-Fire Exam Questions You'll See**

1. **"Got a 400 error, should I retry?"**  
   → No. 400 = your fault. Fix the request.

2. **"429 vs 500 — which should I retry?"**  
   → Both. But 429 needs backoff + honor retry-after.

3. **"Tool returned error string but Claude ignored it"**  
   → Missing `is_error: true` flag.

4. **"Claude ignores tool result"**  
   → Message array malformed. Missing assistant message before tool_result.

5. **"Why does Console work but production fails?"**  
   → Integration bug. Your code mangling something.

6. **"Console also fails. What now?"**  
   → Model/prompt origin. Improve prompt or tool definitions.

7. **"How to catch regressions?"**  
   → Full test suite on every change. Compare side-by-side.

8. **"Database is down. What to return?"**  
   → Graceful degradation. Return partial results + explicitly report DB unavailable.

---

## **Key Phrases to Recognize**

🟡 **Classification cues**: "429", "retry", "backoff", "is_error"

🟡 **Trace cues**: "stop_reason", "tool_use_id", "vanishing", "runaway"

🟡 **Origin cues**: "Console", "integration", "model bug", "prompt issue"

🟡 **Recovery cues**: "retry", "degrade", "escalate", "graceful", "regression"

---

## **30-SECOND RECAP**

✅ 4xx (not 429) = fix, 429/5xx = retry  
✅ Always flag tool errors with is_error: true  
✅ Roles alternate, tool_use_id link exactly  
✅ Console replay = find bug origin  
✅ Syntax ≠ semantics, validate meaning yourself  
✅ Full test suite on every change  
✅ Reject regressions even if average improves  

---

## **Study Time Allocation**

| Task | Time | Priority |
|------|------|----------|
| Task 4.1 (Classification) | 30% | 🔴 High |
| Task 4.2 (Trace Analysis) | 30% | 🔴 High |
| Task 4.3 (Origin Isolation) | 25% | 🔴 High |
| Task 4.4 (Recovery & Evals) | 15% | 🟡 Medium |

---