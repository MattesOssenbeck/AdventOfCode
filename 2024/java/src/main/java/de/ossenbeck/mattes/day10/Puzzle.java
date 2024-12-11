package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final char MAX_HEIGHT = '9';
    private static final int CLIMBABLE_SLOPE = 1;
    private final Grid grid;
    private final List<Coordinate> trailheads;

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.trailheads = grid.findAll('0');
    }

    @Override
    public Integer solvePartOne() {
        return trailheads.stream()
                .map(this::findGoodHikingTrails)
                .map(HashSet::new)
                .mapToInt(Set::size)
                .sum();
    }

    @Override
    public Integer solvePartTwo() {
        return trailheads.stream()
                .map(this::findGoodHikingTrails)
                .mapToInt(List::size)
                .sum();
    }

    private List<Coordinate> findGoodHikingTrails(Coordinate trailhead) {
        var peaks = new ArrayList<Coordinate>();
        var toVisit = new ArrayList<Coordinate>();
        toVisit.add(trailhead);
        while (!toVisit.isEmpty()) {
            var current = toVisit.removeFirst();
            if (grid.charAt(current) == MAX_HEIGHT) {
                peaks.add(current);
                continue;
            }
            findAdjacentClimbableSlopes(current).forEach(toVisit::add);
        }
        return peaks;
    }

    private Stream<Coordinate> findAdjacentClimbableSlopes(Coordinate current) {
        var currentHeight = grid.charAt(current);
        return Direction.cardinalDirections().stream()
                .map(current::move)
                .filter(grid::isInBounds)
                .filter(coordinate -> grid.charAt(coordinate) - currentHeight == CLIMBABLE_SLOPE);
    }
}
