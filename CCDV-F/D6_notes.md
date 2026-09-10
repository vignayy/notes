# CCDV-F Domain 6: Prompt & Context Engineering — Complete Study Guide
## ~7 Questions | 11.0% of Exam

---

## **OVERVIEW: Four Interconnected Tasks**

| Task | Focus | Key Principle | Exam Weight |
|------|-------|---------------|------------|
| 6.1 | Core Principles | Vague → Explicit | High |
| 6.2 | XML, Examples, CoT | Structure & Reasoning | High |
| 6.3 | Context Management | Lost-in-the-Middle & Compaction | High |
| 6.4 | Consistency & Drift | Three-Part Stack | Medium |

---

---

# **TASK 6.1: CORE PROMPT PRINCIPLES**

## **The Golden Rule: Be Clear & Direct**

### **The #1 Exam Trap: Vague → Explicit**

❌ **Vague Prompt** (fails):
```
"Summarize this document."
```

✓ **Explicit Prompt** (succeeds):
```
"Summarize in exactly 3 bullets. Each bullet ≤20 words. 
Focus on financial impact. Write for a CFO audience. No jargon."
```

**Why it matters**: Claude is a brilliant new employee with no context on your norms. Specify everything: format, length, style, audience.

---

## **The Colleague Test**

**Debugging rule**: Show your prompt to a colleague with minimal context and ask them to follow it. If they'd be confused, Claude will be too.

Without explicit instructions:
- Format varies
- Length is unpredictable
- Style is inconsistent
- Quality drops

---

## **Core Principles**

| Principle | What It Means | Example |
|-----------|---------------|---------|
| **Be specific** | Define format, length, style | "3 bullets, ≤20 words each, no jargon" |
| **Role prompting** | Focus vocabulary and reasoning | "You are a senior security engineer" |
| **System prompt** | Persistent role & constraints | Resent every API call (stateless) |
| **Success criteria** | Testable, measurable outcome | "F1 ≥ 0.85 on 1000 test cases" |
| **Format spec** | JSON/XML/template structure | "Respond only in JSON" |

---

## **The Five Consistency Levers**

When output keeps varying, try these (in order):

| Lever | What It Does | When to Use |
|-------|-------------|------------|
| **1. Exact format spec** | JSON/XML/template | Always for structured output |
| **2. Few-shot examples** | Demonstrate exact format | When format keeps varying |
| **3. System prompt role** | Define personality/background | Always — your baseline |
| **4. Retrieval grounding** | Anchor in retrieved passages | Factual accuracy tasks |
| **5. Prompt chaining** | Split complex into subtasks | Multi-step workflows |

---

## **System Prompt Reality**

### ⚠️ **Not "Persistent" in LMS Sense**

The API is **stateless**. The system prompt is **resent with every request**.

```
Turn 1: POST with system + messages
    ↓
Turn 2: POST with system + ALL messages (including turn 1) + messages
    ↓
Turn 3: POST with system + ALL messages (including turns 1-2) + messages
```

Your code must include the system prompt every time. It's not stored server-side.

---

## **The Vague → Explicit Rewrite (Case Study)**

### **Scenario**

Developer prompts: "Summarize this document."  
Claude returns a 500-word essay with inconsistent formatting.  
Developer complains: "The model is bad at summarizing."

### **Root Cause**

Prompt is vague. Claude interpreted "summarize" as "write an essay."

### **Fix**

```
"Summarize this document in exactly 3 bullet points.
Each bullet must be ≤20 words.
Focus on financial impact.
Write for a CFO audience.
No jargon.
Respond only with the 3 bullets. No introduction, no closing, no commentary."
```

---

## **Before You Prompt-Engineer**

Ask yourself:

1. Do I have clear success criteria?
2. Do I have a way to test empirically?
3. Do I have a first-draft prompt to improve?

**Not every failing eval is a prompt problem:**
- Latency/cost issues → model choice
- Format not guaranteed → Structured Outputs
- Accuracy low → better prompting + validation

---

## **Why Prompt Engineering Works**

✅ Faster than training  
✅ Cheaper than labeling data  
✅ Preserves general knowledge  
✅ Works across model updates  
✅ Needs no labeled data  

---

## **Task 6.1 Key Takeaways**

