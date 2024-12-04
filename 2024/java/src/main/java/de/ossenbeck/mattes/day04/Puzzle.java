package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;

import java.util.stream.Stream;

public class Puzzle implements Solvable<Long, Long> {
    private final Grid wordSearch;

    public Puzzle(InputReader inputReader) {
        this.wordSearch = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
    }

    @Override
    public Long solvePartOne() {
        return wordSearch.traverse()
                .filter(coordinate -> wordSearch.charAt(coordinate) == 'X')
                .flatMap(coordinate -> Direction.allDirections()
                        .filter(direction -> wordSearch.isInBounds(coordinate.move(direction, 3)))
                        .filter(direction -> matchesXMAS(coordinate, direction))
                ).count();
    }

    @Override
    public Long solvePartTwo() {
        return wordSearch.traverse()
                .filter(coordinate -> wordSearch.charAt(coordinate) == 'A')
                .filter(this::ordinalDirectionsAreInBounds)
                .filter(this::matchesX_MAS)
                .count();
    }

    private boolean ordinalDirectionsAreInBounds(Coordinate coordinate) {
        return Direction.ordinalDirections().allMatch(direction -> wordSearch.isInBounds(coordinate.move(direction)));
    }

    private boolean matchesXMAS(Coordinate coordinate, Direction direction) {
        return wordSearch.charAt(coordinate.move(direction)) == 'M'
                && wordSearch.charAt(coordinate.move(direction, 2)) == 'A'
                && wordSearch.charAt(coordinate.move(direction, 3)) == 'S';
    }

    private boolean matchesX_MAS(Coordinate coordinate) {
        return Stream.of(Direction.NORTH_EAST, Direction.NORTH_WEST)
                .map(direction -> wordSearch.charAt(coordinate.move(direction))
                        + "" + wordSearch.charAt(coordinate.move(direction.opposite()))
                ).allMatch(letters -> letters.equals("SM") || letters.equals("MS"));
    }
}
