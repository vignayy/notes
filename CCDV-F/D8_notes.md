# CCDV-F Domain 8: Tools & MCPs — Complete Study Guide
## ~6 Questions | 10.6% of Exam (FINAL DOMAIN)

---

## **OVERVIEW: Four Interconnected Tasks**

| Task | Focus | Key Principle | Exam Weight |
|------|-------|---------------|------------|
| 8.1 | Tool Implementation | Claude requests, code executes | High |
| 8.2 | MCP Architecture | Three primitives, two transports | High |
| 8.3 | Types & Orchestration | Server vs client, tool_choice | High |
| 8.4 | Remote MCP | HTTP only, tools only, beta header | Medium |

---

---

# **TASK 8.1: TOOL IMPLEMENTATION**

## **The Tool-Use Round Trip**

### **🔑 The Fundamental Principle**

```
Claude does NOT execute tools.
Claude returns a structured request.
YOUR code executes and returns the result.
```

This separation is the **safety boundary** the exam tests repeatedly.

---

## **The Five Steps**

### **Step 1: Define Tools**

```python
tools = [{
    "name": "get_weather",
    "description": "Get current weather for a location.",
    "input_schema": {
        "type": "object",
        "properties": {
            "location": {"type": "string"}
        },
        "required": ["location"]
    }
}]
```

### **Step 2: Claude Analyzes Request**

User: "What's the weather in San Francisco?"

Claude decides: "I should call get_weather with location='San Francisco'"

### **Step 3: Claude Returns tool_use Block**

```json
{
  "stop_reason": "tool_use",
  "content": [{
    "type": "tool_use",
    "id": "toolu_01abc",
    "name": "get_weather",
    "input": { "location": "San Francisco" }
  }]
}
```

### **Step 4: Your Code Executes**

```python
if tool_use.name == "get_weather":
    result = get_weather(tool_use.input["location"])
    # Returns: "72°F, sunny"
```

### **Step 5: Return tool_result**

```json
{
  "type": "tool_result",
  "tool_use_id": "toolu_01abc",
  "content": "72°F, sunny"
}
```

Claude uses this to answer: "It's 72°F and sunny in San Francisco."

---

## **The Dispatcher Analogy**

Claude is a dispatcher at a call center:
- Decides which department to route to
- Requests information from that department
- But never picks up the phone itself

Your code is the department:
- Does the actual work
- Reports back with results
- Decides whether to actually perform actions

This separation makes tool use **safe**: Claude can request any action, but your code decides whether to actually perform it.

---

## **Tool Definition Rules**

| Field | Rule | Exam Trap |
|-------|------|----------|
| **name** | Regex: `^[a-zA-Z0-9_-]{1,64}$` | No spaces or special chars |
| **description** | "Provide extremely detailed descriptions" | Vague descriptions = wrong tool selected |
| **input_schema** | JSON Schema for inputs only | Does NOT define outputs — common distractor |

---

## **The is_error Flag**

### **Success (Normal Result)**

```json
{
  "type": "tool_result",
  "tool_use_id": "toolu_01",
  "content": "72°F, sunny"
}
```

### **Failure (Must Flag It)**

```json
{
  "type": "tool_result",
  "tool_use_id": "toolu_01",
  "is_error": true,
  "content": "Error: City not found: 'Atlantiss'"
}
```

### ⚠️ **The Trap**

Without `is_error: true`, Claude treats the error string as normal data:

```
❌ Claude says: "Your city's status is: Connection timeout"
✅ Claude should: "I couldn't reach the server, let me retry"
```

---

## **Ambiguous Tool Descriptions Case Study**

### **Scenario**

Both `get_customer_profile` and `get_customer_orders` have similar descriptions.

Claude picks the wrong one 40% of the time.

### **Fix**

Make descriptions specific:

```
get_customer_profile:
"Retrieves customer profile (name, email, preferences). 
Use this for identity and contact info. 
Do NOT use for order history — use get_customer_orders instead."

get_customer_orders:
"Retrieves customer's order history. 
Use this for past purchases and status. 
Do NOT use for profile info — use get_customer_profile instead."
```

---

## **Input Schema Misconception**

