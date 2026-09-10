# CCDV-F Domain 8: Tools & MCPs — Revision Sheet
## Quick Reference | 10.6% Exam | ~6 Questions (FINAL DOMAIN)

---

## **THE GOLDEN RULES**

1. ✅ **Claude requests, your code executes (not the other way).**
2. ✅ **Always flag tool errors with `is_error: true`.**
3. ✅ **Three MCP primitives: Tools, Resources, Prompts.**
4. ✅ **stdio = local subprocess, HTTP = remote server.**
5. ✅ **Parallel calls: return results for ALL (is_error for failed).**
6. ✅ **MCP connector: HTTP only, tools only, beta header required.**
7. ✅ **Built-in server tools first, custom tools only for business logic.**

---

## **TASK 8.1: TOOL IMPLEMENTATION**

### The Five-Step Round Trip

```
1. Define tool with name, description, input_schema
2. Claude analyzes and decides to call
3. Claude returns tool_use block (requests, doesn't execute)
4. Your code executes and returns result
5. Claude uses result to answer
```

---

### Claude Never Executes

❌ **WRONG**: "Claude calls the database"  
✅ **CORRECT**: "Claude requests tool call, your code calls database"

This separation is the **safety boundary**.

---

### Tool Definition Rules

| Field | Rule |
|-------|------|
| name | Regex: `^[a-zA-Z0-9_-]{1,64}$` (no spaces) |
| description | Detailed + when to use vs NOT use |
| input_schema | Request schema only (not response) |

---

### is_error Flag

❌ **WRONG**: 
```json
{"content": "Error: City not found"}
```
Claude treats as data.

✅ **CORRECT**:
```json
{"is_error": true, "content": "Error: City not found"}
```
Claude knows to retry or report.

---

## **Task 8.1 Stress Points**

🚩 Claude requests, code executes (not vice versa)  
🚩 Always set is_error: true on failures  
🚩 Detailed descriptions prevent misselection  
🚩 input_schema = request only  

---

---

## **TASK 8.2: MCP ARCHITECTURE**

### Three Primitives

| Primitive | What | Example |
|-----------|------|---------|
| **Tools** | Actions | create_issue, search_docs |
| **Resources** | Data | file contents, API responses |
| **Prompts** | Templates | code_review, summarise |

---

### Two Transports

| Transport | When | Example |
|-----------|------|---------|
| **stdio** | Local dev, one person | Subprocess on same machine |
| **HTTP** | Remote, multi-client, prod | Streamable HTTP or SSE |

---

### Transport Decision

| Scenario | Transport | Why |
|----------|-----------|-----|
| Local dev, filesystem tools | stdio | Subprocess |
| 20 devs sharing server | HTTP | Centralized URL |
| Production service | HTTP | Multi-client |
| Cloud third-party | HTTP | Only option |

---

### Key Facts

- stdio launches subprocess locally
- HTTP server runs on remote host
- HTTP servers MUST validate Origin header (DNS defense)
- MCP tools appear as `mcp__server__tool` in hooks

---

## **Task 8.2 Stress Points**

🚩 stdio = local subprocess (not HTTP)  
🚩 HTTP = remote, centralized  
🚩 Transport depends on local vs multi-client  
🚩 Origin header validation (security)  

---

---

## **TASK 8.3: TYPES & ORCHESTRATION**

### Server vs Client Tools

| Type | Runs Where | Examples |
|------|-----------|----------|
| **Server** | Anthropic infra | web_search, code_execution |
| **Client** | Your code | Custom functions, bash |

---

### Four Ways to Extend

1. Built-in tools (Read, Edit, Bash)
2. Custom tools (your functions)
3. Skills (local SKILL.md)
4. MCP tools (remote servers)

---

### tool_choice Options

| Value | Behavior |
|-------|----------|
| **auto** | Claude decides (tool or text) |
| **any** | Must call some tool |
| **specific** | Must call that tool only |

---

### Parallel Calls

✅ **Rules**:
- Claude may return multiple tool_use blocks
- Execute all (parallel or sequential)
- Return result for each
- Match by tool_use_id

❌ **Trap**: Skipping a call breaks structure

---

### Forced tool_choice Incompatibility

❌ **DON'T**: `tool_choice: "any"` + extended thinking = 400 error

✅ **DO**: `tool_choice: "auto"` + extended thinking

---

### Loop: stop_reason Drives Everything

```
while response.stop_reason != "end_turn":
    execute tools and continue
```

Don't parse text to decide loop flow — use stop_reason.

---

## **Task 8.3 Stress Points**

