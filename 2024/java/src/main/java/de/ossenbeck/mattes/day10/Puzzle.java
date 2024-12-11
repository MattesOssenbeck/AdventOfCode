package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Pair;

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
                .mapToInt(paths -> (int) paths.stream()
                        .flatMap(Set::stream)
                        .filter(coordinate -> grid.charAt(coordinate) == MAX_HEIGHT)
                        .distinct()
                        .count())
                .sum();
    }

    @Override
    public Integer solvePartTwo() {
        return trailheads.stream()
                .map(this::findGoodHikingTrails)
                .mapToInt(Set::size)
                .sum();
    }

    private Set<Set<Coordinate>> findGoodHikingTrails(Coordinate trailhead) {
        var goodHikingTrails = new HashSet<Set<Coordinate>>();
        var toVisit = new ArrayList<Pair<Coordinate, ? extends Set<Coordinate>>>();
        toVisit.add(new Pair<>(trailhead, Set.of(trailhead)));
        while (!toVisit.isEmpty()) {
            var current = toVisit.removeFirst();
            var climbedTrail = new HashSet<>(current.right());
            climbedTrail.add(current.left());
            if (grid.charAt(current.left()) == MAX_HEIGHT) {
                goodHikingTrails.add(climbedTrail);
                continue;
            }
            findAdjacentClimbableSlopes(current.left())
                    .map(coordinate -> new Pair<>(coordinate, climbedTrail))
                    .forEach(toVisit::add);
        }
        return goodHikingTrails;
    }

    private Stream<Coordinate> findAdjacentClimbableSlopes(Coordinate current) {
        var currentHeight = grid.charAt(current);
        return Direction.cardinalDirections().stream()
                .map(current::move)
                .filter(grid::isInBounds)
                .filter(coordinate -> grid.charAt(coordinate) - currentHeight == CLIMBABLE_SLOPE);
    }
}
