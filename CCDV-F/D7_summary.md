# CCDV-F Domain 7: Security & Safety — Revision Sheet
## Quick Reference | 8.1% Exam | ~5 Questions

---

## **THE GOLDEN RULES**

1. ✅ **Jailbreak = user adversary. Indirect injection = content adversary.**
2. ✅ **MUST = hook (deterministic). SHOULD = prompt (probabilistic).**
3. ✅ **PreToolUse blocks BEFORE execution. PostToolUse audits AFTER.**
4. ✅ **tool_result isolates untrusted content.**
5. ✅ **Secrets in env vars, never in prompts.**
6. ✅ **Scope per task. Deny by default.**
7. ✅ **Human approval for high-stakes actions.**

---

## **TASK 7.1: PROMPT INJECTION**

### Two Threat Models

| Threat | Adversary | Attack | Defense |
|--------|-----------|--------|---------|
| **Jailbreak** | The user | Crafted prompts | Hardened prompt + Haiku screen |
| **Indirect** | Third-party content | Instructions in emails/web | tool_result isolation + screen |

---

### Defense-in-Depth Stack

1. **Harmlessness screen (Haiku)** — Pre-screen every input, fast/cheap
2. **Hardened system prompt** — Emphasize boundaries always
3. **tool_result isolation** — External data goes here only
4. **Output screening** — Catch leaked secrets before user sees
5. **Throttle & ban** — Block repeat jailbreak attempts

---

### tool_result vs User Message

❌ **WRONG**: Put email in user message
- Claude treats as trusted instructions
- Embedded commands likely followed

✅ **CORRECT**: Put email in tool_result
- Claude treats as untrusted data
- Embedded commands likely ignored

---

### Secrets Management

❌ **WRONG**: Database password in system prompt
✅ **CORRECT**: 
- Environment variable (at runtime)
- Secrets manager (AWS, Vault)
- Output screening (catch patterns before leak)

---

## **Task 7.1 Stress Points**

🚩 Jailbreak vs Indirect = different defenses  
🚩 tool_result = untrusted data  
🚩 Secrets = env vars, not prompts  
🚩 Pre-screen with Haiku = cost effective  

---

---

## **TASK 7.2: HOOKS & GUARDRAILS**

### MUST vs SHOULD

| Requirement | Type | What | How |
|---|---|---|---|
| **MUST never** | Hard security | "Can't execute rm -rf" | Hook with exit code 2 |
| **SHOULD** | Best effort | "Avoid verbose output" | Prompt instruction |

**Hooks** = code enforces (deterministic)  
**Prompts** = model tries (probabilistic)

---

### Exit Codes (PreToolUse)

| Code | Meaning | Behavior |
|------|---------|----------|
| **0** | Allow | Proceed |
| **1** | Warn | Non-blocking warning, proceeds |
| **2** | Block | Denied, stderr to Claude |

---

### Lifecycle Events

| Event | When | Use |
|-------|------|-----|
| **PreToolUse** | Before execution | **BLOCK dangerous actions** |
| **PostToolUse** | After execution | Audit, redact, log |
| **UserPromptSubmit** | On user message | Input screening |

---

### 🔑 The Timing Trap

❌ **PostToolUse prevents rm -rf?**  
NO — runs after execution, damage done.

✅ **PreToolUse prevents rm -rf?**  
YES — runs before, exit code 2 blocks action.

---

### Deny Rules

Cannot be overridden, even by `bypassPermissions`.

Security team defines baseline, developers can't lower.

Example:
```
Managed: Deny Read(./.env)
Project: Allow Read tool
Result: Read allowed, EXCEPT .env
```

---

## **Task 7.2 Stress Points**

