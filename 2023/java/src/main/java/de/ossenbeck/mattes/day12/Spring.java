package de.ossenbeck.mattes.day12;

public enum Spring {
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    private final char identifier;

    Spring(char identifier) {
        this.identifier = identifier;
    }

    public String identifierAsString() {
        return String.valueOf(identifier);
    }

    public char identifier() {
        return identifier;
    }
}
