package de.ossenbeck.mattes.day02;

public enum Color {
    RED,
    GREEN,
    BLUE;

    public static Color of(String value) {
        return valueOf(value.toUpperCase());
    }
}