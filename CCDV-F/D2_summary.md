# CCDV-F Domain 2: Applications & Integration — Revision Sheet
## Quick Reference | 33.1% Exam | ~17 Questions (HEAVIEST)

---

## **THE GOLDEN RULES**

1. ✅ **Claude is stateless** — send full history every call.
2. ✅ **Roles must alternate** — user → assistant → user.
3. ✅ **Stream for latency. Batch for cost. Cache for repeated.**
4. ✅ **429 is the only retryable 4xx** — all others are YOUR bug.
5. ✅ **Structured outputs guarantee syntax, not semantics.**
6. ✅ **CLAUDE.md guides. settings.json enforces.**

---

## **TASK 2.1: STATELESS MESSAGES API**

### The One Rule
**Claude forgets everything between calls.**  
Every request must include the **full message history**.

### Alternating Roles (Critical)
```
user → assistant → user → assistant → ...
```

❌ **Can't have**: Two users in a row, missing assistant with tool_use  
✅ **Must have**: Full alternating pattern

### Common Failures

| Problem | Cause | Fix |
|---------|-------|-----|
| "Claude forgets" | Only sending latest message | Send full history |
| "Claude ignores tool result" | Missing assistant message with tool_use block | Include assistant message before tool_result |

### Advanced Features

| Feature | What | Exam Note |
|---------|------|-----------|
| **Vision** | Images (base64/URL) | Supported on all recent models |
| **Prefill** | Seed response format | ❌ Deprecated on Opus 4.6+/Sonnet 4.6+ |
| **Adaptive Thinking** | Claude chooses per-turn | May have no thinking block — don't assume |
| **Extended Thinking** | Fixed budget | Use Batch API if >32K budget (timeout risk) |

### Stress Point
**Prefill is deprecated** — if exam mentions steering format on new models, it won't work.

---

## **TASK 2.2: STREAMING, BATCH & CACHING**

### Three Tools, Three Goals

| Tool | Goal | Cost | Latency | Use |
|------|------|------|---------|-----|
| **Streaming** | Reduce perceived latency | Normal | ⬇️ Feel faster | User-facing chatbots |
| **Batch API** | Cut cost in half | ⬇️ 50% off | N/A (async) | Overnight bulk |
| **Caching** | Reuse expensive prefixes | ⬇️ 90% off | Normal | Repeated system prompts |

---

## **STREAMING**

