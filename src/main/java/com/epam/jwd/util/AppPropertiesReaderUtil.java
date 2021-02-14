package com.epam.jwd.util;

import java.util.ResourceBundle;

/**
 * Class that contains app properties from file.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
public final class AppPropertiesReaderUtil {

    private AppPropertiesReaderUtil() {
    }
    /**
     * {@link ResourceBundle} that get properties from app.properties.
     */
    public final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("app");

}
