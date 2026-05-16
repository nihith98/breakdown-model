package com.nihith.breakdown.test.model.transactions;

import com.nihith.breakdown.model.transactions.Transaction;
import com.nihith.breakdown.model.transactions.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Transaction model class.
 * Verifies proper handling of transaction amounts, especially null values
 * which can occur during Jackson deserialization from MongoDB.
 */
public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
    }

    @Test
    void setAmount_withValidBigDecimal_shouldScaleToTwoDecimals() {
        // Arrange
        BigDecimal inputAmount = new BigDecimal("10.5");

        // Act
        transaction.setAmount(inputAmount);

        // Assert
        assertNotNull(transaction.getAmount());
        assertEquals(new BigDecimal("10.50"), transaction.getAmount());
        assertEquals(2, transaction.getAmount().scale());
    }

    @Test
    void setAmount_withPrecisionBigDecimal_shouldRoundHalfUp() {
        // Arrange
        BigDecimal inputAmount = new BigDecimal("10.555");

        // Act
        transaction.setAmount(inputAmount);

        // Assert
        assertNotNull(transaction.getAmount());
        assertEquals(new BigDecimal("10.56"), transaction.getAmount());
        assertEquals(2, transaction.getAmount().scale());
    }

    @Test
    void setAmount_withNullBigDecimal_shouldSetFieldToNull() {
        // Arrange
        transaction.setAmount(new BigDecimal("100.00")); // Set a value first
        assertNotNull(transaction.getAmount());

        // Act - Set to null (simulates Jackson deserialization with null amount)
        transaction.setAmount(null);

        // Assert - Should not throw NullPointerException
        assertNull(transaction.getAmount());
    }

    @Test
    void setAmount_withNullValue_shouldNotThrowException() {
        // Arrange & Act - Should not throw NullPointerException when setting null
        assertDoesNotThrow(() -> transaction.setAmount(null));

        // Assert
        assertNull(transaction.getAmount());
    }

    @Test
    void setAmount_withZeroBigDecimal_shouldScaleToTwoDecimals() {
        // Arrange
        BigDecimal inputAmount = BigDecimal.ZERO;

        // Act
        transaction.setAmount(inputAmount);

        // Assert
        assertNotNull(transaction.getAmount());
        assertEquals(new BigDecimal("0.00"), transaction.getAmount());
        assertEquals(2, transaction.getAmount().scale());
    }

    @Test
    void setAmount_withHighPrecisionBigDecimal_shouldScale() {
        // Arrange
        BigDecimal inputAmount = new BigDecimal("123.456789");

        // Act
        transaction.setAmount(inputAmount);

        // Assert
        assertNotNull(transaction.getAmount());
        assertEquals(new BigDecimal("123.46"), transaction.getAmount());
        assertEquals(2, transaction.getAmount().scale());
    }

    @Test
    void transaction_shouldBeSerializable() {
        // Arrange
        transaction.setUuid("test-uuid-123");
        transaction.setTransactionName("Dinner");
        transaction.setTransactionType(TransactionType.EXPENSE);
        transaction.setAmount(new BigDecimal("50.00"));
        transaction.setPaidById("alice");
        transaction.setGroupId("group-001");

        // Act
        String transactionString = transaction.toString();

        // Assert
        assertNotNull(transactionString);
        assertTrue(transactionString.contains("test-uuid-123"));
        assertTrue(transactionString.contains("Dinner"));
        assertTrue(transactionString.contains("50.00"));
    }

    @Test
    void transaction_withNullAmount_shouldHandleSerialization() {
        // Arrange
        transaction.setUuid("test-uuid-456");
        transaction.setTransactionName("Settlement");
        transaction.setTransactionType(TransactionType.SETTLEMENT);
        transaction.setAmount(null);
        transaction.setPaidById("bob");
        transaction.setGroupId("group-002");

        // Act
        String transactionString = transaction.toString();

        // Assert
        assertNotNull(transactionString);
        assertTrue(transactionString.contains("test-uuid-456"));
        assertTrue(transactionString.contains("Settlement"));
    }

    @Test
    void setAmount_multipleTimesWithNullAndValue_shouldHandleCorrectly() {
        // Arrange & Act
        transaction.setAmount(new BigDecimal("25.50"));
        assertEquals(new BigDecimal("25.50"), transaction.getAmount());

        transaction.setAmount(null);
        assertNull(transaction.getAmount());

        transaction.setAmount(new BigDecimal("75.00"));
        assertEquals(new BigDecimal("75.00"), transaction.getAmount());

        transaction.setAmount(null);
        assertNull(transaction.getAmount());

        // Assert - All transitions should work without exceptions
        assertTrue(true);
    }
}
