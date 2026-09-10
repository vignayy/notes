# CCDV-F Domain 5: Model Selection & Optimization — Complete Study Guide
## ~10 Questions | 16.8% of Exam (SECOND HEAVIEST)

---

## **OVERVIEW: Four Interconnected Concepts**

| Concept | Focus | Key Principle | Exam Weight |
|---------|-------|---------------|------------|
| 1 | Tokens & Context | max_tokens caps OUTPUT only | High |
| 2 | Model Tiers | Right-size to task | High |
| 3 | Cost Management | Priority order matters | High |
| 4 | Thinking & Deployment | Effort before switching | Medium |

---

---

# **CONCEPT 1: TOKEN FUNDAMENTALS**

## **The Context Window**

The **context window** is the total capacity for a single API call.

```
Context Window = System + Messages + Tools + Images + Output + Thinking
                └────────────────────────────────────────────────────┘
                       All compete for the SAME total capacity
```

Everything inside the window eats from the same budget. There's no separate pool for input vs output.

---

## **Context Window Sizes by Model**

| Model | Context Window | Max Output | Notes |
|-------|---|---|---|
| **Fable 5** | 1M tokens | 128K | Frontier agentic |
| **Opus 4.8** | 1M tokens | 128K | Deep reasoning |
| **Sonnet 5 / 4.6** | 1M tokens | 128K | Versatile default |
| **Haiku 4.5** | 200K tokens | 64K | 🔴 OUTLIER (smaller) |

### 🔑 Critical Exam Fact

**Haiku is the outlier**:
- Only 200K context window (not 1M)
- Only 64K max output (not 128K)
- Still uses manual extended thinking (not adaptive)

The exam tests these Haiku-specific limits heavily.

---

## **The max_tokens Misconception**

### ❌ **Common Myth**

"Raising max_tokens from 1024 to 4096 gives the model more room for conversation history."

### ✓ **Reality**

```
max_tokens = OUTPUT CAP ONLY
           ≠ Context window size
           ≠ Input capacity
```

**max_tokens does NOT expand the context window.**

The window is **fixed by the model** (1M or 200K). Raising max_tokens just allows Claude to write a longer response.

---

## **The Exam Trap**

**Scenario**: "Context is filling up. Options: A) Increase max_tokens, B) Prune old messages, C) Use caching."

The answer is **B or C** (both valid), never **A**.

**Why**: max_tokens controls answer length, not input capacity. When context fills up, you need to manage **input**, not output.

---

## **Token Counting**

### What Consumes Tokens

Everything:
- System prompt
- Messages
- Tool definitions
- Images (converted to tokens)
- PDFs (converted to tokens)
- Output
- Thinking blocks

### Counting Tokens

```python
response = client.messages.count_tokens(
    model="claude-opus-4-8",
    system="You are a scientist",
    messages=[{"role":"user","content":"Hello, Claude"}]
)
# {"input_tokens": 14} — free to call
```

**Important**: Token counting is **free to call** but **rate-limited by tier**.

---

## **Tokenizer Changes Across Versions**

### ⚠️ **Critical for the Exam**

Newer models (Opus 4.7+, Fable 5, Sonnet 5) use a tokenizer that produces **~30% more tokens per piece of text**.

**Example**:
- Sonnet 4.6: 10,000 tokens
- Sonnet 5: 13,000 tokens (same content, new tokenizer)

**When migrating between versions, always recount your tokens.** A prompt that fit comfortably before may now exceed limits.

---

## **Output Costs 5× Input**

### **The Cost Ratio by Model**

| Model | Input /MTok | Output /MTok | Ratio |
|-------|---|---|---|
| **Haiku 4.5** | $1 | $5 | 5× |
| **Sonnet 4.6** | $3 | $15 | 5× |
| **Opus 4.8** | $5 | $25 | 5× |
| **Fable 5** | $10 | $50 | 5× |

### 🔑 **Optimize Output First**

Because output costs 5× input per token, a long answer dominates the bill faster than a long prompt.

**Optimizations (priority order)**:
1. Concise responses (trim verbose explanations)
2. Structured fields (required-only, no extras)
3. Avoid verbose explanations when user asked for data
4. Request JSON/bullet points instead of prose

---

## **Thinking Token Costs**

Thinking tokens are billed at **output rates** (5× input) and **consume context window capacity**.

