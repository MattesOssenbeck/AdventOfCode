package de.ossenbeck.mattes.day23;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ALongWalk implements Solveable<Integer, Integer> {
    private final List<String> map;
    private final Coordinate start;
    private final Coordinate destination;
    private Map<Coordinate, Set<State>> adjacentTrails;
    private final Set<Coordinate> visited = new HashSet<>();

    public ALongWalk(List<String> input) {
        this.map = input;
        this.start = new Coordinate(input.getFirst().indexOf(Tile.PATH), 0);
        this.destination = new Coordinate(input.getLast().indexOf(Tile.PATH), input.size() - 1);
    }

    @Override
    public Integer solvePartOne() {
        return findLongestHikingTrail(start, true);
    }

    @Override
    public Integer solvePartTwo() {
        return findLongestHikingTrail(start, false);
    }

    private int findLongestHikingTrail(Coordinate start, boolean hasSlippySlopes) {
        visited.clear();
        buildHikingTrails(hasSlippySlopes);
        return findLongestHikingTrail(start);
    }

    private int findLongestHikingTrail(Coordinate current) {
        if (current.equals(destination)) {
            return 0;
        }
        visited.add(current);
        var longestPath = Integer.MIN_VALUE;
        for (var adjacentTrail : adjacentTrails.get(current)) {
            if (!visited.contains(adjacentTrail.coordinate())) {
                longestPath = Math.max(longestPath, findLongestHikingTrail(adjacentTrail.coordinate()) + adjacentTrail.length());
            }
        }
        visited.remove(current);
        return longestPath;
    }

    private List<Coordinate> getPossibleSteps(Coordinate coordinate, boolean hasSlippySlopes) {
        if (hasSlippySlopes && Tile.isSlope(map.get(coordinate.y()).charAt(coordinate.x()))) {
            return List.of(followSlope(coordinate));
        }
        return Arrays.stream(Direction.values())
                .map(coordinate::moveIn)
                .filter(this::isHikingTrail)
                .toList();
    }

    private Coordinate followSlope(Coordinate coordinate) {
        return switch (map.get(coordinate.y()).charAt(coordinate.x())) {
            case Tile.SLOPE_NORTH -> coordinate.moveIn(Direction.NORTH);
            case Tile.SLOPE_EAST -> coordinate.moveIn(Direction.EAST);
            case Tile.SLOPE_SOUTH -> coordinate.moveIn(Direction.SOUTH);
            case Tile.SLOPE_WEST -> coordinate.moveIn(Direction.WEST);
            default -> throw new IllegalArgumentException();
        };
    }

    private boolean isHikingTrail(Coordinate coordinate) {
        return coordinate.y() >= 0 && coordinate.y() < map.size()
                && coordinate.x() >= 0 && coordinate.x() < map.get(coordinate.y()).length()
                && map.get(coordinate.y()).charAt(coordinate.x()) != Tile.FOREST;
    }

    private boolean isTrailBranching(int numberOfPossibleSteps) {
        return numberOfPossibleSteps >= 3;
    }

    private void buildHikingTrails(boolean hasSlippySlopes) {
        this.adjacentTrails = IntStream.range(0, map.size())
                .mapToObj(y -> IntStream.range(0, map.get(y).length())
                        .filter(x -> map.get(y).charAt(x) != Tile.FOREST)
                        .mapToObj(x -> new Coordinate(x, y))
                        .filter(coordinate -> isTrailBranching(getPossibleSteps(coordinate, hasSlippySlopes).size())))
                .flatMap(Function.identity())
                .collect(Collectors.toCollection(() -> new ArrayList<>(List.of(start, destination))))
                .stream()
                .collect(Collectors.toMap(Function.identity(), __ -> new HashSet<>()));

        for (var adjacentTrail : adjacentTrails.keySet()) {
            var visited = new HashSet<Coordinate>();
            var trail = new ArrayDeque<State>();
            trail.add(new State(adjacentTrail, 0));
            visited.add(adjacentTrail);

            while (!trail.isEmpty()) {
                var state = trail.poll();
                if (!state.coordinate().equals(adjacentTrail) && adjacentTrails.containsKey(state.coordinate())) {
                    adjacentTrails.get(adjacentTrail).add(state);
                } else {
                    for (var possibleStep : getPossibleSteps(state.coordinate(), hasSlippySlopes)) {
                        if (!visited.contains(possibleStep)) {
                            trail.add(new State(possibleStep, state.length() + 1));
                            visited.add(possibleStep);
                        }
                    }
                }
            }
        }
    }
}
