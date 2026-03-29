---
description: "Use when writing or modifying any code in breakdown-model: domain POJOs, enums, constants classes, DB service interfaces, response model classes, utility classes, or exception classes. Covers manual getter/setter patterns, Jackson annotations, Bean Validation, constant organization, utility class design, and JavaDoc style."
applyTo: "breakdown-model/src/**/*.java"
---

# breakdown-model Coding Style

## Module Role

This is the **shared library** used by all other modules. It contains:
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

---

## Domain Model POJOs

### Annotations

Every domain POJO must have:
- `@JsonIgnoreProperties(ignoreUnknown = true)` at the class level — resilient JSON deserialization

Bean Validation (`jakarta.validation`) annotations on fields as appropriate:
- `@NotEmpty` for required String and Collection fields
- `@NotNull` for required objects and numeric fields
- `@DecimalMin(value = "0.0", inclusive = false)` for amounts

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    private static final long serialVersionUID = 1L;

    private String uuid;

    @NotEmpty
    private String transactionName;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    @NotEmpty
    private String paidById;

    @NotNull
    private List<PaidFor> paidForList;
```

### No Lombok

**Do not add Lombok.** Write all getters, setters, and `toString()` manually. No `@Data`, `@Builder`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`.

### Constructors

- Provide a no-arg constructor if needed
- When a no-arg constructor sets a generated field (like UUID), initialize it there:

  ```java
  public Transaction() {
      this.uuid = UUID.randomUUID().toString();
  }
  ```

- Provide focused parameterized constructors for common initialization patterns:

  ```java
  public TransactionList(String groupId) {
      this.groupId = groupId;
  }
  ```

### Getters and Setters

Standard JavaBean style — always use `this.field = field` in setters:

```java
public String getGroupId() {
    return groupId;
}

public void setGroupId(String groupId) {
    this.groupId = groupId;
}
```

### `toString()`

Concatenation format using `ClassName{field='value', ...}`:

```java
@Override
public String toString() {
    return "Transaction{" +
            "uuid='" + uuid + '\'' +
            ", transactionName='" + transactionName + '\'' +
            ", amount=" + amount +
            '}';
}
```

### No Spring Data MongoDB Annotations

Do **not** add `@Document`, `@Field`, or `@Id` to model classes. This module does not depend on Spring Data MongoDB. Document binding is done in `breakdown-mongo-adapter` via `ObjectMapperUtil` (Jackson serialization).

---

## Enums

Use enums for all closed-value sets. Enums live in the same domain sub-package as their model:

```java
// Simple value enum
public enum TransactionType { EXPENSE, SETTLEMENT }
public enum SplitType { EQUAL, SHARES, PERCENTAGE, AMOUNT }
public enum TransactionStatus { COMPLETE, INCOMPLETE }
public enum ResponseStatus { SUCCESS, FAILURE }
public enum MessageType { INFORMATION, WARNING, ERROR }
```

For enums that need a display label, add a field + constructor + getter:

```java
public enum Operation {
    CREATE("Create"), MODIFY("Modify"), DELETE("Delete");

    private final String displayName;

    Operation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
```

---

## Constants Classes

Constants live in **plain classes** (not interfaces) with `public static final` fields.

- Group logically related constants in one class (`MessageConstants` for messages, `FieldNameConstants` for MongoDB field names)
- Use `/* Comment */` block comments to section constants within a class
- Constants that are only relevant to one class go on that class as `private static final`

```java
public class MessageConstants {

    /* Error Messages */
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    /* Success Messages */
    public static final String TRANSACTION_INSERT_SUCCESS = "Successfully Inserted Transaction";
    public static final String TRANSACTION_DELETE_SUCCESS = "Successfully Deleted Transaction";
    public static final String TRANSACTION_LIST_FETCH_SUCCESS = "Successfully Fetched Transaction List";
    public static final String GROUP_CREATION_FAILURE = "Group Creation Failed";
    public static final String GROUP_CREATION_SUCCESS = "Group Created Successfully";
}
```

```java
public class FieldNameConstants {
    public static final String TRANSACTION_ID = "uuid";
    public static final String GROUP_ID = "groupId";
    public static final String EXPENSE_TYPE = "transactionType";
}
```

---

## DB Service Interfaces

Service interfaces define the database contract that infrastructure modules must implement.

- Methods throw `SystemException` for infrastructure failures
- Use `{@code true}` / `{@code false}` in return JavaDoc
- Full `@param`, `@return`, `@throws` on every method

