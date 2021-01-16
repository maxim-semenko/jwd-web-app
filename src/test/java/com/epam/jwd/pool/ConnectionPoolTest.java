package com.epam.jwd.pool;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.listener.InitListener;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletContextEvent;
import java.sql.SQLException;
import static org.mockito.Mockito.mock;

public class ConnectionPoolTest {


    @Test
    public void getAvailableConnectionsTest() {
        InitListener initListener = new InitListener();
        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        initListener.contextInitialized(servletContextEvent);
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Assert.assertEquals(connectionPool.getAvailableConnections(), 10);
    }

    @Test
    public void DestroyTest() throws InterruptedException, SQLException {
        InitListener initListener = new InitListener();
        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        initListener.contextInitialized(servletContextEvent);
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
        Assert.assertTrue(connectionPool.getConnection().isClosed());
    }



}