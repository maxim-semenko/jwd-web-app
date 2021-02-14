package com.epam.jwd.entity;

import com.epam.jwd.exception.UnknownEnumFacultyException;

/**
 * Class enum - type of {@link Faculty}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public enum EnumFaculty {
    /**
     * Faculty of Computer Systems and Networks.
     */
    FCSN(1),
    /**
     * Faculty of Information Technologies and Controls
     */
    FITC(2),
    /**
     * Faculty of Computer-Aided Design.
     */
    FCAD(3),
    /**
     * Faculty of RadioEngineering and Electronics.
     */
    FRE(4);

    private final int id;

    EnumFaculty(final int id) {
        this.id = id;
    }

    /**
     * Method that gets {@link Integer} id.
     *
     * @return {@link Integer} id
     */
    public int getId() {
        return id;
    }

    public static EnumFaculty resolveById(int id) throws UnknownEnumFacultyException {
        switch (id) {
            case 1:
                return FCSN;
            case 2:
                return FITC;
            case 3:
                return FCAD;
            case 4:
                return FRE;
            default:
                throw new UnknownEnumFacultyException();
        }
    }
}