```java
/**
 * Service interface that defines the database operations for managing transactions
 * and settlements. Implementations provide the persistence layer for a specific
 * database technology (e.g. MongoDB).
 */
public interface TransactionDBService {

    /**
     * Persists a new transaction record in the data store.
     *
     * @param transaction the transaction to insert
     * @return {@code true} if the insertion was successful, {@code false} otherwise
     */
    public boolean insertTransaction(Transaction transaction);

    /**
     * Retrieves only the {@link TransactionType#EXPENSE} transactions for the specified group.
     *
     * @param groupId the unique identifier of the group
     * @return a list of expense transactions; empty list if none found
     */
    public List<Transaction> fetchExpenseList(String groupId);
}
```

---

## Utility Classes

Utility classes are **static-only** — no Spring annotation, no instantiation:

- All methods are `public static` (or `private static` for helpers)
- Logger declared as `public static final Logger logger = LogManager.getLogger(ClassName.class)` (Log4j2)
- `ObjectMapper` declared as `public static final ObjectMapper objectMapper = new ObjectMapper()`

```java
/**
 * Utility class providing static helper methods for converting between Java POJOs
 * and MongoDB {@link Document} objects using Jackson's {@link ObjectMapper}.
 */
public class ObjectMapperUtil {

    public static final Logger logger = LogManager.getLogger(ObjectMapperUtil.class);
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static Document castToDocument(Object object) { ... }

    public static <T> T castToObject(Document document, Class<T> objectClass) { ... }
}
```

`EnvironmentUtil` is the single access point for all env var / system property resolution:

```java
public static String getEnvironmentVariable(String key) {
    return Optional.ofNullable(System.getProperty(key)).orElse(System.getenv(key));
}
```

- JVM system property takes priority over OS environment variable
- All other modules call `EnvironmentUtil.getEnvironmentVariable(KEY)` — never call `System.getenv()` or `System.getProperty()` directly elsewhere

---

## Response Model

### `ResponseStructure`

Universal response wrapper. The `payload` field is `Object` (generic — holds any domain object or `null`):

```java
public class ResponseStructure {
    private ResponseStatus responseStatus;  // SUCCESS or FAILURE
    private ResponseMessages messages;
    private Object payload;
}
```

### `ResponseMessages`

Groups messages by severity. Fields are `List<String>` (not `List<SomeMessageObject>`):

```java
public class ResponseMessages {
    List<String> informationMessages;
    List<String> warningMessages;
    List<String> errorMessages;
}
```

All responses are built via `ResponseStructureUtil.generateResponseStructure(...)` — never construct `ResponseStructure` or `ResponseMessages` directly in business code.

---

## SystemException

`SystemException` is the single custom exception for all infrastructure and system errors. It must mirror all `RuntimeException` constructor overloads:

```java
public class SystemException extends RuntimeException {
    public SystemException() { super(); }
    public SystemException(String message) { super(message); }
    public SystemException(String message, Throwable cause) { super(message, cause); }
    public SystemException(Throwable cause) { super(cause); }
    protected SystemException(String message, Throwable cause,
                               boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
```

---

## JavaDoc

JavaDoc is required on all public interfaces, utility classes, exception classes, and their methods. Plain POJO getters/setters do not require JavaDoc.

- **Class-level**: Single sentence to multi-sentence prose. No tags.
- **Methods**: Full `@param`, `@return`, `@throws`. Use `{@link}`, `{@code}`, `{@value}` inline.
- Generic type parameters documented with `@param <T>`
- No `@author`, `@version`, `@since`

```java
/**
 * Deserialises a MongoDB {@link Document} into an instance of the specified class.
 *
 * @param <T>         the target type
 * @param document    the source MongoDB document
 * @param objectClass the class to deserialise into
 * @return a populated instance of {@code T}
 * @throws SystemException if Jackson fails to deserialise the document
 */
public static <T> T castToObject(Document document, Class<T> objectClass) { ... }
```

---

## Tests

### Framework

JUnit 5 (`@ExtendWith(MockitoExtension.class)`) + Mockito. Test classes in `src/test/java` under a sub-package mirroring the main package:

```
src/test/java/com/nihith/breakdown/test/model/util/ResponseStructureUtilTest.java
```

### Test Naming

`methodName_condition_expectedOutcome`:

```
generateResponseStructure_Success
generateResponseStructure_Failure
getEnvironmentVariable_ReturnsSystemProperty
getEnvironmentVariable_FallsBackToEnvVar
castToObject_ValidDocument_ReturnsObject
```

### Static Mocking

Use `MockedStatic` in try-with-resources for mocking `System.getProperty` / `System.getenv` in `EnvironmentUtil` tests:

```java
try (MockedStatic<EnvironmentUtil> mockedStatic = Mockito.mockStatic(EnvironmentUtil.class)) {
    mockedStatic.when(() -> EnvironmentUtil.getEnvironmentVariable("KEY")).thenReturn("value");
    // test
}
```
