package com.juvenxu.mvnbook.account.web;

import com.juvenxu.mvnbook.account.exception.AccountServiceException;
import com.juvenxu.mvnbook.account.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private ApplicationContext context;

    private static final long serialVersionUID = -4446963637833727907L;

    @Override
    public void init() throws ServletException {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        if (id == null || id.length() == 0 || password == null || password.length() == 0) {
            response.sendError(400, "incomplete parameter");
            return;
        }

        AccountService accountService = (AccountService) context.getBean("accountService");

        try {

            accountService.login(id, password);
            response.getWriter().print("Login Successful!");
        } catch (AccountServiceException e) {
            response.sendError(400, e.getMessage());
        }
    }
}