```
Extended thinking budget: 10,000 tokens
Cost: 10,000 × $25/MTok = $0.25 on Opus
Window consumed: 10,000 tokens (toward 1M limit)
```

**Budget for them on complex tasks.** Thinking isn't universally better — for simple lookups it wastes tokens.

---

## **Concept 1 Key Takeaways**

✅ Context window is fixed per model  
✅ max_tokens caps OUTPUT only, not input  
✅ Everything competes for same window  
✅ Haiku: 200K window (outlier)  
✅ Output costs 5× input  
✅ Recount tokens when migrating versions  
✅ Thinking tokens bill at output rates  

---

---

# **CONCEPT 2: MODEL TIERS**

## **The Four Tiers**

| Tier | Best For | Speed | Cost | Window |
|------|----------|-------|------|--------|
| **Haiku 4.5** | Classification, routing, simple extraction, high-volume | Fastest | $1/$5 | 200K |
| **Sonnet 4.6/5** | Versatile default: coding, writing, analysis, multi-step | Fast | $3/$15 | 1M |
| **Opus 4.8** | Complex agentic coding, deep reasoning, enterprise | Moderate | $5/$25 | 1M |
| **Fable 5** | Longest-running agents, frontier research | Slower | $10/$50 | 1M |

---

## **🔑 The Core Rule: Right-Size**

Use the **smallest model that meets quality requirements**.

```
"Using Opus on a task Haiku could handle
 costs you tokens for no gain
 AND slows you down."
```

Right-sizing is both a **cost AND speed win**.

---

## **Haiku 4.5: The Workhorse**

### **Sweet Spot Tasks**

✅ Classification ("Is this positive or negative?")  
✅ Routing ("Which department should handle this?")  
✅ Simple extraction ("Extract email from text")  
✅ High-volume batch processing (cost-sensitive)  

### **Not For**

❌ Complex reasoning  
❌ Multi-step agent loops  
❌ Coding tasks  

### **Haiku-Specific Notes**

- 200K context (smaller than others)
- 64K max output (smaller)
- Manual extended thinking (not adaptive)

---

## **Sonnet 4.6/5: The Default**

### **Sweet Spot Tasks**

✅ General-purpose coding  
✅ Writing and analysis  
✅ Multi-step workflows  
✅ Balanced speed/cost  

### **Why It's Default**

Best speed/quality/cost ratio for most tasks.

---

## **Opus 4.8: The Powerhouse**

### **Sweet Spot Tasks**

✅ Complex agentic coding (long-running agents)  
✅ Deep reasoning (math, logic, analysis)  
✅ Enterprise-critical accuracy  
✅ Frontier capabilities needed  

### **Cost Impact**

Expensive. Use only when Sonnet isn't sufficient.

---

## **Fable 5: The Frontier**

### **Sweet Spot Tasks**

✅ Longest-running agents (multi-hour tasks)  
✅ Frontier research (pushing boundaries)  
✅ When Opus isn't enough  

### **Cost Impact**

Most expensive. Use only when bleeding edge needed.

---

## **Right-Sizing Strategies**

### **Strategy 1: Start Cheap (Recommended)**

```
Haiku → Test performance → Upgrade only if capability gap
```

**When to use**: Prototyping, cost-sensitive, high-volume simple tasks

**Advantage**: Start cheap, upgrade only when needed

### **Strategy 2: Start Capable**

```
Opus → Optimize prompts → Down-tier or lower effort later
```

**When to use**: Complex reasoning where accuracy outweighs cost

**Advantage**: Get best results, then optimize

---

## **The Tiered Routing Pattern**

This is a **production-grade pattern** the exam tests:

```
Haiku classifies request
    ↓
If confidence > threshold:
    ├─ Easy task → Return Haiku answer
    └─ Hard task → Escalate to Sonnet/Opus
```

**Benefits**:
- Speed of Haiku for easy cases
- Quality of Opus for hard cases
- Intelligent resource allocation

**Reference**: `anthropics/claude-cookbooks` has the exact implementation.

---

## **Effort Parameter: Tune Before Switching**

### **The Exam Principle**

```
Tuning effort within a model often beats
switching models entirely
because you keep the same quality ceiling.
```

### **Example Scenario**

**Problem**: "Opus is too slow and expensive for coding"

**Wrong answer**: Switch to Sonnet  
**Right answer**: Lower the `effort` parameter on Opus first

