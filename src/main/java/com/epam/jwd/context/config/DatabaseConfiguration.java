package com.epam.jwd.context.config;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.util.DatabasePropertiesReaderUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class DatabaseConfiguration, which
 * stores configuration and allows to take values.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

@Getter
@ToString
@Log4j2
public final class DatabaseConfiguration {

    private static DatabaseConfiguration instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String NAME = "name";
    private static final String TEST_NAME = "testname";
    private static final String DRIVER = "driver";
    private static final String JDBC = "jdbc";
    private static final String POOL_SIZE = "poolsize";

    private String login;
    private String password;
    private String host;
    private String port;
    private String name;
    private String driver;
    private String jdbc;
    private int poolSize;


    public static DatabaseConfiguration getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = init();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    /**
     * Method performs initialization configurations of data base.
     *
     * @return {@link DatabaseConfiguration} singleton class object
     */
    private static DatabaseConfiguration init() {
        instance = new DatabaseConfiguration();

        log.info("Start database configuration");
        instance.login = DatabasePropertiesReaderUtil.resourceBundle.getString(LOGIN);
        instance.password = DatabasePropertiesReaderUtil.resourceBundle.getString(PASSWORD);
        instance.host = DatabasePropertiesReaderUtil.resourceBundle.getString(HOST);
        instance.port = DatabasePropertiesReaderUtil.resourceBundle.getString(PORT);

        instance.name = AppContext.getType() ==
                AppContext.Type.PRODUCTION ?
                DatabasePropertiesReaderUtil.resourceBundle.getString(NAME) :
                DatabasePropertiesReaderUtil.resourceBundle.getString(TEST_NAME);


        instance.driver = DatabasePropertiesReaderUtil.resourceBundle.getString(DRIVER);
        instance.jdbc = DatabasePropertiesReaderUtil.resourceBundle.getString(JDBC);
        instance.poolSize = Integer.parseInt(DatabasePropertiesReaderUtil.resourceBundle.getString(POOL_SIZE));

        return instance;
    }


    /**
     * Method that build and return JDBC url.
     *
     * @return {@link String} JDBC url
     */
    public String getJdbcUrl() {
        return jdbc + host + ":" + port + "/" + name;
    }


}
