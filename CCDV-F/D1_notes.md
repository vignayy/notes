# CCDV-F Domain 1: Agents & Workflows — Complete Study Guide
## ~8 Questions | 14.7% of Exam

---

## **CONCEPT 1: Workflows vs Agents — The Architectural Choice**

### Core Principle
**Workflows** = Predefined code paths (you control every step)  
**Agents** = Model directs its own process (model decides how to accomplish the task)

### Quick Comparison

| Feature | Workflow | Agent |
|---------|----------|-------|
| Control | You (predictable) | Model (adaptive) |
| Cost | Lower | Higher |
| Latency | Consistent | Variable |
| Debugging | Easier | Harder |
| Use When | Well-defined, repetitive tasks | Flexibility needed, open-ended work |

### Golden Rule
✅ **Start simple. Complexity must be earned.**
- Many complex tasks work fine with a single LLM call + retrieval
- If a workflow works, don't build an agent
- Agents trade latency/cost for adaptability

### The Five Workflow Patterns (Building Blocks)

| Pattern | How It Works | When to Use | Example |
|---------|-------------|-----------|---------|
| **Prompt Chaining** | Fixed steps; output → next input | Linear pipelines | Write doc → Proofread → Format |
| **Routing** | Classify input, send to handler | Decision-based | Email arrives → Complaint or question? |
| **Parallelization** | Run independent subtasks concurrently | Multi-section analysis | Analyze 3 report sections simultaneously |
| **Orchestrator-Workers** | Lead model decomposes, delegates | Complex decomposition | "Refactor code" → split into: syntax, optimize, test |
| **Evaluator-Optimizer** | Generate → Evaluate → Improve (loop) | Iterative refinement | Translate → Check → Retranslate until good |

### Exam Angle
❌ "Complex task = use an agent"  
✅ "Use the simplest architecture that solves the problem"

---

## **CONCEPT 2: Agent SDK & Construction — Building Production Agents**

### What the Claude Agent SDK Does
- ✅ Handles the agent loop (call → tool → feed back → repeat)
- ✅ Built-in tools (Read files, Edit files, Bash commands)
- ✅ Context management (remembers state)
- ✅ Permissions (allowed_tools whitelist)
- ✅ Multiple deployments (Claude API, Bedrock, Vertex, Microsoft Foundry)

### Minimal Example
```python
from claude_agent_sdk import query, ClaudeAgentOptions

async def main():
    async for message in query(
        prompt="Find and fix the bug in auth.py",
        options=ClaudeAgentOptions(allowed_tools=["Read","Edit","Bash"]),
    ):
        print(message)
```

### Built-in Tools
| Tool | What It Does |
|------|-------------|
| **Read** | Open and view files |
| **Edit** | Modify files |
| **Bash** | Run terminal commands |

### Design Philosophy
**"Give Your Agent a Computer"**  
- Provide a general-purpose environment
- Let Claude operate like a developer would
- Agent loop: Gather context → Take action → Verify → Repeat

### When to Use SDK vs Hand-Write

| Use SDK | Hand-Write Loop |
|--------|-----------------|
| Standard file/command/edit agents | Need control SDK doesn't expose |
| Faster to ship | Custom tool ecosystem |
| Pre-built, tested, reliable | Novel workflow patterns |
| Permission management built-in | Specialized behavior |

### Critical Distinction: Hooks vs System Prompts

| Hooks | System Prompts |
|-------|----------------|
| Hard/deterministic enforcement | Soft/probabilistic guidance |
| Code that MUST run at certain points | Instructions to the LLM |
| "Always verify before returning" | "Be careful with security" |
| **Exam tests**: Can you distinguish these? |

### Exam Angle
✅ Use the SDK by default for standard agents  
✅ Only hand-write when you need custom control  
✅ Understand the loop underneath (Concept 3)

---

## **CONCEPT 3: Agentic Loop & stop_reason — The Control Mechanism**

### CRITICAL: stop_reason is Your One True Signal

Every Claude response includes `stop_reason`. It's **deterministic and authoritative**.

### The Five stop_reason Values

