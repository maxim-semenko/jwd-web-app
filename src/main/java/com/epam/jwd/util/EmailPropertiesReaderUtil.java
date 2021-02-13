package com.epam.jwd.util;

import java.util.ResourceBundle;

/**
 * Email properties reader, class is responsible
 * for reading email config from properties file.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public class EmailPropertiesReaderUtil {

    public static ResourceBundle resourceBundle;
    private static final String PATH_PROPERTIES = "emailProperties";

    /**
     * Method, which read from file to properties value.
     */
    public static void loadProperties() {
        final String propertiesFileName = AppPropertiesReaderUtil.resourceBundle.getString(PATH_PROPERTIES);
        resourceBundle = ResourceBundle.getBundle(propertiesFileName);
    }
}
