package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Puzzle implements Solvable<Integer, String> {
    private final Grid grid;
    private final List<Coordinate> allCorruptedCoordinates;

    public Puzzle(InputReader inputReader, int range) {
        this.grid = new Grid(new char[range + 1][range + 1]);
        this.allCorruptedCoordinates = inputReader.asStream()
                .map(Util::parseNumbers)
                .map(numbers -> new Coordinate(numbers.getFirst(), numbers.getLast()))
                .toList();
    }

    public Puzzle(InputReader inputReader) {
        this(inputReader, 70);
    }

    private Set<Coordinate> getInitalCorruptedCoordinates() {
        var toIndex = grid.height() == 7 ? 12 : 1024;
        return new HashSet<>(allCorruptedCoordinates.subList(0, toIndex));
    }

    @Override
    public Integer solvePartOne() {
        return findShortestPath(getInitalCorruptedCoordinates()).count();
    }

    @Override
    public String solvePartTwo() {
        var corruptedCoordinates = getInitalCorruptedCoordinates();
        var path = new HashSet<Coordinate>();
        var current = findShortestPath(corruptedCoordinates);
        while (current != null) {
            path.add(current.coordinate());
            current = current.previous();
        }
        for (var i = corruptedCoordinates.size(); i < allCorruptedCoordinates.size(); i++) {
            var newCorruptedCoordinate = allCorruptedCoordinates.get(i);
            corruptedCoordinates.add(newCorruptedCoordinate);
            if (path.contains(newCorruptedCoordinate) && findShortestPath(corruptedCoordinates) == null) {
                return newCorruptedCoordinate.x() + "," + newCorruptedCoordinate.y();
            }
        }
        return "unknown,unknown";
    }

    private Step findShortestPath(Set<Coordinate> corruptedCoordinates) {
        var end = new Coordinate(grid.width() - 1, grid.height() - 1);
        var visited = new HashSet<Coordinate>();
        var toVisit = new ArrayList<Step>();
        toVisit.add(new Step(new Coordinate(0, 0), null));

        while (!toVisit.isEmpty()) {
            var current = toVisit.removeFirst();
            if (visited.contains(current.coordinate())) {
                continue;
            }
            if (current.coordinate().equals(end)) {
                return current;
            }
            visited.add(current.coordinate());
            findAdjacentCoordinates(current, corruptedCoordinates).forEach(toVisit::add);
        }
        return null;
    }

    private Stream<Step> findAdjacentCoordinates(Step current, Set<Coordinate> corrupted) {
        return Direction.cardinalDirections().stream()
                .map(current.coordinate()::move)
                .filter(grid::isInBounds)
                .filter(not(corrupted::contains))
                .map(next -> new Step(next, current));

    }
}