🚩 Pre = before (can prevent), Post = after (can't)  
🚩 Exit code 2 = block with stderr explanation  
🚩 Deny rules non-overridable  
🚩 MUST = hook, not prompt  

---

---

## **TASK 7.3: SECRETS & TRUST**

### Trust Hierarchy

| Level | Type | Content |
|-------|------|---------|
| 🟢 **Highest** | System prompt | YOUR instructions, rules |
| 🟡 **Medium** | User turns | User queries, app instructions |
| 🔴 **Lowest** | tool_result | External: emails, web, APIs |

---

### The Isolation Rule

✅ **YOUR instructions go in**: System prompt or user turns  
❌ **NEVER in tool_result** — ignored

✅ **External data goes in**: tool_result  
❌ **NEVER in system/user** — treated as trusted instructions

---

### Least Privilege: allowed_tools

```python
allowed_tools=["Read", "Grep", "Glob"]
permission_mode: dontAsk
```

Result:
- Those 3 tools approved
- Everything else denied
- No exception possible

---

### Permission Tiers

| Task | Tools | Notes |
|------|-------|-------|
| Code review | Read, Grep, Glob | Read-only |
| Data analysis | Read, Bash | Process but no edits |
| Deployment | Bash, Edit | Deployment + config |

---

### Least Privilege Benefits

Limits blast radius:
- Read-only task can't execute commands
- Code review task can't modify files
- Analysis task can't deploy

---

### Scan-Secrets Hook

PreToolUse hook that:
- Detects API keys, tokens, private keys (regex)
- Exit code 2 if found
- Stderr tells Claude why blocked

---

## **Task 7.3 Stress Points**

🚩 System prompt > user > tool_result (trust order)  
🚩 tool_result = isolate untrusted content  
🚩 Secrets = env vars only  
🚩 allowed_tools limits blast radius  
🚩 Scan-secrets hook prevents exfiltration  

---

---

## **TASK 7.4: ZERO TRUST**

### Core Principle

```
Trust nothing.
Verify everything.
Assume breach already occurred.
```

---

### Six Principles

| Principle | Meaning |
|-----------|---------|
| Scope per task | Grant only tools this task needs |
| Human-in-the-loop | Approve high-stakes, irreversible actions |
| Assume breach | Limit blast radius with least privilege |
| Audit everything | Log every action |
| Least privilege | Start with nothing, add only needed |
| Verify every action | Don't trust permissions alone |

---

### Managed Policy

Enforces non-overridable baseline:

```
Security team:
├─ Deny rules (non-overridable)
└─ Audit hooks

Developers:
├─ Allow rules on top
└─ Can't override security baseline
```

---

### Permission Hierarchy

| Scope | Controls | Overridable |
|-------|----------|------------|
| Managed policy | Security team | NO |
| Enterprise | IT/org admin | Only within managed |
| Project | Developer | Only within enterprise |
| User | Individual | Most restricted |

---

### Human-in-the-Loop

Required for high-stakes, irreversible actions:
- Delete
- Cancel
- Deploy
- Transfer funds

Flow:
```
Agent: "I want to do X"
    ↓
System: Present to human
    ↓
Human: "Approve" or "Deny"
    ↓
Agent: Proceeds or adjusts
```

---

### Audit Logging

PostToolUse hook logs to JSONL:
- Tool called
- Arguments
- Result
- Timestamp
- Identity

For forensics and accountability.

---

### Three Maturity Tiers

| Tier | Features |
|------|----------|
| **Foundation** | Deny rules, human approval, audit logs |
| **Advanced** | Scoped-per-task perms, screening, provenance |
| **Optimized** | Crypto identity, anomaly detection, continuous |

Start at Foundation, mature as risk drops.

---

## **Task 7.4 Stress Points**

🚩 Scope = per task, not global  
🚩 High-stakes = human approval always  
🚩 Managed policy = non-overridable  
🚩 Audit everything via hooks  
🚩 Defense-in-depth: layers compensate  

---

---

## **EXAM RED FLAGS**

🚩 **"PostToolUse prevents rm -rf"**  
→ ❌ No, runs after. Use PreToolUse

🚩 **"Prompt instruction MUST work as security rule"**  
→ ❌ Use hook for deterministic enforcement

🚩 **"Put email body in user message for context"**  
→ ❌ Use tool_result (untrusted isolation)

🚩 **"System prompt can have database password"**  
→ ❌ Use env vars or secrets manager

🚩 **"Code review needs Edit/Bash access"**  
→ ❌ Read, Grep, Glob only

🚩 **"Deny rule can be overridden by developer"**  
→ ❌ Managed policy non-overridable

🚩 **"Trust model because it has permissions"**  
→ ❌ Zero Trust: verify every action

🚩 **"High-stakes action no human approval needed"**  
→ ❌ Always require human oversight

---

---

## **QUICK DECISION TREES**

### Jailbreak or Indirect?

```
User crafted the input to bypass rules?
├─ YES → Jailbreak
│   └─ Hardened prompt + Haiku pre-screen
└─ NO:
    Content contains malicious instructions?
    ├─ YES → Indirect injection
    │   └─ tool_result + output screen
```

### Block action before/after?

```
Want to prevent rm -rf?
├─ PreToolUse + exit 2 → BEFORE (prevents)
└─ PostToolUse → AFTER (can't prevent)
```

### Where to put content?

```
YOUR instructions?
├─ YES → System prompt
External data (email/web)?
├─ YES → tool_result
Sensitive data?
├─ YES → Never in prompts (env vars)
```

### Permissions for task?

```
Read-only code review?
├─ Read, Grep, Glob (no Edit/Bash)

Data processing?
├─ Read, Bash (no edits)

Deployment?
├─ Bash, Edit, careful approval
```

### High-stakes action?

```
Delete/Cancel/Deploy/Transfer?
├─ YES → Require human approval
└─ NO → Depends on task
```

---

---

## **30-SECOND RECAP**

✅ Jailbreak = user, Indirect = content  
✅ PreToolUse + exit 2 = prevent BEFORE  
✅ PostToolUse = audit AFTER  
✅ tool_result = untrusted isolation  
✅ Secrets = env vars, not prompts  
✅ Scope per task with allowed_tools  
✅ Human approval for high-stakes  
✅ Deny rules non-overridable  

---

---

## **STUDY TIME ALLOCATION**

| Task | Time | Priority |
|------|------|----------|
| Task 7.2 (Hooks) | 30% | 🔴 High |
| Task 7.3 (Secrets) | 30% | 🔴 High |
| Task 7.1 (Injection) | 25% | 🔴 High |
| Task 7.4 (Zero Trust) | 15% | 🟡 Medium |

---

---

## **QUICK LOOKUP**

### When to use?

| Scenario | Action |
|----------|--------|
| User jailbreak attempt | Hardened prompt + Haiku screen |
| Malicious email content | tool_result + screen output |
| Database password needed | Env var or secrets manager |
| Must prevent action | PreToolUse + exit 2 |
| Audit what happened | PostToolUse + JSONL log |
| Code review task | allowed_tools: Read/Grep/Glob |
| High-stakes action | Human approval gate |
| Deny rule | Can't be overridden ever |

### Defense layers (in depth)

1. tool_result isolation (model-level)
2. PreToolUse hooks (code-level)
3. allowed_tools scoping (permission-level)
4. Audit hooks (accountability)
5. Human approval (oversight)

### Never do

- ❌ Put secrets in prompts
- ❌ Use PostToolUse to prevent actions
- ❌ Override deny rules
- ❌ Trust just because permissions granted
- ❌ High-stakes without human approval
- ❌ Put YOUR instructions in tool_result
- ❌ Put external data in system prompt

---