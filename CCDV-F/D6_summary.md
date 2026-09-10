# CCDV-F Domain 6: Prompt & Context Engineering — Revision Sheet
## Quick Reference | 11.0% Exam | ~7 Questions

---

## **THE GOLDEN RULES**

1. ✅ **Vague → Explicit (specify format, length, audience, style).**
2. ✅ **System prompt resent every call (stateless API).**
3. ✅ **Few-shot examples > prose descriptions.**
4. ✅ **Context = budget; curate, don't dump everything.**
5. ✅ **Lost-in-the-middle: critical instructions to start/end.**
6. ✅ **Consistency stack: format lock + model pin + eval.**
7. ✅ **Reject changes that break regressions.**

---

## **TASK 6.1: CORE PRINCIPLES**

### The #1 Trap: Vague vs Explicit

❌ **VAGUE**: "Summarize this document."  
✅ **EXPLICIT**: "Summarize in 3 bullets, ≤20 words each, financial focus, CFO audience, no jargon."

---

### Colleague Test

Show prompt to colleague with no context. If confused, Claude will be too.

**What varies without explicit specs**:
- Format
- Length
- Style
- Quality

---

### Five Consistency Levers (Try In Order)

| Lever | When |
|-------|------|
| 1. Exact format spec | Always for structured |
| 2. Few-shot examples | When format varies |
| 3. System prompt role | Always — baseline |
| 4. Retrieval grounding | Factual tasks |
| 5. Prompt chaining | Multi-step workflows |

---

### System Prompt Reality

**NOT persistent** in LMS sense.

API is **stateless** — system prompt resent every call.

Your code must include it every time.

---

### Before Prompt Engineering

1. Clear success criteria?
2. Way to test empirically?
3. First-draft prompt to improve?

**Not every problem is prompt problem:**
- Latency/cost → model choice
- Format not guaranteed → Structured Outputs
- Low accuracy → prompting + validation

---

## **Task 6.1 Stress Points**

🚩 Vague = inconsistent output  
🚩 System prompt resent, not persistent  
🚩 Specify format, length, audience  
🚩 Colleague test = debug clarity  

---

---

## **TASK 6.2: XML & STRUCTURED OUTPUT**

### XML Tags

- Separate instructions from data
- Defend against injection
- No canonical names (be consistent)

Benefits: clarity, accuracy, flexibility, parseability

---

### Few-Shot Examples > Prose

**Prose** (unreliable):
```
Return a JSON object with status, risk, and next_step fields.
Each field should be a string describing...
```

**Examples** (reliable):
```
Example 1:
Input: "Sprint delayed"
Output: {"status": "...", "risk": "...", "next_step": "..."}

Example 2:
Input: "On track"
Output: {"status": "...", "risk": "...", "next_step": "..."}
```

Use 2-5 diverse examples.

---

### Chain of Thought (Three Levels)

| Level | Technique | When |
|-------|-----------|------|
| Basic | "Think step-by-step" | Simple reasoning |
| Guided | Outline steps | Multi-factor |
| **Structured** | `<thinking>` + `<answer>` | Complex (best) |

### Two CoT Rules

1. **Output thinking explicitly** — without it, no thinking occurs
2. **Use judiciously** — adds latency/tokens, hurts simple lookups

---

### Structured Outputs: Syntax ≠ Semantics

✅ **Guarantees**:
- Valid JSON format
- All required fields
- Correct types
- Valid enums

❌ **Does NOT guarantee**:
- Values correct
- Numbers accurate
- Meaning reflects reality

**Fix**: Add validation logic + better prompting

---

### Prefill Caveat

⚠️ **NOT supported on**:
- Opus 4.6+
- Sonnet 4.6+
- Fable 5
- With extended thinking

**Use** Structured Outputs instead.

---

## **Task 6.2 Stress Points**

🚩 Examples > prose descriptions  
🚩 Structured outputs = syntax only, validate semantics  
🚩 CoT outputs thinking for reasoning to occur  
🚩 Prefill deprecated, use Structured Outputs  

---

---

## **TASK 6.3: CONTEXT MANAGEMENT**

### Three Strategies

| Strategy | When |
|----------|------|
| Progressive summarization | Long sessions (20+ turns) |
| Compaction | Midpoint of session |
| Scratchpad/memory tools | External long-term memory |

---

### Placement Rules

**Long data**: Place near TOP (above instructions)

**Query**: Place at END (strongest attention)

```
WEAK: [instructions] → [huge doc] → [query]
STRONG: [huge doc] → [instructions] → [query]
```

---

### Lost-in-the-Middle Effect

Claude attends MOST to start and end.

Middle tokens (10K-90K of 100K) get WEAK attention.

**Fix**: Move critical instructions to system prompt or repeat at end.

---

### Defensive Parsing

❌ **WRONG**: Assume index 0 is text

✅ **CORRECT**: Iterate blocks, check type

Never hardcode index 0.

---

### Degraded Session

**Problem**: After 30 turns, accuracy drops

**Cause**: Context pollution (stale tool results, lost-in-the-middle)

**Fix**: Compact turns 1-25 into summary, keep 26-30 full

---

