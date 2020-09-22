package com.cortex.core.handler.exception;

public class HandlerExceptionNotFound extends Exception {

    private static final long serialVersionUID = 8166985933091458318L;

    public HandlerExceptionNotFound() {
        super();
    }

    public HandlerExceptionNotFound(final String message) {
        super(message);
    }
}
