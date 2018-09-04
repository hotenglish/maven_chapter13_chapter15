package com.juvenxu.mvnbook.account.account.email;

import com.juvenxu.mvnbook.account.exception.AccountEmailException;

public interface AccountEmailService {

    void sendMail(String to,String subject, String htmlText) throws AccountEmailException;
}
