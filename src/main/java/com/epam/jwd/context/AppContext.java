package com.epam.jwd.context;

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

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public void init() {
        initProperties();
        User.COUNT_ID = UserService.getInstance().getMaxId();
        isEnrolledList = UserService.getInstance().getCountUserEnrolled() != 0;
    }

    public void initProperties() {
        log.info("start init properties");
        AdminPropertiesReaderUtil.loadProperties();
        DataBasePropertiesReaderUtil.loadProperties();
        EmailPropertiesReaderUtil.loadProperties();
        ConnectionPool.getInstance();
    }


}