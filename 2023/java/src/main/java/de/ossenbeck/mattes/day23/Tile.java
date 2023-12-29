package de.ossenbeck.mattes.day23;

import java.util.List;

public class Tile {
    public static final char SLOPE_NORTH = '^';
    public static final char SLOPE_EAST = '>';
    public static final char SLOPE_SOUTH = 'v';
    public static final char SLOPE_WEST = '<';
    public static final char PATH = '.';
    public static final char FOREST = '#';
    private static final List<Character> SLOPES = List.of(SLOPE_NORTH, SLOPE_EAST, SLOPE_SOUTH, SLOPE_WEST);

    public static boolean isSlope(char identifier) {
        return SLOPES.contains(identifier);
    }
}