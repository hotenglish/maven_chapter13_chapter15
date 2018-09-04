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
import java.io.OutputStream;

public class CaptchaImageServlet extends HttpServlet {

    private ApplicationContext context;

    private static final long serialVersionUID = 8654459574928762167L;

    @Override
    public void init() throws ServletException {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        if (key == null || key.length() == 0) {
            response.sendError(400, "No Captcha Key Found");
        } else {
            AccountService service = (AccountService) context.getBean("accountService");
            try {
                response.setContentType("image/jpeg");
                OutputStream out = response.getOutputStream();
                out.write(service.generateCaptchaImage(key));
                out.close();
            } catch (AccountServiceException e) {
                response.sendError(404, e.getMessage());
            }
        }
    }
}
