package com.nihith.breakdown.model.exceptions;

/**
 * Unchecked exception used to signal unexpected system-level or infrastructure
 * failures throughout the application (e.g. database errors, JSON processing errors).
 * Wraps the underlying cause and propagates it up the call stack.
 */
public class SystemException extends RuntimeException {

    /**
     * Constructs a {@code SystemException} with no detail message.
     */
    public SystemException() {
        super();
    }

    /**
     * Constructs a {@code SystemException} with the specified detail message.
     *
     * @param message a human-readable description of the error
     */
    public SystemException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code SystemException} with the specified detail message and cause.
     *
     * @param message a human-readable description of the error
     * @param cause   the underlying exception that triggered this exception
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a {@code SystemException} that wraps the given cause.
     * The detail message is derived from the cause.
     *
     * @param cause the underlying exception that triggered this exception
     */
    public SystemException(Throwable cause) {
        super(cause);
    }

    /**
     * Full-control constructor for subclasses that need to influence suppression
     * and stack-trace writability behaviour.
     *
     * @param message            a human-readable description of the error
     * @param cause              the underlying exception that triggered this exception
     * @param enableSuppression  whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     */
    protected SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
