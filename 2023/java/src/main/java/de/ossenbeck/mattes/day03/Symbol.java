package de.ossenbeck.mattes.day03;

import java.util.ArrayList;
import java.util.List;

public class Symbol {
    private static final char GEAR_SYMBOL = '*';
    private static final int PART_NUMBERS_REQUIRED_FOR_GEAR = 2;
    private final char value;
    private final int x;
    private final int y;
    private final List<Number> adjacentNumbers = new ArrayList<>();

    public Symbol(char value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public boolean isAdjacent(Number number) {
        return Math.abs(number.y() - y) <= 1
                && ((Math.abs(number.xStart() - x) <= 1 || Math.abs(number.xEnd() - x) <= 1));
    }

    public void addAdjacentNumber(Number number) {
        adjacentNumbers.add(number);
    }

    public List<Number> getAdjacentNumbers() {
        return adjacentNumbers;
    }

    private boolean isGear() {
        return value == GEAR_SYMBOL && adjacentNumbers.size() == PART_NUMBERS_REQUIRED_FOR_GEAR;
    }

    public int getGearRatio() {
        return adjacentNumbers.stream()
                .filter(__ -> isGear())
                .map(Number::value)
                .reduce(Math::multiplyExact)
                .orElse(0);
    }
}
