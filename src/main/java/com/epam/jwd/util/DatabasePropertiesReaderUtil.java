package com.epam.jwd.util;

import java.util.ResourceBundle;

/**
 * Database properties reader, class is responsible
 * for reading database config from properties file.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */


public final class DatabasePropertiesReaderUtil {

    /**
     * Class, which contain properties from file.
     */
    public static ResourceBundle resourceBundle;
    private static final String PATH_PROPERTIES = "dataBaseProperties";

    /**
     * Method, which read from file to properties value.
     */
    public static void loadProperties() {
        final String propertiesFileName = AppPropertiesReaderUtil.resourceBundle.getString(PATH_PROPERTIES);
        resourceBundle = ResourceBundle.getBundle(propertiesFileName);
    }

}
