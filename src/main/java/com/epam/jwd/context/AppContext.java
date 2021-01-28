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

@Log4j2
public class AppContext {

    private static AppContext instance;
    public static boolean isEnrolledList;
    private static Type type = Type.PRODUCTION;

    public enum Type {
        PRODUCTION,
        TEST;
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public void init() {
        initProperties();
        AdminConfiguration.getInstance();
        DataBaseConfiguration.getInstance();
        ConnectionPool.getInstance();
        EmailConfiguration.getInstance();
        User.COUNT_ID = UserService.getInstance().getMaxId();
        isEnrolledList = UserService.getInstance().getCountUserEnrolled() != 0;
    }

    public void initProperties() {
        log.info("start init properties");
        AdminPropertiesReaderUtil.loadProperties();
        DataBasePropertiesReaderUtil.loadProperties();
        EmailPropertiesReaderUtil.loadProperties();
    }

    public static Type getType() {
        return type;
    }

    public static void setType(Type type) {
        AppContext.type = type;
    }
}