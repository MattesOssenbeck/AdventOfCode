package de.ossenbeck.mattes.day19;

import java.util.Arrays;
import java.util.function.Function;

public enum Category {
    LOOKING("x", Gear::looking),
    MUSICAL("m", Gear::musical),
    AERODYNAMIC("a", Gear::aerodynamic),
    SHINY("s", Gear::shiny);

    private final String identifier;
    private final Function<Gear, Integer> function;

    Category(String identifier, Function<Gear, Integer> function) {
        this.identifier = identifier;
        this.function = function;
    }

    public static Category of(String identifier) {
        return Arrays.stream(values())
                .filter(category -> category.identifier.equalsIgnoreCase(identifier))
                .findAny()
                .orElseThrow();
    }

    public String identifier() {
        return identifier;
    }

    public Function<Gear, Integer> function() {
        return function;
    }
}