### When to Use
✅ User-facing apps (perceived latency matters)  
❌ NOT for bulk processing (doesn't reduce cost)

### What It Does
Tokens appear as they're generated, feels faster even if total time is same.

---

## **BATCH API**

### The Deal
- 50% cheaper
- Single-turn only (no multi-turn conversations)
- Up to 24 hours processing
- Up to 100,000 requests per batch

### Critical Constraint
❌ **Each request single-turn only**  
Every batch request is independent (no conversation context shared).

### When to Use
✅ Overnight classification (50,000 tickets)  
✅ Weekly reports  
✅ Cost-sensitive async work

---

## **PROMPT CACHING**

### The Deal
- Up to 90% off repeated content
- Break-even point: ~2 requests
- Minimum: 1,024 tokens

### 🔑 THE #1 MISTAKE (EXAM LOVES THIS)

❌ **WRONG**: Put dynamic content BEFORE cache breakpoint
```
system=[
    {"text": f"Time: {now()}", "cache_control": {...}}  
    # Changes every call → cache NEVER HITS
]
```

✅ **CORRECT**: Stable first, dynamic last
```
system=[
    {"text": "You are a support agent", "cache_control": {...}},
    {"text": "1000 examples", "cache_control": {...}}
]
messages=[
    {"role": "user", "content": f"Help with issue. Time: {now()}"}
]
# Stable cached on call 1, reused on calls 2+
```

### When to Use
✅ Repeated long system prompt  
✅ Repeated examples/RAG context  
✅ Same codebase analyzed many times  
❌ One-off requests  
❌ Every call has different context  

---

## **DECISION TREE: Stream vs Batch vs Cache**

```
User-facing? YES → Streaming
Async overnight work? YES → Batch API (+ Caching if repeated prompt)
Repeated long prefix? YES → Caching
Blocking operation? YES → Standard API
```

### Cost Comparison (1000 requests, 1500 input tokens each)

| Approach | Cost | Time |
|----------|------|------|
| Standard | $4.50 | Real-time |
| Streaming | $4.50 | Real-time |
| Batch | $2.25 | 24 hours |
| Caching | $1.35 | Real-time |
| **Batch + Caching** | **$0.68** | 24 hours |

### Stress Point
❌ "Streaming is answer to bulk processing"  
✅ "Use Batch for cost"

---

## **TASK 2.3: STRUCTURED OUTPUTS & ERRORS**

## **STRUCTURED OUTPUTS**

### What It Does
Guarantees response matches JSON schema.

### Two Methods

| Method | Constrains | Use |
|--------|-----------|-----|
| JSON Outputs | Entire response | Data extraction, classification |
| Strict Tool Use | Tool input parameters | Tool calling with schema |

### 🔑 SYNTAX ≠ SEMANTICS

✅ **Syntax (guaranteed)**:
- Valid JSON format
- All required fields present
- Correct data types
- Valid enum values

❌ **Semantics (NOT guaranteed)**:
- Value is correct
- Classification is accurate
- Meaning reflects reality

Example: Schema allows `"category": "question"` but summary says "Product crashes" → semantic error, not schema error.

### Schema Design Tips

| Mistake | Fix |
|---------|-----|
| Required field not always present | Use `["type", "null"]` or don't require |
| Forced miscategorization | Add `"unclear"` to enum |
| Too many required fields | Only require always-present fields |

### Stress Point
**Schema doesn't validate meaning** — you must add semantic validation.

---

## **ERROR HANDLING**

### The Hierarchy

| Code | Category | Action |
|------|----------|--------|
| **400/401/403/404** | Client | Fix request, NEVER retry |
| **429** | Rate limit | **Backoff & retry** |
| **500/502/503/529** | Server | Retry with backoff |

### 🔑 THE 429 EXCEPTION (Exam Favorite)

**429 is the ONLY 4xx that should be retried.**

| Error | Why | Action |
|-------|-----|--------|
| 400 Bad Request | Your JSON is malformed | Fix it, don't retry |
| **429 Rate Limited** | **Request valid, rate limit hit** | **Retry with backoff** |
| 500 Server Error | Their problem | Retry with backoff |

### Exponential Backoff

```
Attempt 1: Fail
Attempt 2: Wait 1s
Attempt 3: Wait 2s
Attempt 4: Wait 4s
Attempt 5: Wait 8s
```

Always honor `retry-after` header if present.

### Stress Point
❌ "429 is a client error like 400"  
✅ "429 is the ONLY retryable 4xx"

---

## **TASK 2.4: CLAUDE.md vs settings.json**

### The Core Distinction

| Aspect | CLAUDE.md | settings.json |
|--------|-----------|---------------|
| **Nature** | Probabilistic | Deterministic |
| **Enforcement** | Model tries to follow | Code hooks enforce |
| **Overridable?** | ✅ Yes (prompt can override) | ❌ No (always blocks) |
| **Use for** | Style, conventions | Permissions, blocks |
| **Example** | "Use TypeScript" | "Block DELETE queries" |

### The Golden Rule
```
"MUST never happen" → settings.json hook
"Prefer this style" → CLAUDE.md
```

---

## **CLAUDE.md — Probabilistic Guidance**

What goes in it:
- Code style preferences
- Architecture patterns
- Team conventions
- Testing guidelines
- Commands

What it does:
✅ Provides guidance  
❌ CAN be overridden by prompt injection

---

## **settings.json — Deterministic Enforcement**

What goes in it:
- Hooks (pre_tool_use, pre_file_access)
- MCP server permissions
- Tool whitelists/blacklists
- Hard security rules

What it does:
✅ CANNOT be overridden  
❌ Enforced by code, not prompt

### Hook Types

| Hook | When Triggered | Example |
|------|---|---|
| `pre_tool_use` | Before any tool call | Block DELETE queries |
| `pre_file_access` | Before file read/write | Block /etc access |
| `pre_command` | Before bash execution | Block external URLs |

---

## **SCOPE HIERARCHY**

**Precedence** (highest to lowest):
```
Managed Policy (IT-deployed)
    ↓
Command-Line Arguments
    ↓
Local (settings.local.json — gitignored)
    ↓
Project (.claude/settings.json — shared via git)
    ↓
User (~/.claude/settings.json — personal)
```

### 🔑 THE SCOPE TRAP (Exam Loves This)

**Problem**: New developer doesn't see team conventions.

**Root cause**: Conventions in user scope (~/.claude/), not project scope (.claude/).

**Fix**: Move to project scope (.claude/CLAUDE.md) so it's shared via git.

---

## **PROMPT INJECTION DEFENSE**

❌ **Weak**: CLAUDE.md says "Never modify production"  
✅ **Strong**: settings.json hook blocks DELETE/DROP/TRUNCATE

**Lesson**: Prompts can be overridden. Code hooks cannot.

---

## **DECISION TREE: CLAUDE.md or settings.json?**

```
MUST be enforced? YES → settings.json hook
Should be followed? YES → CLAUDE.md
Where to store?
├─ Personal only → User scope (~/.claude/)
├─ Team-wide → Project scope (.claude/)
└─ Organization-wide → Managed policy
```

---

## **EXAM RED FLAGS (What They LOVE Testing)**

🚩 **"Stream for overnight bulk processing"** → ❌ Use Batch API  
🚩 **"429 should be treated like 400"** → ❌ 429 is special, retry it  
🚩 **"Dynamic content before cache breakpoint is fine"** → ❌ Cache will never hit  
🚩 **"CLAUDE.md blocks dangerous actions"** → ❌ Use settings.json hooks  
🚩 **"Retry 400 errors with backoff"** → ❌ Never retry 4xx (except 429)  
🚩 **"Schema validates meaning"** → ❌ Only syntax, you validate meaning  
🚩 **"New developer sees CLAUDE.md in user scope"** → ❌ Not shared, move to project  

---

## **STRESS POINTS FOR EXAM**

1. **Stateless API** — full history EVERY call
2. **Streaming ≠ cost reduction** — only perceived latency
3. **Batch = single-turn only** — no multi-turn
4. **Cache breakpoint placement** — stable FIRST, dynamic LAST
5. **429 is retryable** — only 4xx that is
6. **Syntax ≠ Semantics** — schema guarantees structure, not meaning
7. **CLAUDE.md = guidance** — can be overridden
8. **settings.json = enforcement** — cannot be overridden
9. **Scope hierarchy** — managed > CLI > local > project > user
10. **Permissions merge** — lower scopes can't override higher blocks

---

## **30-SECOND RECAP**

✅ Send full history (stateless API)  
✅ Roles alternate (user/assistant/user)  
✅ Stream for latency, Batch for cost, Cache for repeated  
✅ 429 = retry, others = fix request  
✅ Schema = syntax only, validate semantics yourself  
✅ CLAUDE.md guides, settings.json enforces  
✅ Cache: stable content first, dynamic content last  
✅ New devs: conventions in project scope (.claude/), not user  

---

## **STUDY TIME ALLOCATION**

| Task | Time | Priority |
|------|------|----------|
| Task 2.2 (Streaming/Batch/Caching) | 35% | 🔴 Critical |
| Task 2.3 (Errors & Structured Outputs) | 30% | 🔴 Critical |
| Task 2.1 (Stateless API) | 20% | 🟡 High |
| Task 2.4 (Configuration) | 15% | 🟡 Medium |

---

## **QUICK LOOKUP**

### When to use each approach?
- **User chatbot** → Streaming
- **50,000 tickets overnight** → Batch API
- **Same system prompt, 1000 queries** → Caching
- **CI checks (blocking)** → Standard API
- **Need deterministic rule** → settings.json hook
- **Need guidance** → CLAUDE.md
- **Rate limited** → Backoff & retry 429
- **Bad request** → Fix it, never retry

### What's guaranteed?
- ✅ Stateless API behavior
- ✅ Alternating roles
- ✅ 50% discount (Batch)
- ✅ 90% discount (Caching prefix)
- ✅ JSON structure (Structured outputs)
- ✅ settings.json enforcement (hooks)

### What's NOT guaranteed?
- ❌ Claude remembers you (stateless)
- ❌ Prefill works (deprecated)
- ❌ Thinking block exists (adaptive)
- ❌ Semantic correctness (syntax only)
- ❌ CLAUDE.md followed (probabilistic)
- ❌ Cache hits (if dynamic content first)

---