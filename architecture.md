# breakdown-model — Module Architecture

> For coding style rules, see `CLAUDE.md` in this directory.

---

## Module Role

This is the **shared library** used by all other modules in the expense system. It contains:
- Domain model POJOs (`model/transactions/`, `model/groups/`, `model/individuals/`)
- Response wrapper types (`model/response/`)
- DB service **interfaces** (`model/service/`) — implementations live in `breakdown-mongo-adapter`
- Shared constants (`model/constants/`)
- Utility classes (`model/util/`)
- Custom exception (`model/exceptions/`)

This module has **no Spring application context** — it is a plain JAR with no `@SpringBootApplication`. It does not use `@Autowired`, `@Component`, or `@Service`.

---

## Package Structure

```
com.nihith.breakdown.model
├── constants/
│   ├── MessageConstants.java         ← human-readable message strings
│   ├── FieldNameConstants.java       ← MongoDB field name strings
│   └── Operation.java                ← enum for CRUD operation names
├── exceptions/
│   └── SystemException.java
├── groups/
│   ├── Group.java
│   └── Family.java
├── individuals/
│   └── PaidFor.java
├── response/
│   ├── ResponseStructure.java
│   ├── ResponseMessages.java
│   ├── ResponseStatus.java           ← enum
│   └── MessageType.java              ← enum
├── service/
│   ├── GroupDBService.java           ← interface only
│   └── TransactionDBService.java     ← interface only
├── transactions/
│   ├── Transaction.java
│   ├── TransactionList.java
│   ├── TransactionType.java          ← enum
│   ├── TransactionStatus.java        ← enum
│   └── SplitType.java                ← enum
└── util/
    ├── EnvironmentUtil.java
    ├── ObjectMapperUtil.java
    └── ResponseStructureUtil.java
```
