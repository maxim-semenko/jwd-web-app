package com.epam.jwd.pool;

import com.epam.jwd.context.config.DataBaseConfiguration;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConnectionPool class, which store all connection.
 *
 * @version 0.0.1
 */

@Log4j2
public final class ConnectionPool {

    private static ConnectionPool instance;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);

    /**
     * The queue of connections.
     */
    private BlockingQueue<Connection> connectionQueue;
    /**
     * The instance of DataBaseConfiguration.
     */
    private static final DataBaseConfiguration dataBaseConfig = DataBaseConfiguration.getInstance();


    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = init();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Method create queue of pool connections.
     */
    private static ConnectionPool init() {
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.connectionQueue = new ArrayBlockingQueue<>(dataBaseConfig.getPoolSize());
        registerDriver();

        log.info("StartUp of Connection pool");
        for (int i = 0; i < 10; i++) {
            connectionPool.connectionQueue.add(createConnection());
        }
        log.info("The connection pool was created[size: " + connectionPool.connectionQueue.size() + "]");
        return connectionPool;
    }

    /**
     * Method gets a new connection from DriverManager.
     *
     * @return a new created connection
     */
    private static Connection createConnection() {
        try {
            log.info("DataBase connection created");
            return DriverManager.getConnection(
                    dataBaseConfig.getJdbcUrl(),
                    dataBaseConfig.getLogin(),
                    dataBaseConfig.getPassword());
        } catch (SQLException e) {
            log.error("Can't create database connection", e);
            throw new RuntimeException("Database connection failed");
        }
    }

    /**
     * Method give an available connection.
     *
     * @return available connection
     * @throws InterruptedException connection interrupted
     */
    public Connection getConnection() throws InterruptedException {
        return connectionQueue.take();
    }

    /**
     * Method returns taken connection after sql execute.
     *
     * @param connection connection, which finished sql execute
     */
    public void releaseConnection(final Connection connection) {
        connectionQueue.add(connection);
    }

    /**
     * Method returns the size of available connection in pool.
     *
     * @return {@link Integer} size of available connection in pool
     */
    public Integer getAvailableConnections() {
        return connectionQueue.size();
    }


    /**
     * Method, which register db Driver.
     */
    private static void registerDriver() {
        try {
            Class.forName(dataBaseConfig.getDriver());
        } catch (ClassNotFoundException e) {
            log.fatal("Can't register Driver dataBase ", e);
            throw new RuntimeException("Database driver connection failed", e);
        }
    }

    /**
     * Method, which destroy connection pool.
     */
    public void destroy() {
        if (instanceCreated.get()) {
            lock.lock();
            try {
                for (Connection connection : connectionQueue) {
                    connection.close();
                }
                instance = null;
                instanceCreated.set(false);
            } catch (SQLException e) {
                log.error("Can't destroy connection pool ", e);
            } finally {
                lock.unlock();
            }
        }
        log.info("Connection pool are destroyed");
    }

}
