# CCDV-F Domain 1: Agents & Workflows — Revision Sheet
## Quick Reference | 14.7% Exam | ~8 Questions

---

## **THE GOLDEN RULES**

1. ✅ **Start simple. Complexity must be earned.**
2. ✅ **Workflows = predictable. Agents = adaptive.**
3. ✅ **Use simplest architecture that solves the problem.**
4. ✅ **Subagents don't inherit coordinator's history** — pass context explicitly.
5. ✅ **Hooks = hard rules. Prompts = soft guidance.**

---

## **CONCEPT 1: Workflows vs Agents**

### What They Are
- **Workflow**: You define every step (LLM + tools orchestrated through code)
- **Agent**: LLM decides its own steps (dynamic, model-driven)

### When to Use
| Use Workflow | Use Agent |
|---|---|
| Well-defined tasks | Open-ended problems |
| Predictable flow | Flexibility needed |
| Cost matters | Adaptability matters |
| Document processing | Research/exploration |

### Red Flag
❌ "Complex task = always use agent"  
✅ "Use simplest approach that works"

---

## **The Five Workflow Patterns**

| Pattern | What | When |
|---------|------|------|
| **Prompt Chaining** | Sequential steps | Linear pipelines (Write → Review → Publish) |
| **Routing** | Classify then branch | Triage (Email → Complaint or Question?) |
| **Parallelization** | Concurrent subtasks | Multi-section analysis |
| **Orchestrator-Workers** | Lead decomposes, delegates | Complex research |
| **Evaluator-Optimizer** | Generate → Evaluate → Loop | Refinement (translate, improve) |

**Stress Point**: Know all 5 by name and use case.

---

## **CONCEPT 2: Agent SDK & Construction**

### What It Does
- ✅ Handles agent loop (call → tool → feed back → repeat)
- ✅ Built-in tools (Read, Edit, Bash)
- ✅ Context management
- ✅ Permissions (allowed_tools whitelist)

### When to Use SDK
✅ Standard file/command/edit agents (90% of cases)  
❌ Only hand-write when SDK doesn't expose needed control

### Critical Distinction
| Hooks (settings.json) | System Prompts (CLAUDE.md) |
|---|---|
| Deterministic enforcement | Probabilistic guidance |
| Code-level, always enforced | Model-level, not guaranteed |
| "MUST never happen" | "Should try to follow" |

**Stress Point**: Hooks can't be overridden; prompts can.

---

## **CONCEPT 3: Agentic Loop & stop_reason**

### 🔑 MOST CRITICAL CONCEPT

**Every response has stop_reason. It's the ONLY reliable signal.**

### The Five stop_reason Values

| Value | Meaning | Action |
|-------|---------|--------|
| `end_turn` | Claude finished | ✅ Use response, stop |
| `tool_use` | Claude calls tool | 🔄 Execute, loop back |
| `max_tokens` | Hit token limit | ⚠️ Increase or continue |
| `pause_turn` | Server tool hit iteration cap | ⏸️ Resume |
| `refusal` | Claude declined | ❌ Read why, retry |

### Critical Error

❌ **WRONG**: Check `content[0].type == "text"` to decide if done  
✅ **CORRECT**: Check `stop_reason == "end_turn"` to decide if done

**Why**: Claude can return text AND tool_use together. Content type alone causes premature stop.

### Tool Round-Trip Pattern

1. Define tool (input_schema)
2. Claude calls it (tool_use block)
3. You execute and return (tool_result with matching tool_use_id)
4. Claude uses result to continue

**Critical**: Always use `is_error: true` for failures. Never swallow errors silently or Claude hallucinates.

**Stress Point**: This is the exam's favorite gotcha.

---

## **CONCEPT 4: Frameworks — Match Constraint to Framework**

### The Three Frameworks

| Framework | When | Key Strength |
|-----------|------|-------------|
| **LangGraph** | Conditional branching, state rollback, approval workflows | Graph state machines + durable execution |
| **Strands** | AWS-native, managed infrastructure, Amazon Q | Lightweight + AWS integration |
| **PydanticAI** | Data extraction, structure correctness critical | Type-safe + validation at write-time |

### Exam Cues

🔷 **LangGraph**: "7-branch", "rollback", "pause for human", "resume after failure"  
🟠 **Strands**: "Amazon Q", "Bedrock", "AWS infrastructure", "managed scaling"  
🟣 **PydanticAI**: "Schema validation", "type-safe", "data extraction", "structure correctness"

