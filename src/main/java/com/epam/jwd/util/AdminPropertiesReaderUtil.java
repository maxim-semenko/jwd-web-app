package com.epam.jwd.util;

import java.util.ResourceBundle;

/**
 * Admin properties reader, class is responsible
 * for reading admin config from properties file.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class AdminPropertiesReaderUtil {

    public static ResourceBundle resourceBundle;
    private static final String PATH_PROPERTIES = "adminProperties";

    private AdminPropertiesReaderUtil() {
    }

    /**
     * Method, which read from file to properties value.
     */
    public static void loadProperties() {
        final String propertiesFileName = AppPropertiesReaderUtil.resourceBundle.getString(PATH_PROPERTIES);
        resourceBundle = ResourceBundle.getBundle(propertiesFileName);
    }

}
