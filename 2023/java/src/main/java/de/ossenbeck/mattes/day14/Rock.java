package de.ossenbeck.mattes.day14;

import java.util.Arrays;

public enum Rock {
    ROUNDED('O'),
    CUBE_SHAPED('#');

    private final char identifier;

    Rock(char identifier) {
        this.identifier = identifier;
    }

    public static Rock of(char identifier) {
        return Arrays.stream(values())
                .filter(rock -> rock.identifier == identifier)
                .findAny()
                .orElseThrow();
    }

    public char identifier() {
        return identifier;
    }
}
