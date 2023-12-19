package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.util.Direction;

public class DigInstruction {
    private final Direction direction;
    private final int totalSteps;

    public DigInstruction(String direction, int steps) {
        this.direction = switch (direction) {
            case "U", "3" -> Direction.NORTH;
            case "D", "1" -> Direction.SOUTH;
            case "L", "2" -> Direction.WEST;
            case "R", "0" -> Direction.EAST;
            default -> throw new IllegalStateException("Invalid direction: " + direction);
        };
        this.totalSteps = steps;
    }

    public Direction direction() {
        return direction;
    }

    public int totalSteps() {
        return totalSteps;
    }
}
