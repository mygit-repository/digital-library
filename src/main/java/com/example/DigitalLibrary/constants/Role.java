package com.example.DigitalLibrary.constants;

public enum Role {
    SUPER_ADMIN("SUP", "01"),
    ADMIN("ADM", "02"),
    STAFF("STA", "03"),
    STUDENT("STU", "04");

    private final String prefix;
    private final String code;

    Role(String prefix, String code) {
        this.prefix = prefix;
        this.code = code;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCode() {
        return code;
    }
}
