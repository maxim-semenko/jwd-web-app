package com.epam.jwd.listener;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.pool.ConnectionPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletContextEvent;

import static org.mockito.Mockito.mock;

public class InitListenerTest {

    static InitListener initListener;
    static ServletContextEvent servletContextEvent;

    @BeforeClass
    public static void setUp() throws Exception {
        AppContext.setType(AppContext.Type.TEST);
        initListener = new InitListener();
        servletContextEvent = mock(ServletContextEvent.class);
        initListener.contextInitialized(servletContextEvent);
    }

    @Test
    public void testContextInitialized() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Assert.assertEquals(connectionPool.getAvailableConnections(), 10);
    }

    @After
    public void tearDown() throws Exception {
        initListener.contextDestroyed(servletContextEvent);
    }
}