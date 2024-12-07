package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final char OBSTRUCTION = '#';
    private final Grid grid;
    private final Position guardStartingPosition;

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.guardStartingPosition = new Position(grid.getCoordinateOf('^'), Direction.NORTH);
    }

    @Override
    public Integer solvePartOne() {
        return simulateGuardMovement(null).size();
    }

    @Override
    public Integer solvePartTwo() {
        var visitedPositions = simulateGuardMovement(null);
        return (int) visitedPositions.parallelStream()
                .filter(not(guardStartingPosition.coordinate()::equals))
                .map(this::simulateGuardMovement)
                .filter(Set::isEmpty)
                .count();
    }

    private Set<Coordinate> simulateGuardMovement(Coordinate temporaryObstruction) {
        var visitedPositions = new HashSet<Position>();
        visitedPositions.add(guardStartingPosition);
        var currentPosition = guardStartingPosition;

        while (true) {
            var nextPosition = currentPosition.move();
            if (!grid.isInBounds(nextPosition.coordinate())) {
                return visitedPositions.stream()
                        .map(Position::coordinate)
                        .collect(Collectors.toSet());
            }
            if (grid.charAt(nextPosition.coordinate()) == OBSTRUCTION || nextPosition.coordinate().equals(temporaryObstruction)) {
                currentPosition = currentPosition.turnRight();
                continue;
            }
            if (visitedPositions.contains(nextPosition)) {
                return Collections.emptySet();
            }
            visitedPositions.add(nextPosition);
            currentPosition = nextPosition;
        }
    }
}