✅ Vague → Explicit (specify format, length, audience, style)  
✅ Use colleague test (would they be confused?)  
✅ System prompt resent every call (stateless API)  
✅ Five levers: format spec, examples, role, grounding, chaining  
✅ Not every problem is a prompt problem  

---

---

# **TASK 6.2: XML TAGS & FEW-SHOT EXAMPLES**

## **XML Tags: Claude's Native Language**

### **Why XML**

- **Clarity**: Claude knows what's what
- **Accuracy**: Less conflation of instruction and data
- **Flexibility**: Nest for hierarchy
- **Parseability**: Tags in output make post-processing easy
- **Injection defense**: Separates structure from data

### **No Canonical Names**

You define the tag names. Consistency matters more than specific names.

```xml
<instructions>
Rewrite for a leadership audience.
</instructions>

<context>
Weekly project update for executives.
</context>

<output_format>
3 bullets: status, risk, next step.
</output_format>

<example>
Input: "Sprint delayed by 2 days due to API changes"
Output: "• Status: Sprint delayed 2d (API dependency)"
</example>
```

---

## **Four Benefits of XML**

1. **Clarity**: Instructions separate from data
2. **Accuracy**: Less chance Claude conflates sections
3. **Flexibility**: Easy to nest for hierarchy
4. **Parseability**: Extract tagged sections from output

---

## **Few-Shot Examples**

### **More Effective Than Prose**

Prose descriptions of format:
```
"Return a JSON object with fields for status, risk, and next_step.
The status field should be a string describing current state.
The risk field should be a string describing any risks.
The next_step field should be a string describing what comes next."
```

Few-shot examples:
```xml
<example>
Input: "Sprint delayed by 2 days due to API changes"
Output: {
  "status": "Sprint delayed 2d (API dependency)",
  "risk": "May impact Q1 release date",
  "next_step": "Escalate to infrastructure team"
}
</example>

<example>
Input: "All tests passing, on schedule"
Output: {
  "status": "On track, all tests green",
  "risk": "None identified",
  "next_step": "Deploy to staging Thursday"
}
</example>
```

**Why examples win**: They demonstrate exact format, length, tone, and content better than any description.

---

## **Chain of Thought (CoT)**

### **Three Levels**

| Level | Technique | When to Use |
|-------|-----------|------------|
| **Basic** | "Think step-by-step" | Simple reasoning tasks |
| **Guided** | Outline specific reasoning steps | Multi-factor decisions |
| **Structured (best)** | Reason in `<thinking>`, answer in `<answer>` | Complex tasks — easy to strip reasoning |

### **Example: Structured CoT**

```
<instructions>
Analyze this investment opportunity.
Think step-by-step in <thinking> tags.
Provide your final assessment in <answer> tags.
</instructions>

<document>
[Investment details]
</document>

User: Should we invest?

Claude response:
<thinking>
1. Market growth potential?
   - SaaS market growing 15% YoY
   - Company growing 30% YoY (2× market)
   
2. Team quality?
   - CEO has 10 years industry experience
   - CTO built 2 unicorns
   
3. Valuation fair?
   - $500M raise at $2B valuation
   - 10× revenue multiple (fair for growth)
   
4. Risk factors?
   - New market entrants
   - Technology disruption risk
   - Customer concentration
   
Recommendation: Investment thesis strong, risks manageable.
</thinking>

<answer>
YES, recommend investment. Strong team, market tailwinds, 
fair valuation. Risk: monitor competitor landscape quarterly.
</answer>
```

Your code:
1. Outputs the `<thinking>` section for transparency
2. Extracts only `<answer>` for the final decision
3. Has easy separation of reasoning from conclusion

---

## **Two Critical CoT Rules**

### **Rule 1: Output the Thinking**

❌ **Without outputting thinking**: No thinking occurs at all

✓ **With outputting thinking**: Claude reasons through the problem

Thinking must be visible in the output for it to happen.

### **Rule 2: Use CoT Judiciously**

CoT adds:
- Latency (longer response)
- Tokens (more output costs 5×)
- Can hurt simple lookups

Reserve CoT for tasks a human would need to think through.

---

## **Structured Outputs: Syntax ≠ Semantics**

### **The Trap**

A developer enables Structured Outputs with a JSON schema.

Result:
- Output is always valid JSON ✅
- All required fields present ✅
- Correct data types ✅
- But "price" field returns $150 instead of $1,500 ❌

### **Root Cause**