On Opus 4.8, `xhigh` is the sweet spot for coding/agentic work — faster and cheaper than default `high` while maintaining quality.

---

## **Concept 2 Key Takeaways**

✅ Right-size to task (smallest model that works)  
✅ Haiku = routing/classification  
✅ Sonnet = versatile default  
✅ Opus = deep reasoning  
✅ Fable = frontier/long-running  
✅ Tune effort before switching models  
✅ Tiered routing = intelligent allocation  

---

---

# **CONCEPT 3: COST MANAGEMENT**

## **The Priority Order**

This is **the #1 cost question** on the exam.

### **Optimization Priority (Do In This Order)**

```
1. Right-size the model        → Up to 10× savings
2. Prompt caching             → ~90% input savings
3. Batch API                  → 50% both input + output
4. Reduce output              → Output costs 5×
5. Context management         → Prune/compact
```

---

## **Lever 1: Right-Size the Model**

| Action | Savings |
|--------|---------|
| Haiku instead of Opus | 5× on both input + output |
| Haiku instead of Sonnet | 3× |
| Sonnet instead of Opus | ~1.7× |

**Example**: Routing task using Opus → switch to Haiku = 5× cost reduction.

---

## **Lever 2: Prompt Caching**

### **The Deal**

- Cache hit: ~10% of base input cost
- Break-even: ~2 requests
- Minimum to cache: 1,024 tokens
- TTL: Default 5 min (1-hour available)

### **How It Works**

```
Call 1: System (2000 tokens) cached with 25% premium
Call 2: System (2000 tokens) read from cache (90% off!)
Call 3: System (2000 tokens) read from cache (90% off!)
...
```

### **🔑 Critical Placement Rule**

**Stable content FIRST, dynamic content LAST.**

```
✅ CORRECT ORDER:
system=[
    {"text": "You are agent", "cache_control": {...}},
    {"text": "1000 examples", "cache_control": {...}}
]
messages=[
    {"role": "user", "content": "Help with X"}
]

❌ WRONG ORDER:
system=[
    {"text": f"Time: {now()}", "cache_control": {...}},  # Changes every call
    {"text": "Examples", "cache_control": {...}}
]
# Cache invalidates every call
```

### **Invalidation Rule**

Any change to the cached prefix — even inserting a new example BEFORE it — **invalidates the entire cache**.

---

## **Lever 3: Batch API**

### **The Deal**

- 50% cost reduction (both input + output)
- Up to 100,000 requests per batch
- Up to 24 hours processing
- Results retained 29 days
- Single-turn requests only

### **When to Use Batch**

✅ Overnight reports  
✅ Bulk extraction  
✅ Large eval suites  
✅ Non-urgent, cost-matters work  

### **When NOT to Use Batch**

❌ User-facing chat (user is waiting)  
❌ Pre-merge CI (blocking developer)  
❌ Anything time-sensitive  

---

## **Lever 4: Reduce Output**

### **Why It Matters**

Output costs **5× input**. A long answer dominates the bill fast.

### **Optimizations**

- Request JSON instead of prose
- Bullet points instead of paragraphs
- Required fields only (no extras)
- Concise summaries (no fluff)

---

## **Lever 5: Context Management**

### **The Problem**

Agent's context is 85% full with old tool results from 10 turns ago.

### ❌ **Wrong Approach**

Switch to a bigger model or raise max_tokens.

### ✅ **Right Approach**

Clear/compact old results. This:
1. Frees the window
2. Cuts per-turn input cost (old payloads still bill every turn)

---

## **Cost Stacking: Cache + Batch**

### **How They Combine**

Cache saves on **input cost** (90% off repeated prefix).  
Batch saves on **both input + output** (50% off).

**Combined**: 
- First batch request: Shared prefix cached + batch discount
- Subsequent requests: Cache hit (90%) + batch discount (50%) = compounding savings

### **Example**

```
Scenario: 1000 API calls with same system prompt, different messages

Without optimization: 1000 × $15 = $15,000

With Batch alone: 1000 × $7.50 = $7,500 (50% off)

With Caching alone: 
- Call 1: $15 × 1.25 = $18.75 (cache write premium)
- Calls 2-1000: $15 × 0.10 = $1.50 each
- Total: $18.75 + (999 × $1.50) = $1,517.25 (90% off on hits)

With Batch + Caching: ~$750 (50% off batch + 90% off repeated prefix)
```

