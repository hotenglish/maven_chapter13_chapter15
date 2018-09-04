package com.juvenxu.mvnbook.account.persist;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

import static org.junit.Assert.*;

public class AccountPersistServiceTest {

    private AccountPersistService service;

    @Before
    public void prepare() throws Exception {
        File persistDataFile = new File("target/test-classes/persist-data.xml");
        if (persistDataFile.exists()) {
            persistDataFile.delete();
        }

        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-persist.xml");
        service = (AccountPersistService) ctx.getBean("accountPersistService");
        Account account = new Account();
        account.setId("1");
        account.setName("Maven");
        account.setEmail("Maven@changeme.com");
        account.setPassword("this_should_be_encrypted");
        account.setActivated(true);
        service.createAccount(account);
    }

    @Test
    public void testReadAccount() throws Exception {
        Account account = service.readAccount("1");
        assertNotNull(account);
        assertEquals("1", account.getId());
        assertEquals("Maven", account.getName());
        assertEquals("Maven@changeme.com", account.getEmail());
        assertTrue(account.isActivated());
    }
}
