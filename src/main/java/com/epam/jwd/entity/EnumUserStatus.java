package com.epam.jwd.entity;

/**
 * Class enum, which assigns the status to the {@link User}.
 *
 * @version 0.0.1
 */

public enum EnumUserStatus {
    UNKNOWN(1),
    ENROLLED(2),
    NO_ENROLLED(3);

    private final int id;

    EnumUserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EnumUserStatus resolveById(int id) {
        switch (id) {
            case 1:
                return UNKNOWN;
            case 2:
                return ENROLLED;
            case 3:
                return NO_ENROLLED;
            default:
                return null;
        }
    }
}
