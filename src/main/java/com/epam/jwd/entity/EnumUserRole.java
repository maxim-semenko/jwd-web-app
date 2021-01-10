package com.epam.jwd.entity;

public enum EnumUserRole {
    ADMIN(1),
    CLIENT(2);

    private final int id;

    EnumUserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EnumUserRole resolveById(int id) {
        switch (id) {
            case 1:
                return ADMIN;
            case 2:
                return CLIENT;
            default:
                return null;
        }
    }

}
