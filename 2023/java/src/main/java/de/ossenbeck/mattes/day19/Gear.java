package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.util.Range;

public record Gear(Range looking, Range musical, Range aerodynamic, Range shiny) {
    public Range rangeFor(Category category) {
        return switch (category) {
            case LOOKING -> looking;
            case MUSICAL -> musical;
            case AERODYNAMIC -> aerodynamic;
            case SHINY -> shiny;
        };
    }
    
    public Gear newWith(Category category, Range newRange) {
        return switch (category) {
            case LOOKING -> new Gear(newRange, musical, aerodynamic, shiny);
            case MUSICAL -> new Gear(looking, newRange, aerodynamic, shiny);
            case AERODYNAMIC -> new Gear(looking, musical, newRange, shiny);
            case SHINY -> new Gear(looking, musical, aerodynamic, newRange);
        };
    }
}