### ❌ **Wrong**

Defining output fields in `input_schema`:

```json
{
  "input_schema": {
    "properties": {
      "location": {"type": "string"},
      "temperature": {"type": "number"},  // ← WRONG (output field)
      "conditions": {"type": "string"}    // ← WRONG
    }
  }
}
```

Claude tries to fill these as inputs, causing confusion.

### ✅ **Correct**

`input_schema` is for the request only:

```json
{
  "input_schema": {
    "properties": {
      "location": {"type": "string"}
    },
    "required": ["location"]
  }
}
```

Return structured output in your code:

```python
return {
    "temperature": 72,
    "conditions": "sunny"
}
```

---

## **Task 8.1 Key Takeaways**

✅ Claude requests, your code executes  
✅ Always flag errors with `is_error: true`  
✅ Detailed descriptions prevent wrong tool selection  
✅ input_schema = request only, not response  
✅ Stop reason: "tool_use" = execute, then continue  
✅ tool_use_id must match exactly  

---

---

# **TASK 8.2: MCP ARCHITECTURE**

## **MCP: The Three Primitives**

MCP (Model Context Protocol) is an open standard for connecting AI to external data, tools, and workflows.

**Described as**: "A USB-C port for AI"

### **The N×M Problem**

Before MCP:
- 10 AI apps × 50 services = 500 custom integrations

With MCP:
- 10 apps + 50 servers = 10 MCP clients + 50 MCP servers
- One protocol, everything connects

---

## **Three Primitives**

| Primitive | What It Does | Example |
|-----------|-------------|---------|
| **Tools** | Actions the model can perform | create_issue, search_docs, send_email |
| **Resources** | Data the model can read | file contents, DB records, API responses |
| **Prompts** | Predefined task templates | code_review, summarise_meeting |

---

## **Architecture: Host → Client → Server**

```
Host (AI application)
    ↓
Client (translates between host and server)
    ↓
Server (exposes capabilities)
```

---

## **The Universal Adapter Analogy**

MCP is like a universal power adapter:
- Before: Every service needs a custom adapter for each device
- After: One standard adapter, everything works

The three primitives are three types of pins:
- **Tools**: Do things (outputs data)
- **Resources**: Provide data (inputs to tools)
- **Prompts**: Provide templates (guides tasks)

---

## **Two Transport Options**

### **🟢 stdio (Standard Input/Output)**

**How**: Client launches server as subprocess; messages via stdin/stdout

**Use when**: 
- Local tools
- Same machine
- Development environments

**Security**: Process isolation, inherits OS permissions

```json
{
  "mcpServers": {
    "memory": {
      "command": "npx",
      "args": ["-y", "@modelcontextprotocol/server-memory"],
      "type": "stdio"
    }
  }
}
```

---

### **🔵 Streamable HTTP (HTTP + optional SSE)**

**How**: Independent server, HTTP POST/GET

**Use when**:
- Remote servers
- Multi-client setups
- Production deployments

**Security**: MUST validate Origin header (DNS-rebinding defense)

```json
{
  "mcpServers": {
    "github": {
      "url": "https://mcp.github.com/mcp",
      "type": "http"
    }
  }
}
```

---

## **🔑 Key Transport Decision**

| Scenario | Transport | Why |
|----------|-----------|-----|
| Local dev tool for 1 person | stdio | Runs locally as subprocess |
| Shared team server (20 devs) | HTTP | Centralized, one deployment |
| Production service | HTTP | Multi-client, remotely managed |
| Cloud-hosted third party | HTTP | Only option for remote |

---

## **The Shared Server Case Study**

### **Scenario**

20 developers need access to an MCP server for internal JIRA.

Each uses Claude Code on their own machine.

Should it use stdio or HTTP?

### **Wrong Answer**

stdio — runs as local subprocess on each machine

**Problem**: 20 separate installations, 20 configs, no centralization

### **Correct Answer**

Streamable HTTP — runs once on shared host

**Benefits**: 
- Centralized auth
- One deployment
- All 20 connect via URL

---

## **DNS-Rebinding Defense**

For Streamable HTTP servers:

**Must validate Origin header** to prevent DNS-rebinding attacks.

