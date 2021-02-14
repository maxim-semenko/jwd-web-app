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
     * {@link ResourceBundle} contain properties.
     */
    public static ResourceBundle resourceBundle;
    /**
     * {@link String} needed name property from app.properties.
     */
    private static final String PATH_PROPERTIES = "databasePropertiesFile";

    private DatabasePropertiesReaderUtil() {
    }
    /**
     * Method that reads from file to properties value.
     */
    public static void loadProperties() {
        final String propertiesFileName = AppPropertiesReaderUtil.RESOURCE_BUNDLE.getString(PATH_PROPERTIES);
        resourceBundle = ResourceBundle.getBundle(propertiesFileName);
    }

}
