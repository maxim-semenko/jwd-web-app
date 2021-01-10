package com.epam.jwd.pool;

import com.epam.jwd.context.AppContext;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class ConnectionPoolTest {


    @Test
    public void getAvailableConnectionsTest() {
        AppContext.getInstance().init();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Assert.assertEquals(connectionPool.getAvailableConnections(), 10);
    }

    @Test
    public void DestroyTest() throws InterruptedException, SQLException {
        AppContext.getInstance().init();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
        Assert.assertTrue(connectionPool.getConnection().isClosed());
    }
}