---

## **Never Sacrifice Correctness for Cost**

### **The Rule**

Unless the scenario **explicitly allows it**, never choose a cheaper option that reduces accuracy.

Quality first, then optimize cost within that quality tier.

---

## **The 85% Context Scenario (Case Study)**

**Situation**: Agent context is 85% full with old tool results

**Colleague suggests**: "Switch to a larger model"

**Correct answer**: Clear/compact old tool results

**Why**: 
- Frees window space
- Reduces per-turn input cost (old payloads still bill every turn)
- Cheaper than switching models
- Faster response (smaller context)

---

## **Concept 3 Key Takeaways**

✅ Right-size → Cache → Batch (in order)  
✅ Output costs 5× input  
✅ Cache: stable first, dynamic last  
✅ Batch: 50% off, single-turn only  
✅ Cache + Batch stack for compounding savings  
✅ Prune context instead of upgrading models  
✅ Never sacrifice correctness for cost  

---

---

# **CONCEPT 4: THINKING & DEPLOYMENT**

## **Adaptive vs Extended Thinking**

### **The Difference**

| Aspect | Adaptive | Extended |
|--------|----------|----------|
| **Models** | Opus 4.7+, Fable, Sonnet 5 | Haiku 4.5 and earlier |
| **How** | Always on, model decides | Manual, you set budget |
| **Control** | Effort parameter | budget_tokens |
| **Minimum** | N/A | 1,024 tokens |
| **Cost** | Output rates (5× input) | Output rates (5× input) |

---

## **Adaptive Thinking (Modern)**

**Always on** — the model decides internally how much to think before answering.

Steer with **effort parameter**:

| Level | Behavior | Use Case |
|-------|----------|----------|
| **low** | Minimal reasoning, fastest | Simple lookups, routing |
| **medium** | Balanced speed/quality | Standard tasks |
| **high** | Default on Opus 4.8 | General-purpose |
| **xhigh** | Deep reasoning, slower | Coding, agentic work (sweet spot) |
| **max** | Maximum depth | Research, complex analysis |

---

## **Extended Thinking (Manual)**

**Manual** — you set a **budget_tokens** (minimum 1,024).

Claude uses exactly that many tokens to think before answering.

### **When to Use**

- Haiku 4.5 (no adaptive)
- When you need to guarantee minimum thinking
- Budget-controlled complex tasks

### **Cost Impact**

```
Thinking budget: 10,000 tokens
Cost: 10,000 × $25/MTok = $0.25 (Opus)
Context consumed: 10,000 tokens (toward 1M window)
```

### **High Budget Handling**

For budgets >32K tokens, use **Batch API** to avoid timeouts.

---

## **Key Principle: Thinking Isn't Universal**

**Thinking wastes tokens on simple tasks.**

```
Simple lookup task:
- Thinking: Wastes 1000+ tokens on unnecessary reasoning
- No thinking: Instant answer, cheap

Complex coding task:
- Thinking: Needed, improves output quality
- No thinking: Shallow answer, errors missed
```

**The exam tests**: "Use thinking only when the task complexity justifies it."

---

## **Deployment Platforms**

Claude runs on multiple platforms. The **Messages API shape is identical** — only auth and feature availability differ.

### **Platform Options**

| Platform | Auth | Notes |
|----------|------|-------|
| **Claude API (direct)** | API key | Full features, direct to Anthropic |
| **Amazon Bedrock** | AWS credentials (SigV4) | Same API shape, AWS integration |
| **Google Vertex AI** | GCP credentials | Same API shape, GCP integration |
| **Microsoft Foundry** | Azure credentials | Same API shape, Azure integration |

### **🔑 Same Shape, Different Auth**

Pick the platform matching your cloud/compliance — not for a different API.

**Request/response shape is identical.** Feature availability may differ.

---

## **The Optimization Mindset**

The cheapest, fastest correct answer wins.

### **Full Optimization Stack**

```
1. Right-size the model
   └─ Haiku for routing, Sonnet as default

2. Tune effort within the tier
   └─ Opus with effort=xhigh for coding

3. Cache the static prefix
   └─ System prompt, tools, documents

4. Batch non-urgent work
   └─ Reports, evals, bulk extraction

5. Prune verbose outputs
   └─ Clear old tool results

6. Request concise output
   └─ JSON > prose, required fields only
```

