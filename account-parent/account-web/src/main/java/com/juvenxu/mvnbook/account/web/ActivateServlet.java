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

public class ActivateServlet extends HttpServlet {

    private ApplicationContext context;

    private static final long serialVersionUID = -7783463550778895953L;

    @Override
    public void init() throws ServletException {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String key = request.getParameter("key");
        if (key == null || key.length() == 0) {
            response.sendError(400, "No activation key provided.");
            return;
        }

        AccountService accountService = (AccountService) context.getBean("accountService");

        try {
            accountService.activate(key);
            response.getWriter().write( "Account is activated, now you can login." );
        } catch (AccountServiceException e) {
            response.sendError(400, "Unable to activate account");
            return;
        }
    }
}
