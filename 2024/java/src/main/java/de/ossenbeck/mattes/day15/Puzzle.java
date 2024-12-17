package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.ossenbeck.mattes.common.Util.*;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final char ROBOT = '@';
    private static final char EMPTY_SPACE = '.';
    private static final char WALL = '#';
    private static final char SMALL_BOX = 'O';
    private static final char BIG_BOX_LEFT_HALF = '[';
    private static final char BIG_BOX_RIGHT_HALF = ']';
    private final Grid grid;
    private final List<Direction> movements;

    public Puzzle(InputReader inputReader) {
        var input = DOUBLE_LINE_SEPARATOR.split(inputReader.asString());
        this.grid = new Grid(
                LINE_SEPARATOR.splitAsStream(input[0])
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.movements = LINE_SEPARATOR.splitAsStream(input[1])
                .flatMap(SINGLE_CHAR_SEPARATOR::splitAsStream)
                .map(Direction::of)
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        return attemptMovements(grid.copy(), SMALL_BOX);
    }

    @Override
    public Integer solvePartTwo() {
        return attemptMovements(scaleUpWarehouse(), BIG_BOX_LEFT_HALF);
    }

    private int attemptMovements(Grid warehouse, char boxSymbol) {
        var robot = warehouse.find(ROBOT);
        for (var direction : movements) {
            if (attemptMovement(robot, direction, warehouse)) {
                robot = robot.move(direction);
            }
        }
        return warehouse.traverse()
                .filter(coordinate -> warehouse.charAt(coordinate) == boxSymbol)
                .mapToInt(box -> box.y() * 100 + box.x())
                .sum();
    }

    private boolean attemptMovement(Coordinate current, Direction direction, Grid warehouse) {
        var next = current.move(direction);
        if (canMove(next, direction, warehouse)) {
            shift(current, direction, warehouse);
            return true;
        }
        return false;
    }

    private boolean canMove(Coordinate current, Direction direction, Grid warehouse) {
        var symbol = warehouse.charAt(current);
        if (isWall(symbol)) {
            return false;
        }
        if (isEmptySpace(symbol)) {
            return true;
        }
        if (canBeShiftedEasily(symbol, direction)) {
            return canMove(current.move(direction), direction, warehouse);
        }
        return canShiftBigBoxUpOrDown(current, direction, warehouse);
    }

    private void shift(Coordinate current, Direction direction, Grid warehouse) {
        var next = current.move(direction);
        var symbol = warehouse.charAt(next);
        if (canBeShiftedEasily(symbol, direction)) {
            shift(next, direction, warehouse);
        } else if (isBigBox(symbol)) {
            shiftBigBoxUpOrDown(next, direction, warehouse);
        }
        warehouse.replaceCharAt(next, warehouse.charAt(current));
        warehouse.replaceCharAt(current, EMPTY_SPACE);
    }

    private boolean canBeShiftedEasily(char symbol, Direction direction) {
        return isSmallBox(symbol)
                || (isBigBox(symbol) && (Direction.WEST.equals(direction) || Direction.EAST.equals(direction)));
    }

    private void shiftBigBoxUpOrDown(Coordinate current, Direction direction, Grid warehouse) {
        var bigBox = getBixBox(current, warehouse);
        shift(bigBox.left(), direction, warehouse);
        shift(bigBox.right(), direction, warehouse);
    }

    private boolean canShiftBigBoxUpOrDown(Coordinate current, Direction direction, Grid warehouse) {
        var bigBox = getBixBox(current, warehouse);
        return canMove(bigBox.left().move(direction), direction, warehouse)
                && canMove(bigBox.right().move(direction), direction, warehouse);
    }

    private Pair<Coordinate, Coordinate> getBixBox(Coordinate current, Grid warehouse) {
        var symbol = warehouse.charAt(current);
        var leftHalf = symbol == BIG_BOX_LEFT_HALF ? current : current.move(Direction.WEST);
        var rightHalf = symbol == BIG_BOX_RIGHT_HALF ? current : current.move(Direction.EAST);
        return new Pair<>(leftHalf, rightHalf);
    }

    private boolean isWall(char symbol) {
        return symbol == WALL;
    }

    private boolean isEmptySpace(char symbol) {
        return symbol == EMPTY_SPACE;
    }

    private boolean isSmallBox(char symbol) {
        return symbol == SMALL_BOX;
    }

    private boolean isBigBox(char symbol) {
        return symbol == BIG_BOX_LEFT_HALF || symbol == BIG_BOX_RIGHT_HALF;
    }

    private Grid scaleUpWarehouse() {
        return new Grid(IntStream.range(0, grid.height())
                .mapToObj(y -> IntStream.range(0, grid.width())
                        .mapToObj(x -> new Coordinate(x, y))
                        .map(grid::charAt)
                        .map(this::scaleUpWarehouseObject)
                        .collect(Collectors.joining()))
                .map(String::toCharArray)
                .toArray(char[][]::new)
        );
    }

    private String scaleUpWarehouseObject(char warehouseObject) {
        return switch (warehouseObject) {
            case '#' -> "##";
            case '.' -> "..";
            case '@' -> "@.";
            case 'O' -> "[]";
            default -> throw new IllegalArgumentException("Unknown warehouse object");
        };
    }
}
