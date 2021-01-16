package com.epam.jwd.listener;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Log4j2
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Init app.
     *
     * @param sce {@link ServletContextListener}
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("App init");
        AppContext.getInstance().init();
    }

    /**
     * Destroy app.
     *
     * @param sce {@link ServletContextListener}
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
        log.info("Connection pool destroy");
    }
}
