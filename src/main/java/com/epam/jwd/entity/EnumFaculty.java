package com.epam.jwd.entity;

import com.epam.jwd.exception.UnknownEnumFacultyException;

/**
 * Class enum - type of {@link Faculty}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */

public enum EnumFaculty {
    FCSN(1), // Faculty of Computer Systems and Networks.
    FITC(2), // Faculty of Information Technologies and Controls.
    FCAD(3), // Faculty of Computer-Aided Design.
    FRE(4);   // Faculty of RadioEngineering and Electronics.

    private final int id;

    EnumFaculty(int id) {
        this.id = id;
    }

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