| stop_reason | Meaning | What You Do |
|-------------|---------|------------|
| `end_turn` | Claude finished naturally | ✅ Use response and stop |
| `tool_use` | Claude wants to call a tool | 🔄 Execute it and loop back |
| `max_tokens` | Hit token limit | ⚠️ Increase limit or continue |
| `pause_turn` | Server tool hit iteration cap | ⏸️ Send content back to resume |
| `refusal` | Claude declined | ❌ Read why, try fallback |

### The Core Loop (Simplified)
```python
if response.stop_reason == "end_turn":
    return response.content[0].text  # Done
    
elif response.stop_reason == "tool_use":
    run_tools_and_continue(response)  # Keep looping
```

### The Tool Round-Trip (Three Acts)

**Act 1: Define the tool**
```python
tools = [{
  "name": "get_weather",
  "description": "Get current weather",
  "input_schema": {
    "type": "object",
    "properties": {"location": {"type": "string"}},
    "required": ["location"]
  }
}]
```

**Act 2: Claude uses it**
```json
{
  "type": "tool_use",
  "id": "toolu_123",
  "name": "get_weather",
  "input": {"location": "San Francisco"}
}
```

**Act 3: You execute & return**
```python
tool_result = {
  "type": "tool_result",
  "tool_use_id": "toolu_123",  # Must match
  "content": "Sunny, 72°F"
}
```

### Critical Error Patterns

❌ **Anti-Pattern: Swallow errors silently**
```python
try:
    result = get_weather("SF")
except Exception:
    tool_result = {
        "type": "tool_result",
        "tool_use_id": "toolu_123",
        "content": ""  # Empty = Claude thinks it worked!
    }
    # Claude hallucinates a response
```

✅ **Correct: Use is_error flag**
```python
tool_result = {
    "type": "tool_result",
    "tool_use_id": "toolu_123",
    "content": "Error: timeout after 30s",
    "is_error": true  # Claude knows it failed
}
```

### The Loop-Control Trap (Exam Tests This)

❌ **WRONG: Check content type**
```python
if response.content[0].type == "text":
    return response  # Agent stops prematurely!
```

**What happens**: Claude says "Let me look that up" (text) + adds a tool_use block. Your code sees text and stops. The lookup never runs.

✅ **CORRECT: Check stop_reason**
```python
if response.stop_reason == "end_turn":
    return response
elif response.stop_reason == "tool_use":
    run_tools_and_continue(response)  # Loop continues
```

**Why it works**: Claude can have text AND tools. Only `stop_reason == "end_turn"` means truly done.

### Graceful Degradation Pattern
```
Try operation
  ↓
Tool fails (is_error: true)
  ↓
Retry with different approach
  ↓
Still fails
  ↓
Escalate to human (redirect_to_agent: true)
```

Keep full context: exact IDs, original request, prior errors, current state.

### Exam Checklist
✅ Know all five stop_reason values  
✅ **Always check stop_reason, never content type**  
✅ Use is_error: true for failures  
✅ Match tool_use_id when returning results  
✅ Understand error recovery patterns  

---

## **CONCEPT 4: Frameworks — LangGraph, Strands & PydanticAI**

### Recognition-Level Knowledge Required
You don't need to code with these. Just know which constraint matches which framework.

### The Three Frameworks

#### 🔷 **LangGraph — Graph-Based State Machines**
**What it is**: Agent modeled as connected nodes/edges (like a flowchart)

**Core strengths**:
- ✅ Complex branching (7-branch approval workflow)
- ✅ State rollback (undo and try different path)
- ✅ Durable execution (crash → resume where you left off)
- ✅ Human-in-the-loop (pause for approval)
- ✅ Mix deterministic + model-driven steps

**Exam cue**: "Resume after failure", "state rollback", "approval workflow"

---

#### 🟠 **Strands — AWS-Native, Model-Driven**
**What it is**: Open-source SDK (Python & TypeScript) built by AWS

**The three pieces**:
1. A model
2. A system prompt
3. A set of tools

**Core strengths**:
- ✅ AWS-native (Bedrock, Amazon Q integration)
- ✅ Managed scaling & lifecycle
- ✅ MCP support built-in
- ✅ Multi-agent patterns (Swarms, Graphs, Workflows)
- ✅ OpenTelemetry observability