## **Task 6.3 Stress Points**

🚩 Lost-in-the-middle: critical instructions to start/end  
🚩 Compaction: summarize old, keep recent full  
🚩 Defensive parsing: iterate, don't assume index 0  
🚩 Context = budget, don't dump everything  

---

---

## **TASK 6.4: CONSISTENCY & DRIFT**

### Three-Part Consistency Stack

| Layer | What | How |
|-------|------|-----|
| **1. Format Lock** | Output schema | tool_use / Structured Outputs |
| **2. Model Pinning** | Behavior | Specific version string |
| **3. Eval Suite** | Regressions | Full CI testing |

---

### Drifting Agent

**Problem**: Turn 1-5 JSON, Turn 15 markdown, Turn 25 plain text

**Cause**: System prompt influence weakens in long session

**Fixes**:
1. Use tool_use to lock format at API level
2. Re-inject constraints every 10 turns
3. Compact old turns

---

### Model Pinning

**Don't**: `model="claude-opus-4-8"` (unpinned, latest)

**Do**: `model="claude-opus-4-8-20250514"` (pinned, specific)

Process:
1. Pin to version
2. Run full eval suite
3. Compare before upgrade
4. Only upgrade if no regressions

---

### Extended Thinking & Consistency

New models: Adaptive + Extended Thinking

Replaces prefill tricks for format/reasoning guarantees.

Use **structured outputs + thinking**, not prefill.

---

### Eval Suite: Regression Defense

**Scenario**: Prompt fixes 3, breaks 2, average goes up

**Answer**: REJECT change (2 regressions = bad)

**Rule**: Full suite on every change. Reject any breakage.

**Best practices**:
- Version prompts
- Re-run full suite
- Compare side-by-side
- Reject regressions

---

## **Task 6.4 Stress Points**

🚩 Format lock = tool_use, not prompt  
🚩 Re-inject constraints mid-session  
🚩 Model pinning = specific versions  
🚩 Eval suite catches regressions before production  
🚩 Reject changes that break tests  

---

---

## **EXAM RED FLAGS**

🚩 **"Vague prompt is fine, Claude will understand"**  
→ ❌ Be explicit

🚩 **"System prompt persists like LMS"**  
→ ❌ Resent every call (stateless)

🚩 **"Prose description works as well as examples"**  
→ ❌ Examples > prose

🚩 **"Structured Outputs validates values"**  
→ ❌ Syntax only, validate semantics yourself

🚩 **"Prefill works on all new models"**  
→ ❌ Deprecated, use Structured Outputs

🚩 **"Ignore lost-in-the-middle, documents can go anywhere"**  
→ ❌ Critical instructions to start/end

🚩 **"Prompt fixes 3 tests, breaks 2. Deploy if average up"**  
→ ❌ Reject, regressions = bad

🚩 **"Use largest model to keep consistency"**  
→ ❌ Use consistency stack (lock + pin + eval)

---

---

## **QUICK DECISION TREES**

### Output varying?

```
Exact format?
├─ NO → Add format spec
Showing examples?
├─ NO → Add few-shot examples
Using role?
├─ NO → Add system prompt role
Still varying?
└─ YES → Use tool_use/Structured Outputs
```

### Where to place content?

```
Critical instruction?
├─ YES → System prompt (start) or end of message
Large document?
├─ YES → Place near top
Query?
├─ YES → Place at end (strongest attention)
```

### Agent drifting off format?

```
Layer 1: Use tool_use to lock format
Layer 2: Re-inject constraints every 10 turns
Layer 3: Compact old turns
```

### Prompt change safe?

```
All tests pass?
├─ YES → Check for regressions
Any broken?
├─ YES → REJECT (even if avg up)
└─ NO → Deploy
```

---

---

## **30-SECOND RECAP**

✅ Vague → explicit (specify everything)  
✅ System prompt resent every call  
✅ Few-shot examples > prose  
✅ Context = budget, curate carefully  
✅ Lost-in-the-middle: critical to start/end  
✅ Consistency stack: lock + pin + eval  
✅ Reject regressions always  

---

---

## **STUDY TIME ALLOCATION**

| Task | Time | Priority |
|------|------|----------|
| Task 6.2 (XML/CoT) | 30% | 🔴 High |
| Task 6.3 (Context) | 30% | 🔴 High |
| Task 6.1 (Core) | 25% | 🔴 High |
| Task 6.4 (Drift) | 15% | 🟡 Medium |

---

---

## **QUICK LOOKUP**

### When to use?

| Scenario | Action |
|----------|--------|
| Output varying | Exact format spec + examples |
| Long session degrading | Compact old turns |
| Lost content in middle | Move to start or end |
| Format must lock | tool_use / Structured Outputs |
| New model may change behavior | Model pinning |
| Prompt change risky | Run full eval suite |
| Simple lookup | No CoT (waste) |
| Complex reasoning | Structured CoT |

### Five Levers

1. Exact format spec
2. Few-shot examples
3. System prompt role
4. Retrieval grounding
5. Prompt chaining

### Three Stack Layers

1. Format lock (tool_use)
2. Model pinning (version string)
3. Eval suite (CI testing)

---