**Structured Outputs guarantee SYNTAX only, not SEMANTICS.**

```
✅ What Structured Outputs guarantee:
   - Valid JSON format
   - All required fields
   - Correct data types
   - Valid enum values

❌ What Structured Outputs do NOT guarantee:
   - Values are correct
   - Numbers are accurate
   - Meaning reflects reality
```

A perfectly valid JSON object can contain completely wrong values.

### **Fix**

1. Add field-level validation in your code (range checks, cross-references)
2. Add clear instructions in the prompt about how to extract/calculate the value
3. Use CoT to improve reasoning accuracy

---

## **The Prefill Caveat**

### ⚠️ **Prefill Not Supported on New Models**

Prefill (starting the assistant message with `{`) is **NOT supported** on:
- Opus 4.6+
- Sonnet 4.6+
- Fable 5

**With extended thinking**, prefill is also unavailable.

### **Also Not Allowed**

Content cannot end with trailing whitespace (400 error).

### **Alternative**

Use **Structured Outputs** instead for guaranteed JSON on new models.

---

## **Task 6.2 Key Takeaways**

✅ XML tags clarify structure and defend against injection  
✅ Few-shot examples > prose descriptions  
✅ CoT: structured (thinking + answer tags) > guided > basic  
✅ Output thinking explicitly for reasoning to occur  
✅ Structured Outputs = syntax only, validate semantics separately  
✅ Prefill deprecated on new models, use Structured Outputs instead  

---

---

# **TASK 6.3: CONTEXT MANAGEMENT**

## **Context as a Budget**

Context is a **finite resource**. Context engineering is **curating what goes in each turn**, not stuffing everything in.

---

## **Three Strategies**

| Strategy | What It Does | When to Use |
|----------|-------------|------------|
| **Progressive summarization** | Compress older turns while keeping recent detail | Long sessions (20+ turns) |
| **Compaction** | Summarize conversation into compact block | Midpoint of session |
| **Scratchpad/memory tools** | Offload state to files or KV stores | External long-term memory |

---

## **Placement Rules**

### **Long Data Near Top**

Place bulk documents **above** instructions/queries.

```
NOT this:
[instructions] → [massive document] → [query]
(Query gets weakest attention)

DO this:
[instructions] → [massive document] → [query]
(Query at end, strongest attention)
```

### **XML Wrapping**

Multiple documents in one prompt:

```xml
<document>
  <source>Q3 Financial Report</source>
  <content>[financial data]</content>
</document>

<document>
  <source>Board Minutes</source>
  <content>[board content]</content>
</document>

<query>
What is the biggest risk mentioned?
</query>
```

### **Quote-Then-Answer**

For long-context Q&A:

```
1. Ask Claude to quote relevant passages first
2. Then ask for the answer

Example:
"Quote the 3 most relevant passages from this document.
Then answer: What is the main financial risk?"
```

This forces Claude to ground its answer in the document.

---

## **Lost-in-the-Middle Effect**

### **The Problem**

Claude attends **most** to the start and end of a long context.

A critical instruction buried at token 50,000 of 100,000 can be **missed**.

```
100K token window:
├─ Tokens 1-10K: STRONG attention
├─ Tokens 10K-90K: WEAK attention (lost in middle)
└─ Tokens 90K-100K: STRONG attention
```

### **The Fix**

Move critical instructions:
1. **To the system prompt** (always at the start)
2. **To the end** of the user message (repeat if needed)

Don't bury key requirements in the middle of a massive document.

---

## **Defensive Parsing: stop_reason**

### **Never Assume Index 0 Is Text**

❌ **Crashes**:
```python
output = response.content[0].text  # Crashes if first block is tool_use
```

✅ **Defensive**:
```python
for block in response.content:
    if block.type == "text":
        output = block.text
    elif block.type == "tool_use":
        execute_tool(block)
```

### **Why It Matters**

A response can contain **both text and tool_use blocks**.

Index 0 might be:
- Text block (normal)
- tool_use block (function call)
- Error (crash)

Always iterate and check type.

---

## **The Degraded Session (Case Study)**

### **Scenario**

After 30 turns, an agent's responses degrade:
- Forgets earlier instructions
- Gives contradictory answers
- Occasionally hallucinates

### **Wrong Diagnosis**

Raise `max_tokens` — doesn't help.

### **Root Cause**