🚩 Server tools run on Anthropic, client on your code  
🚩 Parallel calls: return ALL results  
🚩 tool_choice: auto (default)  
🚩 Stop reason drives loop  
🚩 Don't use forced tool_choice with extended thinking  

---

---

## **TASK 8.4: REMOTE MCP**

### The MCP Connector

Connects to remote MCP servers directly from Messages API.

```python
mcp_servers=[{
    "type": "url",
    "url": "https://example.com/mcp",
    "name": "my_tools"
}]
```

---

### Four Constraints

1. **HTTP only** (Streamable HTTP or SSE)
2. **Tools only** (no resources or prompts)
3. **Beta header** (`mcp-client-2025-11-20`)
4. **API only** (not Bedrock/Vertex)

---

### What's Supported

| Feature | Support |
|---------|---------|
| Tool calls | ✅ |
| Resources | ❌ |
| Prompts | ❌ |
| stdio servers | ❌ |
| Bedrock/Vertex | ❌ |
| Auth | ✅ |

---

### Local vs Remote

**Local stdio**:
- Client launches subprocess
- No HTTP endpoint
- Can't be connected via connector

**Remote HTTP**:
- Server runs on network
- HTTP endpoint available
- Connector-compatible

---

### Resources/Prompts?

MCP connector = **tools only**.

To access resources/prompts: Use **full MCP client** on your infrastructure.

---

### Security

Remote servers not endorsed by Anthropic.

**Only connect to servers you trust.**

Your data flows through that server.

---

### Auth

Pass headers:

```python
mcp_servers=[{
    "type": "url",
    "url": "https://...",
    "headers": {
        "Authorization": "Bearer token",
        "X-API-Key": "key"
    }
}]
```

---

## **Task 8.4 Stress Points**

🚩 HTTP only, tools only, beta header  
🚩 stdio can't be connected  
🚩 Resources need full client  
🚩 Trust servers, secure auth  

---

---

## **EXAM RED FLAGS**

🚩 **"Claude calls the database"**  
→ ❌ Claude requests, code executes

🚩 **"Input_schema defines outputs"**  
→ ❌ Request only, not response

🚩 **"stdio server via MCP connector"**  
→ ❌ Connector needs HTTP URL

🚩 **"Skip parallel call, return others"**  
→ ❌ Return all (is_error: true for failed)

🚩 **"tool_choice: any with thinking"**  
→ ❌ 400 error, use auto

🚩 **"MCP connector accesses resources"**  
→ ❌ Tools only, need full client

🚩 **"Custom tool instead of web_search"**  
→ ❌ Built-in server tools first

---

---

## **QUICK DECISION TREES**

### Tool failing?

```
Always set is_error: true?
├─ YES → Claude knows to retry/report
└─ NO → Treated as data (wrong answer)
```

### Local dev or shared server?

```
One person?
├─ YES → stdio
└─ NO → HTTP
```

### Access resources from remote server?

```
Via MCP connector?
├─ NO (tools only)
└─ Need full MCP client
```

### Multiple tool calls?

```
Execute all?
├─ YES (parallel or sequential)
└─ Return result for each (is_error for failed)
```

### Loop control?

```
Drive by stop_reason?
├─ YES (tool_use → execute, end_turn → stop)
└─ Parse text (wrong)
```

---

---

## **30-SECOND RECAP**

✅ Claude requests, code executes  
✅ Always is_error: true on failures  
✅ Detailed tool descriptions  
✅ stdio = local, HTTP = remote  
✅ Parallel calls: return all results  
✅ tool_choice: auto (default)  
✅ Stop reason drives loop  
✅ MCP connector: HTTP, tools only, beta  

---

---

## **STUDY TIME ALLOCATION**

| Task | Time | Priority |
|------|------|----------|
| Task 8.1 (Tool Impl) | 30% | 🔴 High |
| Task 8.2 (MCP Arch) | 30% | 🔴 High |
| Task 8.3 (Types) | 25% | 🔴 High |
| Task 8.4 (Remote) | 15% | 🟡 Medium |

---

---

## **QUICK LOOKUP**

### Key Concepts

**Round trip**: Define → Request → Execute → Result

**Safety boundary**: Claude requests, code runs (not reversed)

**Three primitives**: Tools (do), Resources (read), Prompts (template)

**Two transports**: stdio (local), HTTP (remote)

**Parallel**: Execute all, return all (is_error for failed)

**Connector**: HTTP only, tools only, beta header

### Never Do

- ❌ Parse text to drive loop (use stop_reason)
- ❌ Skip parallel call (return all)
- ❌ Use forced tool_choice with thinking
- ❌ Connect stdio via HTTP
- ❌ Miss is_error flag
- ❌ Trust untrusted servers
- ❌ Expect resources via connector

---