### Exam Strategy

❌ "Use most popular framework"  
✅ "Match framework's core strength to scenario's constraint"

**Stress Point**: Know the decision table by heart.

---

## **CONCEPT 5: Multi-Agent Systems**

### When Multi-Agent Wins (Only 3 Situations)

| Situation | Why | Example |
|-----------|-----|---------|
| **Context Pollution** | One window can't hold everything | Research 3 topics separately |
| **Parallelism** | Subtasks run simultaneously | 5 subagents search in parallel |
| **Specialization** | Distinct prompts/tools improve focus | Search agent (web) + Analysis agent (code) |

### The Cost
⚠️ Multi-agent burns **FAR more tokens** than single agent.  
Only use when at least ONE of the 3 situations applies.

### 🔑 CRITICAL: Context Isolation

**Subagents DO NOT inherit coordinator's history.**

```
❌ Subagent has NO CONTEXT
spawn_subagent(task="Research AI safety")

✅ Pass context explicitly
spawn_subagent(
    task="Research AI safety",
    context="Focus on alignment challenges",
    tools=["web_search"]
)
```

### Debugging Incomplete Output

**Scenario**: 5 subagents succeed, report only covers 1 topic.

❌ **Wrong**: "Subagent's search was too narrow"  
✅ **Correct**: "Coordinator's decomposition was incomplete"

**Root cause**: Always trace back to coordinator's breakdown, not subagents.

**Stress Point**: Exam loves this debugging scenario.

---

## **QUICK DECISION TREES**

### Do I Need an Agent?

```
Task well-defined? → NO → Consider agent
                    → YES → Use workflow

Flexibility needed? → YES → Agent
                    → NO → Workflow

Cost sensitive? → YES → Workflow
               → NO → Maybe agent
```

### Which Framework?

```
Branching/rollback? → LangGraph
AWS-native? → Strands
Type-safe/schema? → PydanticAI
```

### Multi-Agent or Single?

```
Context pollution? YES → Multi-agent
Parallelism? YES → Multi-agent
Specialization? YES → Multi-agent
Else → Single agent (well-prompted)
```

---

## **EXAM RED FLAGS (What They LOVE Testing)**

🚩 **"Complex task needs an agent"** → ❌ Start simple  
🚩 **"Check content type to know if done"** → ❌ Check stop_reason  
🚩 **"CLAUDE.md blocks dangerous actions"** → ❌ Use settings.json hooks  
🚩 **"Silent error handling is fine"** → ❌ Use is_error: true  
🚩 **"Subagents inherit coordinator's context"** → ❌ Must pass explicitly  
🚩 **"Multi-agent is always better"** → ❌ Use for specific situations only  
🚩 **"Incomplete output = subagent's fault"** → ❌ Usually coordinator's decomposition  

---

## **STRESS POINTS FOR EXAM**

1. **stop_reason is the ONLY signal** — check it, not content type
2. **Subagents need explicit context** — they don't inherit history
3. **Hooks are hard rules, prompts are soft** — use appropriately
4. **Frameworks match constraints** — know the 3-framework decision table
5. **Start simple, earn complexity** — avoid over-engineering
6. **Multi-agent only for 3 situations** — don't default to it
7. **is_error flag on tool failures** — never swallow errors
8. **Tool round-trip needs tool_use_id match** — exact IDs required

---

## **30-SECOND RECAP**

✅ Use workflows by default, agents when adaptive needed  
✅ Use Agent SDK for standard tasks, hand-write only when needed  
✅ Check stop_reason, never check content type  
✅ Subagents need explicit context passing  
✅ Match framework to constraint (not popularity)  
✅ Multi-agent only for context pollution/parallelism/specialization  
✅ Hooks enforce, prompts guide  
✅ Always flag tool errors with is_error: true  

---

## **STUDY TIME ALLOCATION**

| Concept | Time | Priority |
|---------|------|----------|
| Concept 3 (stop_reason) | 30% | 🔴 Critical |
| Concept 5 (Multi-agent) | 25% | 🔴 High |
| Concept 1 (Workflows) | 20% | 🟡 Medium |
| Concept 4 (Frameworks) | 15% | 🟡 Medium |
| Concept 2 (SDK) | 10% | 🟢 Light |

---