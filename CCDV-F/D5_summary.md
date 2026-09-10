# CCDV-F Domain 5: Model Selection & Optimization — Revision Sheet
## Quick Reference | 16.8% Exam | ~10 Questions (SECOND HEAVIEST)

---

## **THE GOLDEN RULES**

1. ✅ **max_tokens caps OUTPUT only, not input.**
2. ✅ **Output costs 5× input — optimize output first.**
3. ✅ **Right-size → Cache → Batch (in that order).**
4. ✅ **Haiku = routing, Sonnet = default, Opus = reasoning.**
5. ✅ **Tune effort before switching models.**
6. ✅ **Cache: stable content first, dynamic last.**
7. ✅ **Thinking wastes tokens on simple tasks.**

---

## **CONCEPT 1: TOKEN FUNDAMENTALS**

### Context Window (Fixed Per Model)

| Model | Window | Max Output | Notes |
|-------|--------|-----------|-------|
| Fable 5 | 1M | 128K | Frontier |
| Opus 4.8 | 1M | 128K | Reasoning |
| Sonnet 5/4.6 | 1M | 128K | Default |
| **Haiku 4.5** | **200K** | **64K** | 🔴 Outlier |

**🔑 Haiku is the outlier** (exam loves this).

---

### The max_tokens Myth

❌ **MYTH**: Raising max_tokens expands context window  
✅ **REALITY**: max_tokens caps OUTPUT only, doesn't expand input capacity

**Exam trap**: "Context filling up" problem is never solved by increasing max_tokens.

---

### Output Costs 5× Input

| Model | Input | Output | Ratio |
|-------|-------|--------|-------|
| Haiku | $1 | $5 | 5× |
| Sonnet | $3 | $15 | 5× |
| Opus | $5 | $25 | 5× |
| Fable | $10 | $50 | 5× |

**🔑 Optimize output first** — it dominates the bill.

---

### Tokenizer Changes

New models (Opus 4.7+, Fable 5, Sonnet 5) produce **~30% more tokens** than older versions.

**Action**: Always recount tokens when migrating.

---

### Thinking Token Costs

- Bill at output rates (5× input)
- Consume context window
- Not universally better (waste on simple tasks)

---

## **Concept 1 Stress Points**

🚩 max_tokens ≠ context expansion  
🚩 Haiku context = 200K (not 1M)  
🚩 Output costs 5× input  
🚩 Recount tokens on version migrations  

---

---

## **CONCEPT 2: MODEL TIERS**

### Quick Decision Table

| Model | Best For | Speed | Cost |
|-------|----------|-------|------|
| **Haiku** | Routing, classification, extraction | Fastest | $1/$5 |
| **Sonnet** | Coding, writing, multi-step (default) | Fast | $3/$15 |
| **Opus** | Complex reasoning, agentic loops | Moderate | $5/$25 |
| **Fable** | Frontier, longest-running agents | Slowest | $10/$50 |

---

### 🔑 Right-Sizing Rule

Use smallest model that meets quality.

```
Wrong: Use Opus for classification (5× cost, slower)
Right: Use Haiku for classification (best fit)
```

---

### Haiku-Specific

| Aspect | Note |
|--------|------|
| Window | 200K (not 1M) |
| Max output | 64K (not 128K) |
| Thinking | Manual extended (not adaptive) |

---

### Tiered Routing Pattern

```
Haiku classifies
    ├─ Confidence > threshold → Answer with Haiku
    └─ Confidence < threshold → Escalate to Sonnet/Opus
```

Intelligent resource allocation.

---

### Effort Parameter (Before Switching)

| Level | Use |
|-------|-----|
| low | Simple lookups |
| medium | Standard tasks |
| high | Default, general-purpose |
| **xhigh** | **Coding/agentic (sweet spot on Opus)** |
| max | Research, complex |

**🔑 Tune effort first** before down-tiering.

---

## **Concept 2 Stress Points**

🚩 Right-size to task, don't default to biggest  
🚩 Haiku for routing/classification  
🚩 Sonnet is versatile default  
🚩 Tune effort before switching models  

---

---

## **CONCEPT 3: COST MANAGEMENT**

### Priority Order (Do In This Sequence)

```
1. Right-size model      → ~10× (biggest lever)
2. Prompt caching        → ~90% input
3. Batch API            → 50% both
4. Reduce output        → Output 5×
5. Context management   → Prune old results
```

---

### Cost Per Lever

| Lever | Savings | What It Attacks |
|-------|---------|-----------------|
| Right-size | Up to 10× | Both input + output |
| Caching | ~90% input | Input cost only |
| Batch | 50% | Both input + output |
| Output | Variable | Output (biggest per-token) |

---

### Prompt Caching Rules

| Rule | Detail |
|------|--------|
| Order | Stable first, dynamic last |
| Minimum | ≥1,024 tokens |
| Hit cost | ~10% of base |
| Write cost | ~1.25× base (one-time) |
| TTL | 5 min default (1-hour available) |
| **Invalidation** | **Any prefix change = cache miss** |

🔑 **Timestamp in system = cache broken every call**.

---

### Batch API Specs

| Spec | Value |
|------|-------|
| Discount | 50% |
| Max requests | 100,000 |
| Max size | 256 MB |
| Processing | Up to 24 hours |
| Results kept | 29 days |

**Use**: Overnight work, bulk extraction, evals  
**Don't use**: User-facing, pre-merge CI (too slow)

---

### Cache + Batch Stacking

Cache saves on input (90%).  
Batch saves on both (50%).  
Together: **Compounding savings**.

