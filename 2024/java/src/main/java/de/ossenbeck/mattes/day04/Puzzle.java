package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;

import java.util.stream.Stream;

import static de.ossenbeck.mattes.common.Util.traverseGrid;

public class Puzzle implements Solvable<Long, Long> {
    private final char[][] wordSearch;

    public Puzzle(InputReader inputReader) {
        this.wordSearch = inputReader.asStream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Long solvePartOne() {
        return traverseGrid(wordSearch)
                .filter(coordinate -> wordSearch[coordinate.y()][coordinate.x()] == 'X')
                .flatMap(coordinate -> Direction.allDirections()
                        .filter(direction -> isInBounds(coordinate, 3, direction))
                        .filter(direction -> matchesXMAS(coordinate, direction))
                ).count();
    }

    @Override
    public Long solvePartTwo() {
        return traverseGrid(wordSearch)
                .filter(coordinate -> wordSearch[coordinate.y()][coordinate.x()] == 'A')
                .filter(this::ordinalDirectionsAreInBounds)
                .filter(this::matchesX_MAS)
                .count();
    }

    private boolean isInBounds(Coordinate coordinate, int range, Direction direction) {
        var xEnd = coordinate.x() + range * direction.x();
        var yEnd = coordinate.y() + range * direction.y();
        return yEnd >= 0 && yEnd < wordSearch.length && xEnd >= 0 && xEnd < wordSearch[coordinate.y()].length;
    }

    private boolean ordinalDirectionsAreInBounds(Coordinate coordinate) {
        return Direction.ordinalDirections().allMatch(direction -> isInBounds(coordinate, 1, direction));
    }

    private boolean matchesXMAS(Coordinate coordinate, Direction direction) {
        return wordSearch[coordinate.y() + direction.y()][coordinate.x() + direction.x()] == 'M'
                && wordSearch[coordinate.y() + 2 * direction.y()][coordinate.x() + 2 * direction.x()] == 'A'
                && wordSearch[coordinate.y() + 3 * direction.y()][coordinate.x() + 3 * direction.x()] == 'S';
    }

    private boolean matchesX_MAS(Coordinate coordinate) {
        return Stream.of(Direction.NORTH_EAST, Direction.NORTH_WEST)
                .map(direction -> wordSearch[coordinate.y() + direction.y()][coordinate.x() + direction.x()]
                        + "" + wordSearch[coordinate.y() + direction.opposite().y()][coordinate.x() + direction.opposite().x()])
                .allMatch(letters -> letters.equals("SM") || letters.equals("MS"));
    }
}
