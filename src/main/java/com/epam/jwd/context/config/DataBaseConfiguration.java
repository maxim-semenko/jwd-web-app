package com.epam.jwd.context.config;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.util.DataBasePropertiesReaderUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class DatabaseConfiguration, which
 * stores configuration and allows to take values.
 *
 * @version 0.0.1
 */

@Getter
@ToString
@Log4j2
public final class DataBaseConfiguration {

    private static DataBaseConfiguration instance;
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


    public static DataBaseConfiguration getInstance() {
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
     * @return {@link DataBaseConfiguration} singleton class object
     */
    private static DataBaseConfiguration init() {
        instance = new DataBaseConfiguration();

        log.info("Start database configuration");
        instance.login = DataBasePropertiesReaderUtil.resourceBundle.getString(LOGIN);
        instance.password = DataBasePropertiesReaderUtil.resourceBundle.getString(PASSWORD);
        instance.host = DataBasePropertiesReaderUtil.resourceBundle.getString(HOST);
        instance.port = DataBasePropertiesReaderUtil.resourceBundle.getString(PORT);

        instance.name = AppContext.getType() ==
                AppContext.Type.PRODUCTION ?
                DataBasePropertiesReaderUtil.resourceBundle.getString(NAME) :
                DataBasePropertiesReaderUtil.resourceBundle.getString(TEST_NAME);

        System.out.println("NAME = " + instance.name);

        instance.driver = DataBasePropertiesReaderUtil.resourceBundle.getString(DRIVER);
        instance.jdbc = DataBasePropertiesReaderUtil.resourceBundle.getString(JDBC);
        instance.poolSize = Integer.parseInt(DataBasePropertiesReaderUtil.resourceBundle.getString(POOL_SIZE));

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