**Must bind to localhost** (not 0.0.0.0) when meant for local use.

---

## **Hook Events: mcp__server__tool**

MCP tools appear in hook events with the pattern:

```
mcp__server__tool
```

Example matcher:

```json
{
  "matcher": "mcp__server__tool"
}
```

This allows PreToolUse hooks to validate/block MCP tools.

---

## **Tool Hints**

MCP tools can declare hints about behavior:

| Hint | Meaning |
|------|---------|
| `readOnlyHint: true` | Only reads data (safe to auto-approve) |
| `destructiveHint: true` | Can modify or delete (should prompt) |

**Important**: Hints are **advisory**, not enforced. Server declares them; client decides what to do.

---

## **Task 8.2 Key Takeaways**

✅ Three primitives: Tools, Resources, Prompts  
✅ stdio = local subprocess  
✅ Streamable HTTP = remote server  
✅ Transport decision: local vs remote, single vs multi-client  
✅ HTTP servers must validate Origin header  
✅ MCP tools appear as `mcp__server__tool` in hooks  

---

---

# **TASK 8.3: TYPES & ORCHESTRATION**

## **Server Tools vs Client Tools**

### **Where Does the Code Execute?**

| Type | Runs Where | Examples | How It Works |
|------|-----------|----------|------------|
| **Server tools** | Anthropic's infrastructure | web_search, web_fetch, code_execution | Results in same response |
| **Client tools** | Your application | Your custom functions, bash, editor | stop_reason: "tool_use", you execute |

---

## **Four Ways to Extend Claude Code**

1. **Built-in tools** — Read, Edit, Bash, Glob, Grep (your code runs these)
2. **Custom tools** — Your API functions (you write execution)
3. **Skills** — SKILL.md files in `.claude/skills/` (local instructions, no server)
4. **MCP tools** — Protocol connections to running servers (server-hosted code)

---

## **The Skill vs MCP Distinction**

| Aspect | Skill | MCP Tool |
|--------|-------|----------|
| **Storage** | Local markdown file | Remote server |
| **Execution** | Instructions (no code) | Server code executes |
| **Update** | Edit file locally | Server deployer updates |
| **Privacy** | Stays local | Data flows through server |

---

## **tool_choice: Controlling Tool Selection**

### **Three Options**

