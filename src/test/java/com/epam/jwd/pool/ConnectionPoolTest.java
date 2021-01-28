package com.epam.jwd.pool;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.listener.InitListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContextEvent;
import java.sql.SQLException;
import static org.mockito.Mockito.mock;

public class ConnectionPoolTest {

    @Before
    public void setUp() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
    }

    @Test
    public void getAvailableConnectionsTest() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Assert.assertEquals(connectionPool.getAvailableConnections(), 10);
    }

}