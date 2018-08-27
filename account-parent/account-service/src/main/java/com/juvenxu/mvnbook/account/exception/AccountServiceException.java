package com.juvenxu.mvnbook.account.exception;

public class AccountServiceException extends Exception {

    public AccountServiceException(String message) {
        super(message);
    }

    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
