package de.ossenbeck.mattes.day19;

import java.util.Arrays;

public enum Category {
    LOOKING("x"),
    MUSICAL("m"),
    AERODYNAMIC("a"),
    SHINY("s");

    private final String identifier;

    Category(String identifier) {
        this.identifier = identifier;
    }

    public static Category of(String identifier) {
        return Arrays.stream(values())
                .filter(category -> category.identifier.equalsIgnoreCase(identifier))
                .findAny()
                .orElseThrow();
    }
}