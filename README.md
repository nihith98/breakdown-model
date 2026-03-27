# breakdown-model

Shared data model library for the Breakdown expense-splitting platform. This module defines every domain object, enum, service interface, and response contract used across all other Breakdown modules. It has no business logic of its own — it is a pure contract/model JAR that all other modules depend on.

---

## Module Coordinates

| Property    | Value                             |
|-------------|-----------------------------------|
| groupId     | `com.nihith`                      |
| artifactId  | `breakdown-model`                 |
| version     | `0.0.1`                           |
| packaging   | `jar`                             |
| Java        | 21                                |
| Spring Boot | 3.4.3 (for JPA + Validation only) |

---

## Domain Overview

Breakdown is a REST API backend for an expense-management and bill-splitting tool, inspired by Splitwise. Users are organised into **Groups**. Inside a group, **Transactions** (expenses or settlements) are recorded. Each expense tracks who paid and how much each participant owes. The platform automatically computes an optimised **settlement list** so that the minimum number of transfers clears all debts.

### The Family Feature

Breakdown extends this model with a **Family** concept. A Family is a named sub-group within an expense group. When a settlement is computed, any members belonging to the same Family are treated as one collective entity — their individual balances are merged, and the family as a whole settles with others rather than each member settling separately.

**Example:** Group has members A, B, and C. A pays $6, split equally (each person owes $2). B and C belong to Family **F**. Instead of generating two settlement records (B → A: $2 and C → A: $2), the engine generates a single record: **F → A: $4**.

---

## Package Structure

```
com.nihith.breakdown.model
├── constants/
│   ├── FieldNameConstants.java      # MongoDB / JSON field name string constants
│   └── MessageConstants.java        # Human-readable API response message strings
├── exceptions/
│   └── SystemException.java         # Unchecked runtime exception for system errors
├── groups/
│   ├── Group.java                   # Expense group with members and families
│   └── Family.java                  # Sub-group (family) within an expense group
├── individuals/
│   └── PaidFor.java                 # Represents one participant's share in an expense
├── response/
│   ├── MessageType.java             # Enum: INFORMATION | WARNING | ERROR
│   ├── ResponseMessages.java        # Typed response message bean
│   ├── ResponseStatus.java          # Enum: SUCCESS | FAILURE
│   └── ResponseStructure.java       # Generic API response wrapper
├── service/
│   ├── GroupDBService.java          # Interface: CRUD operations for Group entities
│   └── TransactionDBService.java    # Interface: CRUD + query operations for Transactions
├── transactions/
│   ├── SplitType.java               # Enum: how an expense is divided
│   ├── Transaction.java             # Core transaction / settlement record
│   ├── TransactionList.java         # Container: expense list + settlement list for a group
│   ├── TransactionStatus.java       # Enum: COMPLETE | INCOMPLETE
│   └── TransactionType.java         # Enum: EXPENSE | SETTLEMENT
└── util/
    ├── EnvironmentUtil.java          # Reads environment variables (e.g. PREFERRED_DB)
    ├── ObjectMapperUtil.java         # Jackson helpers — deserialise / cast to BSON Document
    └── ResponseStructureUtil.java    # Factory: builds ResponseStructure from result + metadata
```

---

## Key Models

### `Transaction`

The central domain object. Represents either an **expense** (someone paid for others) or a **settlement** (repayment between two parties).

| Field                  | Type                  | Validation          | Description                                                  |
|------------------------|-----------------------|---------------------|--------------------------------------------------------------|
| `uuid`                 | `String`              | auto-generated      | UUID v4, set in the default constructor                      |
| `transactionName`      | `String`              | `@NotEmpty`         | Short label for the expense (e.g. "Dinner")                  |
| `transactionDescription` | `String`            | optional            | Longer description                                           |
| `transactionType`      | `TransactionType`     | optional            | `EXPENSE` or `SETTLEMENT`                                    |
| `amount`               | `BigDecimal`          | `@NotNull`, `> 0`   | Total value of the transaction                               |
| `paidById`             | `String`              | `@NotEmpty`         | ID of the person (or family) who paid                        |
| `paidForList`          | `List<PaidFor>`       | `@NotNull`          | Each participant's share of this transaction                 |
| `splitType`            | `SplitType`           | optional            | How the expense was divided (see below)                      |
| `timestamp`            | `Date`                | optional            | When the transaction occurred                                |
| `groupId`              | `String`              | `@NotEmpty`         | The expense group this transaction belongs to                |
| `transactionStatus`    | `TransactionStatus`   | optional            | `COMPLETE` or `INCOMPLETE`                                   |

