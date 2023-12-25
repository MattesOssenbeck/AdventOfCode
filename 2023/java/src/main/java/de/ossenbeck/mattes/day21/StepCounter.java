package de.ossenbeck.mattes.day21;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

import java.util.*;
import java.util.stream.IntStream;

public class StepCounter implements Solveable<Integer, Long> {
    private static final char GARDEN_PLOT = '.';
    private static final char ROCK = '#';
    private final char[][] grid;
    private final Coordinate start;
    private final Map<Integer, Integer> cache = new HashMap<>();

    public StepCounter(List<String> input) {
        this.grid = input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
        this.start = IntStream.range(0, input.size())
                .filter(y -> input.get(y).contains("S"))
                .mapToObj(y -> new Coordinate(input.get(y).indexOf("S"), y))
                .findAny()
                .orElseThrow();
        grid[start.y()][start.x()] = GARDEN_PLOT;
    }

    @Override
    public Integer solvePartOne() {
        /*
         * The 'infiniteGrid' flag is not necessarily needed for part 1 because 64 steps do not run out of the grid.
         * Nevertheless, it remains, because in part 1 it is simply not given that the grid repeats infinitely.
         */
        return countSteps(new HashSet<>(Set.of(start)), 64, false).size();
    }

    @Override
    public Long solvePartTwo() {
        var totalSteps = 26_501_365;
        var stepsNeeded = grid.length * 2 + grid.length / 2;
        countSteps(new HashSet<>(Set.of(start)), stepsNeeded, true);
        /*
         * My solution is obviously too slow for part 2, but it is sufficient to determine the necessary numbers
         * for the following calculation, which I took from this video -> https://www.youtube.com/watch?v=99Mjs1i0JxU
         */
        var positionsAfter65Steps = cache.get(stepsNeeded - grid.length / 2);
        var positionsAfter196Steps = cache.get(stepsNeeded - (grid.length + grid.length / 2));
        var positionsAfter327Steps = cache.get(0);
        var a = (positionsAfter327Steps - (2 * positionsAfter196Steps) + positionsAfter65Steps) / 2;
        var b = positionsAfter196Steps - positionsAfter65Steps - a;
        var c = positionsAfter65Steps;
        var n = (long) (totalSteps - grid.length / 2) / grid.length;
        return ((a * (n * n)) + (b * n) + c);
    }

    private Set<Coordinate> countSteps(Set<Coordinate> currentPositions, int remainingSteps, boolean infiniteGrid) {
        cache.put(remainingSteps, currentPositions.size());
        if (remainingSteps == 0) {
            return currentPositions;
        }
        var possiblePositions = new HashSet<Coordinate>();
        for (var currentPosition : currentPositions) {
            for (var direction : Direction.values()) {
                var position = new Coordinate(currentPosition.x() + direction.x(), currentPosition.y() + direction.y());
                var projectedPosition = infiniteGrid ? projectPositionToGrid(position) : position;
                if ((infiniteGrid || isInGrid(position)) && grid[projectedPosition.y()][projectedPosition.x()] != ROCK) {
                    possiblePositions.add(position);
                }
            }
        }
        return countSteps(possiblePositions, remainingSteps - 1, infiniteGrid);
    }

    private Coordinate projectPositionToGrid(Coordinate c) {
        var ny = c.y();
        while (ny < 0) {
            ny += grid.length;
        }
        var nx = c.x();
        while (nx < 0) {
            nx += grid[0].length;
        }
        return new Coordinate(nx % grid[0].length, ny % grid.length);
    }

    private boolean isInGrid(Coordinate coordinate) {
        return coordinate.y() >= 0 && coordinate.y() < grid.length
                && coordinate.x() >= 0 && coordinate.x() < grid[0].length;
    }
}
