# CCDV-F Domain 4: Evaluation, Testing & Debugging — Revision Sheet
## Quick Reference | 2.6% Exam | ~1-2 Questions (VERY LIGHT)

---

## **THE GOLDEN RULES**

1. ✅ **4xx (not 429) = fix request. 429/5xx = retry with backoff.**
2. ✅ **Always set `is_error: true` on tool failures.**
3. ✅ **Roles alternate: user → assistant → user.**
4. ✅ **Console replay = isolate integration vs model bugs.**
5. ✅ **Run full test suite on every change.**
6. ✅ **Reject regressions even if average improves.**

---

## **TASK 4.1: ERROR CLASSIFICATION**

### The Core Rule
```
4xx (except 429) → Fix request, NEVER retry
429 → Backoff & retry (only 4xx)
5xx → Retry with backoff
```

### Status Code Meanings

| Code | Meaning | Action |
|------|---------|--------|
| 400 | Bad request | Fix it |
| 401 | Auth error | Fix credentials |
| 403 | Permission error | Fix permissions |
| **429** | **Rate limited** | **Retry with backoff** |
| 5xx | Server error | Retry |

### Mental Model
- **400** = Wrong turn (route won't help)
- **429** = Red light (wait for green)
- **500** = Road closure (try again soon)

---

## **is_error Flag**

❌ **WRONG**: Return error string without flag
```
content: "Error: DB timeout"
```
Claude treats it as data.

✅ **CORRECT**: Always flag tool errors
```
content: "Error: DB timeout"
is_error: true
```
Claude knows to retry/escalate.

---

## **Task 4.1 Stress Points**

🚩 Never retry 4xx (except 429)  
🚩 Always flag tool errors with is_error: true  
🚩 SDKs auto-retry transient failures twice  

---

---

## **TASK 4.2: TRACE ANALYSIS**

### stop_reason (Check First!)

| Value | Means | Check |
|-------|-------|-------|
| `end_turn` | Finished | Output correct? |
| `tool_use` | Calling tool | Send tool_result next |
| `max_tokens` | Cut off | Increase max_tokens |
| `refusal` | Declined | Check usage policy |

---

### Roles Must Alternate

```
user message
    ↓
assistant message (with tool_use)
    ↓
user message (with tool_result)
    ↓
assistant response
```

---

### tool_use_id Linking

Every tool_use has an `id`. Every tool_result must reference it via `tool_use_id`.

```
tool_use: id="toolu_01XYZ"
tool_result: tool_use_id="toolu_01XYZ"  ← Must match!
```

---

## **The Vanishing Result Bug**

**Problem**: Claude ignores tool result

**Cause**: Missing assistant message with tool_use block

**Fix**: Include assistant message before tool_result

---

## **Common Scenarios**

| Problem | Cause | Fix |
|---------|-------|-----|
| Runaway loop (50+ turns) | Forced tool_choice | Use auto, not any |
| Truncated output | Low max_tokens | Increase it |
| Wrong tool selected | Ambiguous definitions | Improve descriptions |

---

## **Task 4.2 Stress Points**

🚩 stop_reason first, not content type  
🚩 Vanishing result = missing assistant message  
🚩 tool_use_id must match exactly  

---

---

## **TASK 4.3: ORIGIN ISOLATION**

### The Console Replay Test

1. Copy exact request from trace to Console
2. Run it in isolation
3. Compare result

---

### The Isolation Verdict

| Console Result | Origin | Fix |
|---|---|---|
| Same bug reproduced | Model/Prompt | Improve prompt, examples |
| Bug does NOT reproduce | Integration Code | Fix message assembly, IDs |

---

### Key Insight
**Most "model bugs" are integration bugs.**

Console eliminates your code. If Console works, bug is in your integration.

---

## **Syntax vs Semantics**

| Error | Example | Origin | Fix |
|-------|---------|--------|-----|
| **Syntax** | Malformed JSON | Integration | Enable JSON mode |
| **Semantic** | Valid JSON, wrong price | Model | Improve prompt |

### 🔑 Critical Truth
**Structured outputs = syntax only, NOT semantics.**

You must validate meaning yourself.

---

## **Task 4.3 Stress Points**

🚩 Console = eliminate your code  
🚩 Same bug → model origin  
🚩 Different result → integration origin  
🚩 Syntax ≠ semantics  

---

---

## **TASK 4.4: RECOVERY & EVALS**

### Recovery Decision Tree

```
Transient error?
├─ YES → Retry with backoff
└─ NO:
    Partial failure?
    ├─ YES → Graceful degradation
    └─ NO → Escalate to human
```

---

## **The Four Strategies**

| Strategy | When | Example |
|----------|------|---------|
| **Retry** | Transient (429, 5xx) | Auto-retry with backoff |
| **Degrade** | Partial failure | Return search, report DB down |
| **Escalate** | Persistent | Handoff to human |
| **Fallback** | Overloaded | Opus → Sonnet → Haiku |

---

## **Graceful Degradation**

**Don't fail entirely. Don't hide the failure.**

Return partial results + explicitly report what's unavailable.

Example:
- Search works, DB is down
- Return: search results + "Order lookup unavailable"

---

## **Evaluation: SMART Criteria**

| Principle | Example |
|-----------|---------|
| Specific | "F1 ≥ 0.85 on 10,000 tweets" |
| Measurable | Not "classify well" |
| Automated | Code-based grading |
| Volume | 1,000 auto-tests > 50 expert |

---

## **Grading Hierarchy** (Cheapest to Expensive)

1. **Code-based**: Exact match, regex (fast)
2. **LLM-as-judge**: Claude grades Claude (moderate)
3. **Human grading**: Expert review (gold standard)

---

## **🔑 Regression Testing: Full Suite Rule**

**Problem**: Prompt change fixes 3, breaks 2, average goes up

**Solution**: REJECT it

Every change must:
- Run full test suite (not just targeted cases)
- Include regression cases
- Reject any breakage (even if average improves)

---

## **Best Practices**

1. Version prompts
2. Re-run full suite on every version
3. Compare side-by-side in Console
4. Reject regressions
5. Use code-based grading first

---

## **Task 4.4 Stress Points**

🚩 Retry transient, escalate persistent  
🚩 Full suite on every change  
🚩 Reject regressions (even if average up)  
🚩 Code-based > LLM grading > human  

---

---

## **EXAM RED FLAGS**

🚩 **"429 is a client error like 400"**  
→ ❌ No. 429 = only retryable 4xx

🚩 **"Return error string, Claude will understand"**  
→ ❌ Use is_error: true flag

🚩 **"Console works but production fails"**  
→ ❌ Integration bug, not model

🚩 **"Prompt fix improves average score"**  
→ ❌ Check for regressions before deploying

🚩 **"Schema guarantees correct values"**  
→ ❌ Only syntax, validate semantics

---

---

## **QUICK DECISION TREES**

### Error Classification?
```
4xx except 429? → Fix request
429? → Retry with backoff
5xx? → Retry with backoff
```

### Console reproduces bug?
```
YES → Model/Prompt origin
NO → Integration origin
```

### Prompt change safe to deploy?
```
All tests pass? → Check for regressions
Any broken? → REJECT (even if avg up)
```

### Tool failure?
```
Always return: is_error: true
```

---

---

## **30-SECOND RECAP**

✅ 4xx (not 429) = fix, never retry  
✅ 429 = retry with backoff  
✅ Flag tool errors with is_error: true  
✅ Roles alternate, tool_use_id match  
✅ Console replay = find bug origin  
✅ Full suite on every change  
✅ Reject regressions  

---

---

## **STUDY TIME ALLOCATION**

| Task | Time | Priority |
|------|------|----------|
| Task 4.1 (Classification) | 30% | 🔴 High |
| Task 4.2 (Trace) | 30% | 🔴 High |
| Task 4.3 (Origin) | 25% | 🔴 High |
| Task 4.4 (Recovery) | 15% | 🟡 Medium |

---

---

## **QUICK LOOKUP**

### When to retry?
- **429** → YES (with backoff)
- **400/401/403/404** → NO (fix request)
- **5xx** → YES (with backoff)

### When to use tool_use_id?
- **Always** (must match tool_use id)

### When is_error: true?
- **Always on tool failures** (never return error string alone)

### Console same bug?
- **YES** → model/prompt origin
- **NO** → integration origin

### Run full test suite?
- **On every change** (including regressions)

---