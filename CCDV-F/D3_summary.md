# CCDV-F Domain 3: Claude Code — Revision Sheet
## Quick Reference | 3.1% Exam | ~2 Questions (LIGHTEST)

---

## **THE GOLDEN RULES**

1. ✅ **CLAUDE.md guides. settings.json enforces.**
2. ✅ **PreToolUse + exit 2 = BLOCK before execution.**
3. ✅ **PostToolUse = format/audit AFTER (can't prevent).**
4. ✅ **Subagents run in isolated context** — don't inherit parent history.
5. ✅ **`-p` flag = headless mode** (mandatory for CI).
6. ✅ **API keys via secrets**, never hardcoded.

---

## **TASK 3.1: CONFIGURATION**

### CLAUDE.md vs settings.json

| Feature | CLAUDE.md | settings.json |
|---------|-----------|---------------|
| **Nature** | Probabilistic | Deterministic |
| **Overridable** | ✅ Yes | ❌ No |
| **Use** | "Prefer this style" | "MUST never happen" |
| **Example** | "Use TypeScript" | "Block DELETE queries" |

### The Golden Rule
```
"MUST" → settings.json hook
"Prefer" → CLAUDE.md
```

---

## **Scope Hierarchy**

**Precedence** (highest to lowest):
```
Managed (org policy)
    ↓
CLI args
    ↓
Local (per-machine, gitignored)
    ↓
Project (.claude/, shared via git)
    ↓
User (~/.claude/, personal)
```

### Key Fact: Permissions MERGE
Lower scopes can't override higher scopes' blocks. Deny at any level = stays denied.

---

## **🔑 THE SCOPE TRAP (Exam Loves This)**

**Problem**: New dev doesn't see team conventions.

**Root Cause**: Conventions in user scope (~/.claude/), not project scope.

**Fix**: Move to project scope (.claude/CLAUDE.md) so it's shared via git.

---

## **Directory Structure**

```
.claude/
├── CLAUDE.md                  # Conventions (every session)
├── settings.json              # Hooks, MCP, permissions
├── skills/<name>/SKILL.md     # Reusable workflows
├── agents/<name>.md           # Subagent definitions
└── commands/<name>.md         # Custom /commands
```

---

## **Prompt Injection Defense**

❌ **Weak**: CLAUDE.md says "Never delete production"  
✅ **Strong**: settings.json hook blocks DELETE with exit code 2

**Lesson**: Prompts can be overridden. Code hooks cannot.

---

## **Task 3.1 Stress Points**

🚩 Conventions in user scope (personal), not project scope (shared)  
🚩 CLAUDE.md alone won't block dangerous actions  
🚩 Scope hierarchy matters for organization  

---

---

## **TASK 3.2: SKILLS & SUBAGENTS**

### Quick Decision

| Use | When |
|-----|------|
| **Skill** | Reusable workflow, stays in main context |
| **Subagent** | Verbose side task, isolate the mess |
| **Command** | One-shot stored prompt (merged into skills) |

---

## **SKILL: Reusable Workflows**

- Loads only when used (cheap until needed)
- Stays in main conversation
- Medium complexity workflows

---

## **SUBAGENT: Isolated Context**

### Key Rule
**Subagents DO NOT inherit parent's history.**

Parent must pass relevant context explicitly.

### Benefits
- Context isolation (no pollution)
- Parallelization (concurrent subtasks)
- Tool restrictions (read-only subagent)

### When to Use
✅ Verbose side task (complex analysis)  
✅ Want to avoid context pollution  
✅ Tool restrictions needed (read-only)  

---

## **The Verbose Side Task**

**Without subagent**: Main conversation fills with 50 files (2500+ lines)  
**With subagent**: Subagent reads 50 files (isolated), returns only summary

---

## **Conversational Modes**

| Command | Use |
|---------|-----|
| `/compact` | Compress conversation, continue task |
| `/clear` | Fresh start, keep CLAUDE.md + skills |

---

## **Task 3.2 Stress Points**

🚩 Subagents don't inherit parent context  
🚩 Use subagents to avoid context pollution  
🚩 Skills load on-demand, Subagents run isolated  

---

---

## **TASK 3.3: HOOKS & AUTOMATION**

### Events

| Event | When | Use |
|-------|------|-----|
| **PreToolUse** | BEFORE tool runs | **BLOCK dangerous commands** |
| **PostToolUse** | AFTER tool succeeds | Auto-format, audit-log, redact |
| **UserPromptSubmit** | On user message | Input screening |
| **SessionStart** | Session begins | Setup |
| **Stop** | Agent finishes | Cleanup |

---

### Exit Codes (PreToolUse)

| Code | Meaning |
|------|---------|
| **0** | Allow (proceed) |
| **1** | Warning (still proceed) |
| **2** | **BLOCK** (deny, feed reason to Claude) |

### 🔑 Magic Number: Exit Code 2
Blocks and explains to Claude why action was denied.

---

## **🔑 THE TIMING TRAP (Exam Favorite)**

**Problem**: Developer uses PostToolUse with exit code 2 to prevent `rm -rf`  
**Result**: Command STILL RUNS (then gets logged)

**Why**: PostToolUse fires AFTER execution — too late to prevent.

**Fix**: Use PreToolUse + exit code 2 to block BEFORE execution.

---

## **Matchers**

| Matcher | Matches |
|---------|---------|
| `Bash` | Bash commands |
| `Edit` | File edits |
| `Edit|*.ts` | TypeScript edits only |
| `Write` | File writes |

---

## **Verify Hooks**

```
/hooks
```

Shows which hooks are configured.

---

## **Task 3.3 Stress Points**

🚩 PreToolUse + exit 2 = block BEFORE  
🚩 PostToolUse = format/audit AFTER (can't block)  
🚩 Timing matters: when the hook fires determines what it can do  

---

---

## **TASK 3.4: HEADLESS & CI/CD**

### The `-p` Flag (Critical)

Running headless = no interactive terminal.

❌ **Without `-p`**: Claude waits for input → CI hangs forever  
✅ **With `-p`**: Runs prompt, outputs result, exits  

---

## **The Hanging Pipeline**

**Scenario**: CI runs Claude Code, hangs 30min, times out  
**Root Cause**: Missing `-p` flag  
**Fix**: Add `-p "your prompt"`

---

## **Headless Mode Flags**

| Flag | Use |
|------|-----|
| `-p "prompt"` | Single prompt, headless |
| `--output-format json` | Machine-parseable output |
| `--max-turns N` | Cost control (cap agentic turns) |

---

## **Output Formats**

| Format | When |
|--------|------|
| `text` | Default, human-readable |
| `json` | Machine parsing |
| `stream-json` | Real-time streaming |

---

## **GitHub Actions**

```yaml
- uses: anthropics/claude-code-action@v1
  with:
    anthropic_api_key: ${{ secrets.ANTHROPIC_API_KEY }}
    prompt: "Review this PR"
    max_turns: 5
```

---

## **Two Permission Layers**

### Layer 1: GitHub Token
```yaml
permissions:
  pull-requests: write
  contents: read
```

### Layer 2: Claude Tools
```yaml
allowed_tools: "Read,Grep"
```

**Both matter**: GitHub controls workflow, Claude controls Claude Code.

---

## **🔑 THE AUTONOMOUS RUN PROBLEM**

**Scenario**: CI job needs to Edit files, but Edit not pre-allowed → Stalls

**Fix**: Pre-allow exactly needed tools

```yaml
allowed_tools: "Edit,Write"
```

**Principle**: Least privilege applied to CI.

---

## **API Key Management**

❌ **WRONG**: Hardcoded in YAML  
✅ **CORRECT**: Use GitHub secrets `${{ secrets.ANTHROPIC_API_KEY }}`

Set in: Repo → Settings → Secrets → Add `ANTHROPIC_API_KEY`

---

## **Agent SDK Connection**

Same agent loop as CLI:
- Domain 1 Concept 2 covers the loop
- SDK drives it programmatically
- Headless mode uses same loop

---

## **Task 3.4 Stress Points**

🚩 Missing `-p` flag = pipeline hangs  
🚩 API keys exposed in YAML = security breach  
🚩 Tools not pre-allowed = job stalls  
🚩 Forget permission layers = unexpected denials  

---

---

## **EXAM RED FLAGS**

🚩 **"Put 'Never delete' in CLAUDE.md and it will be enforced"**  
→ ❌ Use settings.json hook with exit code 2

🚩 **"PostToolUse with exit code 2 prevents command"**  
→ ❌ Too late. Use PreToolUse + exit 2

🚩 **"CI hangs but works on laptop"**  
→ ❌ Missing `-p` flag

🚩 **"Subagent inherited parent's context"**  
→ ❌ Subagents isolated, must pass context

🚩 **"API key in GitHub YAML is fine"**  
→ ❌ Use secrets, never hardcode

🚩 **"New dev sees our team conventions"**  
→ ❌ If in user scope (~/.claude/), they don't

---

---

## **QUICK DECISION TREES**

### CLAUDE.md or settings.json?
```
MUST be enforced? YES → settings.json
Should be followed? YES → CLAUDE.md
```

### PreToolUse or PostToolUse?
```
Need to PREVENT? PreToolUse + exit 2
Need to FORMAT/AUDIT? PostToolUse
```

### Skill or Subagent?
```
Reusable, main context? Skill
Verbose, isolate? Subagent
```

### Local or Project or User scope?
```
Personal machine only? User (~/.claude/)
Team shared? Project (.claude/)
Organization? Managed
```

---

---

## **30-SECOND RECAP**

✅ CLAUDE.md = guidance, settings.json = enforcement  
✅ Scope: Managed > CLI > Local > Project > User  
✅ Skills = reusable, Subagents = isolated  
✅ PreToolUse + exit 2 = block before  
✅ PostToolUse = format/audit after  
✅ `-p` flag = headless (required for CI)  
✅ API keys via secrets, not hardcode  
✅ Two layers: GitHub + Claude tools  

---

---

## **STUDY TIME ALLOCATION**

| Task | Time | Priority |
|------|------|----------|
| Task 3.1 (Config) | 40% | 🔴 High |
| Task 3.3 (Hooks) | 30% | 🔴 High |
| Task 3.4 (CI/CD) | 20% | 🟡 Medium |
| Task 3.2 (Skills) | 10% | 🟡 Medium |

---

---

## **QUICK LOOKUP**

### When to use?
- **Block dangerous command** → PreToolUse + exit 2
- **Format after edit** → PostToolUse
- **Automate reusable workflow** → Skill
- **Isolate verbose task** → Subagent
- **Run in CI** → `-p` flag + secrets
- **Block in CI** → Pre-allow only needed tools

### What's guaranteed?
- ✅ settings.json hooks always enforce
- ✅ Exit code 2 blocks action
- ✅ Scope precedence (Managed > User)
- ✅ `-p` flag runs headless

### What can fail?
- ❌ CLAUDE.md can be overridden
- ❌ PostToolUse can't prevent
- ❌ CI hangs without `-p`
- ❌ Hardcoded API keys expose

---