Context pollution. 30 turns of raw history fill the window:
- Stale tool results
- Outdated states
- Lost-in-the-middle weakens attention on middle content

### **Fix: Compaction**

```
Old approach:
[System] → [Turn 1-30 raw] → [Current query]

New approach:
[System] → [Summary of turns 1-25] → [Turns 26-30 in full] → [Current query]

Drop stale tool results that are no longer relevant.
Place compacted summary near the top.
```

---

## **Task 6.3 Key Takeaways**

✅ Context is a budget, curate carefully  
✅ Long data near top, query near end  
✅ Lost-in-the-middle: move critical instructions to start/end  
✅ Compaction: summarize old turns, keep recent full  
✅ Drop stale tool results  
✅ Defensive parsing: iterate blocks, check type  
✅ Use stop_reason, not content type  

---

---

# **TASK 6.4: CONSISTENCY & DRIFT**

## **The Consistency Stack**

Over long sessions, system-prompt influence **weakens** — this is **drift**.

The exam tests a **three-part consistency stack**:

### **Layer 1: Format Lock**

- tool_use / Structured Outputs
- Locks output schema at API level
- No prompt can override this

### **Layer 2: Model Pinning**

- Pin to specific model version string
- Locks behavior across API updates
- Prevents surprise changes

### **Layer 3: Eval Suite in CI**

- Full test suite on every change
- Catches regressions before users see them
- Rejects changes that break previously-passing cases

---

## **The Drifting Agent (Case Study)**

### **Scenario**

System prompt says: "Respond in JSON only."

But:
- Turn 1-5: JSON ✅
- Turn 6-10: Mostly JSON, some markdown
- Turn 11-15: Mostly markdown
- Turn 16-20: Plain text ❌

System prompt hasn't changed. What happened?

### **Root Cause**

System prompt drift. In a long session, the system prompt's influence weakens as more conversation history accumulates between it and the current turn.

### **Three-Part Fix**

```
Layer 1: Locked format
└─ Use Structured Outputs or tool_use to force JSON at API level
   No prompt injection can override this

Layer 2: Re-inject mid-session
└─ Every 10 turns, repeat key constraints in a user message:
   "Remember: you must respond ONLY in JSON format."

Layer 3: Compact old turns
└─ Reduce the "distance" between system prompt and current turn
   Summarize turns 1-10, keep turns 11-20 in full
```

---

## **Extended Thinking & Consistency**

### **New Models: Adaptive + Extended Thinking**

On newest models (Opus 4.7+, Fable 5, Sonnet 5), native thinking replaces prefill tricks.

| Feature | How It Works | Consistency Benefit |
|---------|------------|-------------------|
| **Adaptive thinking** | Always on — model thinks internally | Consistent reasoning without manual prompts |
| **Extended thinking** | Manual — set budget_tokens | Deeper reasoning for complex tasks |
| **Previous-turn thinking** | Ignored — doesn't consume context | No context pollution |

### **The Principle**

Where prefill isn't available, **structured outputs + thinking** give you the format and reasoning guarantees prefill used to provide.

Don't fight the model — use its native capabilities.

---

## **Model Pinning**

### **Why Pin?**

New model versions may change:
- Output style
- Length
- Formatting
- Behavior

### **How to Pin**

```python
# Don't do this (unpinned):
model="claude-opus-4-8"  # Always latest

# Do this (pinned):
model="claude-opus-4-8-20250514"  # Specific version
```

### **Process**

1. Pin to specific version in production
2. Run full eval suite on new version
3. Compare side-by-side in Console
4. Only upgrade if all tests pass + no regressions

---

## **Eval Suite: The Regression Defense**

### **The Scenario**

Prompt change fixes 3 failing test cases.

Result:
- 3 previously-failing cases now pass ✅
- 2 previously-passing cases now break ❌
- Average score ticks up slightly

### **What Should Happen**

**REJECT the change.**

A prompt change that fixes 3 but breaks 2 is a net negative. Those 2 regressions represent real users who will get wrong answers.

### **The Rule**

Every change must run the **full test suite** including regression cases, not just targeted fixes.

A change that improves targets but breaks previously-passing cases is **always wrong**, even if average score goes up.

### **Best Practices**

1. Version your prompts
2. Re-run full suite on every version
3. Compare side-by-side in Console
4. Reject any regressions
5. Use code-based grading first (cheapest)