**Exam cue**: "Amazon Q", "Bedrock-native", "AWS infrastructure"

---

#### 🟣 **PydanticAI — Type-Safe Agents**
**What it is**: "FastAPI for GenAI" — fully type-safe, schema-validated

**Core strengths**:
- ✅ Type safety (bugs move to write-time)
- ✅ Pydantic validation on every input/output
- ✅ Model-agnostic
- ✅ Structured outputs guaranteed
- ✅ Tools + MCP + web search built-in

**Exam cue**: "Data extraction", "schema validation", "structure correctness critical"

---

### The Decision Table (Exam Tests This Directly)

| Constraint | Framework | Why |
|-----------|-----------|-----|
| Conditional branching, state rollback, auditable control flow | **LangGraph** | Graph state machines + durable execution |
| AWS-native, managed scaling, Amazon Q integration | **Strands** | Model-driven SDK with first-class AWS integration |
| Every input/output must be schema-validated | **PydanticAI** | Type-safe by design, validation at write-time |

### What They DON'T Test
❌ How to write full code with each framework  
❌ Specific API details  
❌ Syntax

### What They DO Test
✅ Which constraint matches which framework  
✅ Core strength of each  
✅ Why you'd pick one over another  

### Exam Angle
❌ "Pick the most popular framework for everything"  
✅ "Match the framework's core strength to the scenario's constraint"

---

## **CONCEPT 5: Multi-Agent Systems — Orchestrator-Workers**

### What is a Multi-Agent System?
Multiple LLMs running **in parallel** with **separate conversation contexts**, coordinated by code.

**Pattern**: Orchestrator (lead) spawns Subagents (workers)

```
Lead Agent
├─ Spawns Subagent-1 (research)
├─ Spawns Subagent-2 (analysis)
└─ Spawns Subagent-3 (synthesis)
    ↓ (all run in parallel)
Lead Agent synthesizes results
```

### Real Example: Claude's Research Feature
1. Lead plans: "Research X, Y, Z"
2. Subagent-1 explores X (own context window)
3. Subagent-2 explores Y (own context window)
4. Subagent-3 explores Z (own context window)
5. Lead synthesizes findings into report

### CRITICAL EXAM FACT: Context Isolation

❌ **Subagents DO NOT inherit coordinator's history**

```python
# Lead's context (INVISIBLE to subagent):
messages = [
    {"role": "user", "content": "Research AI safety and LLMs"},
    {"role": "assistant", "content": "I'll spawn subagents..."}
]

# Subagent ONLY sees:
spawn_subagent(task="Research AI safety")
# It has no context from lead's conversation!
```

✅ **Correct: Explicitly pass context**

```python
spawn_subagent(
    task="Research AI safety",
    context="Focus on alignment challenges",
    tools=["web_search"]
)
```

### Three Situations Where Multi-Agent Wins

| Situation | Why Multi-Agent Helps | Example |
|-----------|----------------------|---------|
| **Context Pollution** | One window can't hold everything | Research 3 complex topics separately |
| **Parallelism** | Subtasks run simultaneously | 5 subagents search in parallel (5x faster) |
| **Specialization** | Distinct prompts/tools improve focus | Search agent (web) + Analysis agent (code) |

### The Cost
⚠️ **Multi-agent systems burn FAR more tokens than single agent**

```
Single agent: ~10k tokens
Multi-agent (1 lead + 5 subagents): ~100k+ tokens
```

**Rule**: Only go multi-agent for high-value, parallelisable, open-ended work.

### Over-Engineering Trap

❌ **Too many agents** (Lost context at every handoff):
```
Planning Agent → Execution Agent → Review Agent → Iteration Agent
```

✅ **Single well-prompted agent** (Keeps context):
```
One agent with planning/execution/review built in
```

### Debugging Incomplete Output

**Scenario**: 5 subagents all succeed, but final report only covers 1 topic.

❌ **Wrong diagnosis**: "Subagent's search was too narrow"

✅ **Correct diagnosis**: "Coordinator's decomposition was incomplete"

**Key insight**: **If output is incomplete in SCOPE (not depth), the coordinator's decomposition is the root cause, not any subagent.**