### `PaidFor`

Represents one participant's share within a transaction.

| Field          | Type     | Validation  | Description                                              |
|----------------|----------|-------------|----------------------------------------------------------|
| `paidForId`    | `String` | `@NotEmpty` | ID of the person (or family) receiving this portion      |
| `paidForValue` | `double` |             | The monetary value of this person's share                |

### `Group`

An expense group containing participants and optional family sub-groups.

| Field        | Type             | Description                                     |
|--------------|------------------|-------------------------------------------------|
| `groupId`    | `String`         | Unique identifier for the group                 |
| `groupName`  | `String`         | Human-readable name                             |
| `personList` | `List<String>`   | IDs of all individual members                   |
| `familyList` | `List<Family>`   | Optional family sub-groups within this group    |

### `Family`

A named sub-group. Members of a family share expenses collectively during settlement computation.

| Field        | Type             | Description                                        |
|--------------|------------------|----------------------------------------------------|
| `familyId`   | `String`         | Unique identifier for the family                   |
| `familyName` | `String`         | Human-readable name (e.g. "The Smiths")            |
| `personIds`  | `List<String>`   | IDs of the individual members within this family   |

---

## Enums

### `SplitType`
Controls how an expense total is divided across `paidForList` entries.

| Value        | Meaning                                                            |
|--------------|--------------------------------------------------------------------|
| `EQUAL`      | Divide the total equally among all participants                    |
| `SHARES`     | Each participant is assigned a share ratio (e.g. 2 shares vs 1)   |
| `PERCENTAGE` | Each participant is assigned a percentage of the total             |
| `AMOUNT`     | Each participant is assigned an explicit monetary amount           |

### `TransactionType`

| Value        | Meaning                                |
|--------------|----------------------------------------|
| `EXPENSE`    | Someone paid on behalf of the group    |
| `SETTLEMENT` | A repayment between two parties        |

### `TransactionStatus`

| Value        | Meaning                                             |
|--------------|-----------------------------------------------------|
| `COMPLETE`   | The transaction has been fully settled              |
| `INCOMPLETE` | The transaction still has an outstanding balance    |

---

## Service Interfaces

### `TransactionDBService`

Implemented by database adapters (e.g. `breakdown-mongo-adapter`). Contract for all transaction persistence operations:

| Method                                                        | Description                                                      |
|---------------------------------------------------------------|------------------------------------------------------------------|
| `insertTransaction(Transaction)`                              | Persist a new expense transaction                                |
| `deleteTransaction(String transactionId)`                     | Remove an expense by UUID                                        |
| `fetchTransaction(String groupId)`                            | Retrieve all transactions (expenses + settlements) for a group   |
| `fetchExpenseList(String groupId)`                            | Retrieve only `EXPENSE` type records for a group                 |
| `fetchSettlementList(String groupId)`                         | Retrieve only `SETTLEMENT` type records for a group              |
| `updateSettlementList(String groupId, List<Transaction>)`     | Atomically replace all settlements for a group                   |

### `GroupDBService`

Implemented by database adapters. Contract for group and family persistence operations.

---

## Response Contract

All API responses are wrapped in `ResponseStructure`. The `ResponseStructureUtil` factory produces these consistently.

```json
{
  "data": { ... },
  "responseStatus": "SUCCESS",
  "messages": [
    { "messageType": "INFORMATION", "message": "Transaction inserted successfully" }
  ]
}
```

---

## Build

```bash
cd breakdown-model
./mvnw clean install
```

This installs the JAR into the local Maven repository so that dependent modules (`breakdown-mongo-adapter`, `calculation-engine`, `breakdown-dashboard`) can resolve it.

---

## Dependencies

| Dependency                            | Purpose                                      |
|---------------------------------------|----------------------------------------------|
| `spring-boot-starter-data-jpa`        | JPA annotations (`@Entity`, etc.) on models  |
| `spring-boot-starter-validation`      | Bean validation (`@NotNull`, `@NotEmpty`)     |
| `jackson-databind`                    | JSON serialisation / deserialisation         |
| `org.mongodb:bson`                    | BSON `Document` type used by ObjectMapperUtil|
| `commons-lang3`                       | Utility helpers                              |
| `h2`                                  | In-memory DB (runtime, for JPA bootstrapping)|