---

## **The Consistency Stack in Production**

### **Full Stack Example**

```
1. Format Lock (Layer 1)
   └─ Use tool_use to enforce JSON schema
   
2. Model Pinning (Layer 2)
   └─ Pin to claude-opus-4-8-20250514
   
3. Eval Suite (Layer 3)
   └─ 1000 test cases in CI
   └─ Runs on every prompt/model change
   └─ Blocks deployment if regressions detected
```

**Result**: Format is locked, behavior is locked, changes are caught before users see them.

---

## **Task 6.4 Key Takeaways**

✅ Three-part consistency stack: format lock + model pin + eval suite  
✅ Format lock (tool_use/Structured Outputs) = API level  
✅ Model pinning = specific version strings  
✅ Eval suite catches regressions before production  
✅ Re-inject constraints mid-session (every 10 turns)  
✅ Compact old turns to keep system prompt "closer"  
✅ Reject changes that break regressions (even if average improves)  
✅ Extended thinking + Structured Outputs replace prefill tricks  

---

---

# **DOMAIN 6 FINAL EXAM CHECKLIST** ✅

## **Task 6.1: Core Principles**
- [ ] Vague → Explicit (specify format, length, audience)
- [ ] Colleague test (would they be confused?)
- [ ] System prompt resent every call (stateless)
- [ ] Five levers: format spec, examples, role, grounding, chaining
- [ ] Success criteria before prompt engineering

## **Task 6.2: XML & CoT**
- [ ] XML tags clarify structure
- [ ] Few-shot examples > prose
- [ ] CoT: structured (thinking/answer) > guided > basic
- [ ] Output thinking for reasoning to occur
- [ ] Structured Outputs = syntax only
- [ ] Prefill deprecated on new models

## **Task 6.3: Context Management**
- [ ] Context as budget, curate carefully
- [ ] Long data near top, query near end
- [ ] Lost-in-the-middle: critical instructions to start/end
- [ ] Compaction: summarize old, keep recent full
- [ ] Defensive parsing: iterate blocks, check type
- [ ] Never assume index 0 is text

## **Task 6.4: Consistency**
- [ ] Three-part stack: format lock + pin + eval
- [ ] tool_use/Structured Outputs = format lock
- [ ] Model pinning = specific version
- [ ] Eval suite catches regressions
- [ ] Re-inject constraints mid-session
- [ ] Reject changes that break regressions
- [ ] Extended thinking replaces prefill

---

## **Rapid-Fire Exam Questions**

1. **"Output keeps varying. What do I try first?"**  
   → Exact format spec or few-shot examples

2. **"Vague prompt vs explicit prompt — which works?"**  
   → Explicit (specify format, length, audience, style)

3. **"Colleague test: what's it for?"**  
   → Debug if they'd be confused, Claude will be too

4. **"Should I put large document in the middle?"**  
   → No. Place near top or after instructions

5. **"Lost-in-the-middle: where to put critical instructions?"**  
   → System prompt (start) or end of user message

6. **"Structured Outputs = values are validated?"**  
   → No. Syntax only. Add validation logic yourself

7. **"Prefill works on new models?"**  
   → No. Deprecated. Use Structured Outputs instead

8. **"Agent drifts off format after 20 turns?"**  
   → Re-inject constraints, use tool_use lock, compact old turns

9. **"Prompt change fixes 3 tests but breaks 2?"**  
   → Reject. Run full suite. 2 regressions = net negative

10. **"How to lock behavior across model updates?"**  
    → Pin to specific version, run full eval suite before upgrade

---

## **30-SECOND RECAP**

✅ Vague → explicit (specify format, length, audience)  
✅ XML tags + few-shot examples  
✅ CoT structured (thinking/answer tags)  
✅ Context: budget, curate, place data carefully  
✅ Lost-in-the-middle: critical instructions to start/end  
✅ Consistency stack: format lock + model pin + eval  
✅ Reject changes that cause regressions  

---

## **Study Time Allocation**

| Task | Time | Priority |
|------|------|----------|
| Task 6.1 (Core) | 25% | 🔴 High |
| Task 6.2 (XML/CoT) | 30% | 🔴 High |
| Task 6.3 (Context) | 30% | 🔴 High |
| Task 6.4 (Drift) | 15% | 🟡 Medium |

---