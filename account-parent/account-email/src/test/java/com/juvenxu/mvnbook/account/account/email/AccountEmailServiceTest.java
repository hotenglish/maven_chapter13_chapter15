package com.juvenxu.mvnbook.account.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

import static junit.framework.Assert.assertEquals;

public class AccountEmailServiceTest {

    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception {
        greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.setUser("hotenglish@163.com", "sin90=1");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
        String subject = "Test Subject";
        String htmlText = "<h3>Test</h3>";
        accountEmailService.sendMail("hotenglish@163.com", subject, htmlText);
        greenMail.waitForIncomingEmail(2000, 1);
        Message[] msg = greenMail.getReceivedMessages();
        assertEquals(1, msg.length);
        assertEquals(subject, msg[0].getSubject());
        assertEquals(htmlText, GreenMailUtil.getBody(msg[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception {
        greenMail.stop();
    }
}
