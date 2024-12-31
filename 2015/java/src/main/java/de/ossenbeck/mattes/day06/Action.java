package de.ossenbeck.mattes.day06;

public enum Action {
    TURN_ON,
    TURN_OFF,
    TOGGLE;

    public static Action of(String name) {
        return switch (name) {
            case "turn off" -> TURN_OFF;
            case "turn on" -> TURN_ON;
            case "toggle" -> TOGGLE;
            default -> throw new IllegalArgumentException("Unknown action: " + name);
        };
    }
}