Each lever multiplies the others' effect.

---

## **Concept 4 Key Takeaways**

✅ Adaptive thinking = always on, tune effort  
✅ Extended thinking = manual, minimum 1024 tokens  
✅ Thinking isn't always better (wastes on simple tasks)  
✅ Thinking tokens bill at output rates (5×)  
✅ Budget >32K = use Batch to avoid timeout  
✅ Platforms: same API shape, different auth  
✅ Full stack: right-size → effort → cache → batch → prune  

---

---

# **DOMAIN 5 FINAL EXAM CHECKLIST** ✅

## **Concept 1: Token Fundamentals**
- [ ] Context window fixed per model
- [ ] max_tokens caps OUTPUT only
- [ ] Haiku: 200K window (outlier)
- [ ] Output costs 5× input
- [ ] Recount tokens when migrating versions
- [ ] Thinking tokens bill at output rates

## **Concept 2: Model Tiers**
- [ ] Haiku = routing/classification
- [ ] Sonnet = versatile default
- [ ] Opus = complex reasoning
- [ ] Fable = frontier/long-running
- [ ] Right-size to task
- [ ] Tune effort before switching

## **Concept 3: Cost Management**
- [ ] Right-size → Cache → Batch (order)
- [ ] Output 5× input
- [ ] Cache: 90% off on hits
- [ ] Batch: 50% off, single-turn only
- [ ] Cache + Batch stack
- [ ] Never sacrifice correctness

## **Concept 4: Thinking & Deployment**
- [ ] Adaptive: always on, tune effort
- [ ] Extended: manual, minimum 1024 tokens
- [ ] Thinking isn't always better
- [ ] Budget >32K = use Batch
- [ ] Platforms: same API, different auth

---

## **Rapid-Fire Exam Questions You'll See**

1. **"We need bigger context window. Should we raise max_tokens?"**  
   → No. max_tokens caps OUTPUT only. Use caching/batching to manage input.

2. **"Haiku context window is?"**  
   → 200K (not 1M — outlier).

3. **"Cost reduction: right-size vs caching vs batch — which first?"**  
   → Right-size (biggest savings), then cache, then batch.

4. **"Output tokens cost 5× input. What optimizes fastest?"**  
   → Reduce output (request JSON instead of prose).

5. **"Prompt changed, cache invalidated. Why?"**  
   → Change to cached prefix invalidates entire cache.

6. **"Opus too slow for coding. Switch to Sonnet?"**  
   → Try lowering effort parameter first. xhigh on Opus beats Sonnet at coding.

7. **"Classification task using Opus?"**  
   → Wrong. Haiku is designed for this (5× cheaper).

8. **"Tiered routing with Haiku → escalate if needed?"**  
   → Yes. Haiku for easy cases, Sonnet/Opus for hard. Intelligent allocation.

9. **"Thinking tokens on simple lookup?"**  
   → Wastes tokens. Use thinking only on complex reasoning tasks.

10. **"Cache + Batch together?"**  
    → Yes. Stacking gives compounding savings (90% + 50%).

---

## **Key Phrases to Recognize**

🟡 **Token cues**: "context window", "max_tokens", "output costs 5×", "thinking tokens"

🟡 **Tier cues**: "right-size", "routing", "classification", "coding", "agentic"

🟡 **Cost cues**: "priority order", "cache placement", "batch window", "stacking"

🟡 **Thinking cues**: "adaptive", "extended", "effort parameter", "thinking isn't better"

---

## **30-SECOND RECAP**

✅ max_tokens = output cap only, not input  
✅ Output costs 5× input (optimize output first)  
✅ Right-size → Cache → Batch (in order)  
✅ Haiku = routing, Sonnet = default, Opus = reasoning  
✅ Tune effort before switching models  
✅ Cache: stable first, dynamic last  
✅ Thinking only for complex tasks  
✅ Platforms: same API, different auth  

---

## **Study Time Allocation**

| Concept | Time | Priority |
|---------|------|----------|
| Concept 1 (Tokens) | 25% | 🔴 High |
| Concept 2 (Tiers) | 25% | 🔴 High |
| Concept 3 (Cost) | 35% | 🔴 Very High |
| Concept 4 (Thinking) | 15% | 🟡 Medium |

---