Example: 1000 API calls
- Batch alone: 50% off
- Caching alone: 90% off hits
- **Batch + Cache: ~75% off** (compounding)

---

## **Concept 3 Stress Points**

🚩 Right-size is #1 lever (biggest savings)  
🚩 Cache: stable first, dynamic last  
🚩 Batch: single-turn only, 50% off  
🚩 Output costs 5× — optimize first  
🚩 Cache + Batch stack  

---

---

## **CONCEPT 4: THINKING & DEPLOYMENT**

### Adaptive vs Extended

| Aspect | Adaptive | Extended |
|--------|----------|----------|
| Models | Opus 4.7+, Fable, Sonnet 5 | Haiku 4.5 and earlier |
| Control | Effort parameter | budget_tokens (manual) |
| Always on? | YES | NO (manual) |
| Cost | Output rates | Output rates |

---

### Adaptive Thinking (Modern)

Model decides how much to think internally.

Tune with **effort** parameter.

---

### Extended Thinking (Manual)

You set **budget_tokens** (min 1,024).

Claude uses that many tokens to think.

**For >32K budget: use Batch** (avoid timeout).

---

### 🔑 Thinking Isn't Always Better

- **Simple lookup**: Thinking wastes tokens
- **Complex reasoning**: Thinking helps quality
- **Rule**: Use thinking only when task justifies cost

---

### Deployment Platforms

| Platform | Auth | Notes |
|----------|------|-------|
| Claude API | API key | Direct to Anthropic |
| Bedrock | AWS creds | AWS integration |
| Vertex AI | GCP creds | GCP integration |
| Foundry | Azure creds | Azure integration |

**Same API shape, different auth.**

---

### Full Optimization Stack

```
1. Right-size model (Haiku/Sonnet/Opus)
2. Tune effort (xhigh for coding)
3. Cache static prefix (system, tools)
4. Batch non-urgent work
5. Prune verbose outputs
6. Request concise format (JSON, required fields)
```

Each lever multiplies the others.

---

## **Concept 4 Stress Points**

🚩 Adaptive = always on, tune effort  
🚩 Extended = manual, minimum 1024  
🚩 Thinking wastes on simple tasks  
🚩 Platforms: same API, different auth  

---

---

## **EXAM RED FLAGS**

🚩 **"Increase max_tokens to fit more context"**  
→ ❌ max_tokens caps output only

🚩 **"Haiku has 1M context like others"**  
→ ❌ Haiku outlier = 200K only

🚩 **"Use Opus for everything"**  
→ ❌ Right-size to task

🚩 **"Cache solves cost problems"**  
→ ❌ Right-size is #1 lever

🚩 **"Batch for user-facing chat"**  
→ ❌ Batch is async only

🚩 **"Thinking always improves quality"**  
→ ❌ Wastes tokens on simple tasks

🚩 **"Switch models instead of tuning effort"**  
→ ❌ Tune effort first

🚩 **"Dynamic content before cache breakpoint is fine"**  
→ ❌ Cache invalidates every call

---

---

## **QUICK DECISION TREES**

### Which Model?

```
Routing or classification?
├─ YES → Haiku
└─ NO:
    Coding or multi-step?
    ├─ YES → Sonnet (default)
    └─ NO:
        Deep reasoning?
        ├─ YES → Opus
        └─ NO → Sonnet
```

### Cost Too High?

```
1. Can I use smaller model? → YES → Right-size
2. Repeated queries? → YES → Cache
3. Async work? → YES → Batch
4. Output too verbose? → YES → Request concise
5. Context overfull? → YES → Prune old results
```

### Slow Performance?

```
Model too small?
├─ YES → Check Haiku limits (200K, 64K)
└─ NO:
    Effort too high?
    ├─ YES → Lower effort before switching
    └─ NO:
        Context too large?
        ├─ YES → Compact/prune
        └─ NO → Problem elsewhere
```

### Thinking Needed?

```
Simple lookup?
├─ YES → NO (wastes tokens)
└─ NO:
    Complex reasoning?
    ├─ YES → YES (adaptive or extended)
    └─ NO → Maybe (tune effort instead)
```

---

---

## **30-SECOND RECAP**

✅ max_tokens = output cap only  
✅ Output 5× input — optimize output first  
✅ Right-size → Cache → Batch (order)  
✅ Haiku 200K, Sonnet/Opus/Fable 1M  
✅ Effort before switching  
✅ Cache: stable first, dynamic last  
✅ Thinking only for complex  

---

---

## **STUDY TIME ALLOCATION**

| Concept | Time | Priority |
|---------|------|----------|
| Concept 3 (Cost) | 35% | 🔴 Very High |
| Concept 1 (Tokens) | 25% | 🔴 High |
| Concept 2 (Tiers) | 25% | 🔴 High |
| Concept 4 (Thinking) | 15% | 🟡 Medium |

---

---

## **QUICK LOOKUP**

### When to use?

| Scenario | Model/Action |
|----------|---|
| Routing task | Haiku |
| General coding | Sonnet |
| Deep reasoning | Opus |
| Frontier work | Fable |
| Context filling? | Compact/cache, not max_tokens |
| Cost high? | Right-size first |
| Slow? | Lower effort or check cache |
| Simple lookup + thinking? | NO (wastes tokens) |

### Cost savings stacking

- Right-size: up to 10×
- Cache: 90% (repeated)
- Batch: 50%
- Cache + Batch: ~75% (combined)

### Never do

- ❌ Raise max_tokens to expand context
- ❌ Use Opus for classification
- ❌ Put dynamic content before cache
- ❌ Batch for user-facing work
- ❌ Think on simple lookups
- ❌ Switch models before tuning effort

---