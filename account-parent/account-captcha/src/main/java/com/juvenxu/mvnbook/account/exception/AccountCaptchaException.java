package com.juvenxu.mvnbook.account.exception;

public class AccountCaptchaException extends Exception {

    public AccountCaptchaException(String message) {
        super(message);
    }

    public AccountCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