| Value | Behavior | When to Use |
|-------|----------|------------|
| **auto** (default) | Claude decides: call tool or answer | Most cases — let Claude decide |
| **any** | Must call some tool (can't answer text) | "Must use tool but which depends on input" |
| **{type:"tool", name:"specific"}** | Must call that specific tool | "Must output via this tool only" |

---

## **Examples**

### **auto (default)**

```python
response = client.messages.create(
    model="claude-sonnet-4-6",
    max_tokens=1000,
    messages=[...],
    tools=[...]
    # tool_choice not specified → auto
)
```

Claude decides: call tool, answer text, or both.

### **any**

```python
response = client.messages.create(
    model="claude-sonnet-4-6",
    max_tokens=1000,
    messages=[...],
    tools=[...],
    tool_choice={"type": "any"}
)
```

Claude MUST call a tool. Can't respond with text only.

### **Specific Tool**

```python
response = client.messages.create(
    model="claude-sonnet-4-6",
    max_tokens=1000,
    messages=[...],
    tools=[...],
    tool_choice={"type": "tool", "name": "classify_ticket"}
)
```

Claude MUST call classify_ticket. No other choice.

---

## **Parallel Tool Calls**

### **🔑 The Rules**

By default, Claude may return **multiple `tool_use` blocks in one turn**.

```
├─ Execution order: unordered (run concurrently or sequentially)
├─ Result matching: one `tool_result` per `tool_use` by id
└─ All results must come before any text content
```

---

## **Handling Multiple Calls**

Claude returns:

```json
{
  "stop_reason": "tool_use",
  "content": [
    {"type": "tool_use", "id": "t1", "name": "get_customer", ...},
    {"type": "tool_use", "id": "t2", "name": "get_orders", ...},
    {"type": "tool_use", "id": "t3", "name": "get_payments", ...}
  ]
}
```

Your code:

```python
# Execute all three (parallel or sequential)
results = [
    execute("get_customer", t1.input),
    execute("get_orders", t2.input),
    execute("get_payments", t3.input)
]

# Return all three results
tool_results = [
    {"type": "tool_result", "tool_use_id": "t1", "content": result1},
    {"type": "tool_result", "tool_use_id": "t2", "content": result2},
    {"type": "tool_result", "tool_use_id": "t3", "content": result3}
]
```

---

## **⚠️ The Skipped Call Trap**

If one of 3 parallel calls fails, you **must still return a result** for it.

```python
# ❌ WRONG: Skip the failed call
tool_results = [
    {"type": "tool_result", "tool_use_id": "t1", "content": result1},
    # Missing t2
    {"type": "tool_result", "tool_use_id": "t3", "content": result3}
]

# ✅ CORRECT: Return error for failed call
tool_results = [
    {"type": "tool_result", "tool_use_id": "t1", "content": result1},
    {"type": "tool_result", "tool_use_id": "t2", "is_error": true, "content": "Timeout"},
    {"type": "tool_result", "tool_use_id": "t3", "content": result3}
]
```

---

## **Disable Parallel Calls**

If you need sequential execution:

```python
response = client.messages.create(
    model="claude-sonnet-4-6",
    max_tokens=1000,
    messages=[...],
    tools=[...],
    disable_parallel_tool_use=True
)
```

Claude returns one tool_use at a time.

---

## **Forced tool_choice Incompatibility**

### ⚠️ **400 Error**

Using `tool_choice: "any"` or specific tool **with extended thinking** returns 400.

### **The Fix**

```python
# ❌ DON'T DO THIS
response = client.messages.create(
    model="claude-opus-4-8",
    max_tokens=1000,
    messages=[...],
    tools=[...],
    tool_choice={"type": "any"},
    thinking={"type": "enabled", "budget_tokens": 10000}
)  # → 400 error

# ✅ DO THIS
response = client.messages.create(
    model="claude-opus-4-8",
    max_tokens=1000,
    messages=[...],
    tools=[...],
    tool_choice={"type": "auto"},  # Not forced
    thinking={"type": "enabled", "budget_tokens": 10000}
)
```

Use `tool_choice: "auto"` with extended thinking. Guide tool use via system prompt instead.

---

## **The Agentic Loop**

Driven by **stop_reason**, not by parsing text:

```
while True:
    response = client.messages.create(...)
    
    if response.stop_reason == "tool_use":
        # Execute tools and continue
        tool_results = execute_tools(response)
        continue with tool_results
    
    elif response.stop_reason == "end_turn":
        # Done
        return response.content
```

---

## **The SDK Tool Runner**

The Agent SDK has a built-in `Tool Runner` that automates this entire loop:

- Handles stop_reason checking
- Executes tools automatically
- Returns when loop is done

Use it unless you need custom execution control.

---

## **Skipped Parallel Call Case Study**

### **Scenario**

Claude returns 3 tool_use blocks.

Developer executes only the first one and returns just 1 tool_result.

### **What Happens**

Message structure breaks. May cause 400 error or unpredictable behavior.

### **Fix**

Always return a result for every tool call:

```python
# For failed tools, use is_error: true
results = []
for tool_use in tool_uses:
    try:
        result = execute(tool_use)
        results.append({
            "type": "tool_result",
            "tool_use_id": tool_use.id,
            "content": result
        })
    except Exception as e:
        results.append({
            "type": "tool_result",
            "tool_use_id": tool_use.id,
            "is_error": true,
            "content": str(e)
        })
```

---

## **Server vs Client Tool in Same Batch**

When both are called together:

- **Server tool** (web_search): Result comes inline in same response
- **Client tool** (bash): stop_reason: "tool_use", waits for your execution

You handle both, but the server tool's result is already available.

---

## **When to Build Custom Tools**

Should you build a custom client tool for something web_search can do?

**No.** Reach for built-in server tools first:
- Less code to write
- No infrastructure to maintain
- Anthropic handles updates and reliability
- Same safety standards

Build custom tools **only** for your specific systems and business logic.

---

## **Task 8.3 Key Takeaways**

✅ Server tools (web_search) run on Anthropic infrastructure  
✅ Client tools (your code) run on your infrastructure  
✅ tool_choice: auto (default) → Claude decides  
✅ tool_choice: any → must call some tool  
✅ Parallel calls: execute all, return all results  
✅ Stop reason drives the loop, not text parsing  
✅ Always return is_error for failed tools  
✅ Use built-in server tools before custom tools  

---

---

# **TASK 8.4: REMOTE MCP (THE MCP CONNECTOR)**

## **The MCP Connector**

Let Claude access remote MCP servers directly from the Messages API.

```python
response = client.beta.messages.create(
    model="claude-sonnet-4-6",
    max_tokens=1000,
    messages=[...],
    mcp_servers=[{
        "type": "url",
        "url": "https://mcp.example.com/mcp",
        "name": "my_tools"
    }],
    betas=["mcp-client-2025-11-20"]
)
```

---

## **🔑 Four Constraints**

1. **HTTP only** — Streamable HTTP or SSE
2. **Tools only** — Can't access resources or prompts
3. **Beta header required** — `betas=["mcp-client-2025-11-20"]`
4. **Not on Bedrock/Vertex** — Direct Anthropic API only

---

## **What's Supported**

| Feature | Supported? | Notes |
|---------|-----------|-------|
| **Tool calls** | ✅ Yes | Full tool_use/tool_result flow |
| **Resources** | ❌ No | Cannot read MCP resources |
| **Prompts** | ❌ No | Cannot use MCP prompt templates |
| **stdio servers** | ❌ No | HTTP only |
| **Bedrock/Vertex** | ❌ No | API only |
| **Auth** | ✅ Yes | Pass auth headers/tokens |

---

## **The "Zero Infrastructure" Option**

Instead of running your own MCP client:
- **Before**: You maintain an MCP client on your infrastructure
- **After**: Pass server URL to API, Anthropic handles the client

**Trade-off**: Tools only (no resources/prompts), but fast and simple.

---

## **Local vs Remote: The Trade-off**

### **Local stdio Server**

```
My Machine
├─ Claude Code
└─ MCP Server (stdio) ← Only local access
```

Cannot be connected via MCP connector (no HTTP endpoint).

### **Remote Streamable HTTP Server**

```
My Infrastructure          Anthropic Infrastructure
├─ MCP Server (HTTP)  ←→  Messages API
                                ↑
                           Client connects via URL
```

Can be connected via MCP connector.

---

## **Deployment Decision**

| Scenario | Choice | Why |
|----------|--------|-----|
| Local dev, filesystem tools | stdio + local client | subprocess communication |
| Remote team, shared service | HTTP + MCP connector | centralized URL access |
| Need resources/prompts | Full MCP client | connector is tools-only |
| Quick API integration | HTTP + MCP connector | zero infrastructure |

---

## **The stdio Mistake Case Study**

### **Scenario**

Developer tries:

```python
response = client.beta.messages.create(
    model="claude-sonnet-4-6",
    mcp_servers=[{
        "type": "stdio",
        "command": "node",
        "args": ["./server.js"]
    }],
    ...
)
```

**Result**: Fails. The connector doesn't accept stdio config.

### **Why**

The `mcp_servers` parameter only accepts:

```python
{"type": "url", "url": "..."}
```

You cannot pass command/args.

### **Fix Options**

1. Deploy server with HTTP transport and pass URL
2. Run local MCP client that handles stdio, proxy to your app

---

## **Resources/Prompts Limitation**

### **Can't Access Resources**

The MCP connector is tools-only.

To access resources from a remote server, you need a **full MCP client** running on your infrastructure.

### **Example**

Remote MCP server exposes:
- Tools: search_docs, create_issue
- Resources: company_handbook, API_reference

**Via MCP connector**: Can only call search_docs, create_issue

**Via full MCP client**: Can access all tools AND read handbook/API_reference

---

## **Security: Trust Your Servers**

### ⚠️ **Important**

Remote MCP servers are not owned or endorsed by Anthropic.

**Only connect to servers you trust.**

Review each server's:
- Security practices
- Privacy policy
- Terms of service

**Your data flows through that server** — treat it like granting API access.

---

## **Authentication**

Pass auth headers/tokens:

```python
response = client.beta.messages.create(
    model="claude-sonnet-4-6",
    max_tokens=1000,
    messages=[...],
    mcp_servers=[{
        "type": "url",
        "url": "https://mcp.example.com/mcp",
        "name": "my_tools",
        "headers": {
            "Authorization": "Bearer your_token_here",
            "X-API-Key": "your_api_key"
        }
    }],
    betas=["mcp-client-2025-11-20"]
)
```

---

## **Custom Connectors vs API Connector**

### **Custom Connectors** (Claude.ai/Claude Code)

- Configured in Settings → Connectors
- Interactive OAuth flows
- User-initiated auth

### **API Connector** (Messages API)

- Programmatic via `mcp_servers` parameter
- Pass auth headers/tokens in code
- System-level auth

Both connect to remote HTTP servers, but UX/auth differs.

---

## **Task 8.4 Key Takeaways**

✅ HTTP only, tools only, beta header required  
✅ stdio servers can't be connected  
✅ Connector = zero infrastructure option  
✅ Resources/prompts need full MCP client  
✅ Must trust remote servers (data flows through)  
✅ Pass auth headers for authenticated servers  
✅ Local = subprocess, Remote = HTTP URL  

---

---

# **DOMAIN 8 FINAL EXAM CHECKLIST** ✅

## **Task 8.1: Tool Implementation**
- [ ] Claude requests, code executes
- [ ] Always flag errors with is_error: true
- [ ] Detailed descriptions prevent misselection
- [ ] input_schema = request only
- [ ] tool_use_id must match exactly

## **Task 8.2: MCP Architecture**
- [ ] Three primitives: Tools, Resources, Prompts
- [ ] stdio = local subprocess
- [ ] HTTP = remote server
- [ ] Transport: local vs remote, single vs multi-client
- [ ] Origin header validation (DNS defense)

## **Task 8.3: Types & Orchestration**
- [ ] Server tools (web_search) on Anthropic infra
- [ ] Client tools (custom) on your infra
- [ ] tool_choice: auto/any/specific
- [ ] Parallel calls all return results
- [ ] Stop reason drives loop

## **Task 8.4: Remote MCP**
- [ ] HTTP only, tools only
- [ ] Beta header required
- [ ] stdio can't be connected
- [ ] Resources/prompts need full client
- [ ] Trust servers, secure auth

---

## **Rapid-Fire Exam Questions**

1. **"How does Claude call the database?"**  
   → Claude doesn't. Returns tool_use, your code executes.

2. **"Local stdio server via MCP connector?"**  
   → No. Connector needs HTTP URL, stdio has no endpoint.

3. **"Missing is_error on tool failure?"**  
   → Claude treats error string as data, gives wrong answer.

4. **"20 devs accessing same MCP server?"**  
   → HTTP (centralized), not stdio (each local).

5. **"MCP connector accesses resources?"**  
   → No. Tools only. Need full client for resources.

6. **"Parallel calls: execute first one, skip others?"**  
   → Wrong. Return result for each (is_error: true for failed).

7. **"tool_choice: any with extended thinking?"**  
   → 400 error. Use auto instead.

8. **"Custom tool vs built-in web_search?"**  
   → Use web_search first. Custom only for business logic.

---

## **30-SECOND RECAP**

✅ Claude requests, code executes (safety boundary)  
✅ Always flag errors with is_error: true  
✅ Three primitives: Tools, Resources, Prompts  
✅ stdio = local, HTTP = remote  
✅ Parallel calls return all results (is_error for failed)  
✅ tool_choice: auto (default), any, or specific  
✅ MCP connector = HTTP only, tools only  
✅ Trust servers, secure auth  

---

## **Study Time Allocation**

| Task | Time | Priority |
|------|------|----------|
| Task 8.1 (Tool Impl) | 30% | 🔴 High |
| Task 8.2 (MCP Arch) | 30% | 🔴 High |
| Task 8.3 (Types) | 25% | 🔴 High |
| Task 8.4 (Remote) | 15% | 🟡 Medium |

---