### Explicit Context Passing Pattern
```python
# This line is critical:
spawn_research_subagent(
    topic="AI alignment",
    context="Focus on current research",  # ← Explicitly passed
    tools=["web_search"]
)
# Without this, subagent is blind
```

### Decision Tree: Do I Need Multi-Agent?

```
├─ Is context pollution a problem? YES → Multi-agent
├─ Can subtasks run in parallel? YES → Multi-agent
├─ Do tools/prompts significantly improve focus? YES → Multi-agent
└─ Else → Single agent (well-prompted)
```

---

## **DOMAIN 1 FINAL EXAM CHECKLIST** ✅

### Concept 1: Workflows vs Agents
- [ ] Know the five workflow patterns
- [ ] Understand: workflows = predictable, agents = adaptive
- [ ] Remember: start simple, complexity must be earned

### Concept 2: Agent SDK
- [ ] Know what SDK provides (loop, tools, context, permissions)
- [ ] Understand: SDK vs hand-writing trade-off
- [ ] Remember: Hooks = hard rules, Prompts = soft guidance

### Concept 3: stop_reason Loop
- [ ] Know all five stop_reason values
- [ ] **CRITICAL**: Check stop_reason, not content type
- [ ] Remember: is_error flag for tool failures
- [ ] Understand: tool round-trip (define → use → return)

### Concept 4: Frameworks
- [ ] LangGraph = branching, rollback, state machines
- [ ] Strands = AWS-native, managed scaling
- [ ] PydanticAI = type-safe, schema validation
- [ ] Know the decision table by heart

### Concept 5: Multi-Agent
- [ ] Remember: Subagents don't inherit coordinator's history
- [ ] Know three situations where multi-agent wins
- [ ] Understand: context must be explicitly passed
- [ ] Remember: Use single agent first, multi-agent only when needed
- [ ] Know: Incomplete scope = coordinator's problem, not subagent's

---

## **Rapid-Fire Exam Questions You'll See**

1. **"Should I build an agent for this task?"** → Depends on: Is it well-defined? Can a workflow handle it? Avoid over-engineering.

2. **"My agent stopped after saying it would look something up, but didn't actually look it up."** → You checked `content[0].type == "text"` instead of `stop_reason`. Always check stop_reason.

3. **"Which framework for conditional branching and state rollback?"** → LangGraph.

4. **"Multi-agent system returns data on first topic only."** → Coordinator's decomposition was incomplete, not subagent's problem.

5. **"When to use the Agent SDK?"** → For standard file/command/edit agents. Hand-write only if you need control the SDK doesn't expose.

6. **"Tool returns error but agent hallucinates the answer anyway."** → You didn't use `is_error: true`. Always flag failures.

7. **"AWS-native agent framework?"** → Strands.

8. **"Type-safe agent framework?"** → PydanticAI.

---

## **Quick Reference: Decision Trees**

### When to Build an Agent?
```
Task well-defined?
├─ YES → Try workflow first
└─ NO → Consider agent
```

### Which Framework?
```
Constraint?
├─ Branching/rollback → LangGraph
├─ AWS-native → Strands
└─ Type-safe/schema → PydanticAI
```

### Multi-Agent or Single?
```
Context pollution OR parallelism OR specialization?
├─ YES → Multi-agent
└─ NO → Single agent (well-prompted)
```

### Why Did the Agent Stop?
```
Check stop_reason:
├─ end_turn → Done
├─ tool_use → Execute tool, loop
├─ max_tokens → Increase limit
├─ pause_turn → Resume
└─ refusal → Try fallback
```

---

## **Key Phrases to Recognize on the Exam**

🔷 **LangGraph cues**: "approval workflow", "pause for human", "resume after crash", "state rollback"

🟠 **Strands cues**: "Amazon Q", "Bedrock", "AWS infrastructure", "managed scaling"

🟣 **PydanticAI cues**: "schema validation", "type-safe", "structure correctness", "data extraction"

❌ **Anti-pattern cues**: "Lost context at handoff", "over-engineered", "checked content type", "silent error"

✅ **Correct approach cues**: "checked stop_reason", "passed context explicitly", "started with single agent", "used is_error: true"

---