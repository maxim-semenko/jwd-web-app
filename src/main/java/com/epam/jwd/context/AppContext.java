package com.epam.jwd.context;

import com.epam.jwd.context.config.AdminConfiguration;
import com.epam.jwd.context.config.DataBaseConfiguration;
import com.epam.jwd.context.config.EmailConfiguration;
import com.epam.jwd.entity.User;
import com.epam.jwd.pool.ConnectionPool;
import com.epam.jwd.service.UserService;
import com.epam.jwd.util.AdminPropertiesReaderUtil;
import com.epam.jwd.util.DataBasePropertiesReaderUtil;
import com.epam.jwd.util.EmailPropertiesReaderUtil;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that contains application initialization,
 * settings, the ability to set the project launch
 * {@link Type} (production or testing).
 */

@Log4j2
public class AppContext {

    private static AppContext instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    public static boolean isEnrolledList;
    private static Type type = Type.PRODUCTION;

    public enum Type {
        PRODUCTION,
        TEST;
    }

    public static AppContext getInstance() {
        if (!INSTANCE_CREATED.get()) {
            LOCK.lock();
            try {
                if (instance == null) {
                    instance = new AppContext();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    public void init() {
        initProperties();
        initConfigs();
        ConnectionPool.getInstance();
        User.COUNT_ID = UserService.getInstance().getMaxId();
        isEnrolledList = UserService.getInstance().getCountUserEnrolled() != 0;
    }

    public void initProperties() {
        log.info("start init properties");
        AdminPropertiesReaderUtil.loadProperties();
        DataBasePropertiesReaderUtil.loadProperties();
        EmailPropertiesReaderUtil.loadProperties();
    }

    public void initConfigs() {
        log.info("start init configs");
        AdminConfiguration.getInstance();
        DataBaseConfiguration.getInstance();
        EmailConfiguration.getInstance();
    }

    public static Type getType() {
        return type;
    }

    public static void setType(Type type) {
        AppContext.type = type;
    }
}