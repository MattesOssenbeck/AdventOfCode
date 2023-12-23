package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

import java.util.*;
import java.util.stream.IntStream;

import static de.ossenbeck.mattes.util.Direction.EAST;
import static de.ossenbeck.mattes.util.Direction.SOUTH;
import static java.util.function.Predicate.not;

public class ClumsyCrucible implements Solveable<Integer, Integer> {
    private final CityBlock[][] grid;

    public ClumsyCrucible(List<String> input) {
        this.grid = IntStream.range(0, input.size())
                .mapToObj(y -> IntStream.range(0, input.get(y).length())
                        .mapToObj(x -> new CityBlock(new Coordinate(x, y), Integer.parseInt(input.get(y).charAt(x) + "")))
                        .toArray(CityBlock[]::new))
                .toArray(CityBlock[][]::new);
    }

    @Override
    public Integer solvePartOne() {
        return findPathWithMinHeatLoss(1, 3);
    }

    @Override
    public Integer solvePartTwo() {
        return findPathWithMinHeatLoss(4, 10);
    }

    private int findPathWithMinHeatLoss(int minSteps, int maxSteps) {
        var minHeatLosses = Arrays.stream(grid)
                .map(row -> IntStream.range(0, row.length).map(__ -> Integer.MAX_VALUE).toArray())
                .toArray(int[][]::new);

        var visited = new HashSet<State>();
        var toVisit = new PriorityQueue<State>();
        toVisit.add(new State(grid[0][1], EAST, 1, grid[0][1].heatLoss()));
        toVisit.add(new State(grid[1][0], SOUTH, 1, grid[1][0].heatLoss()));
        while (!toVisit.isEmpty()) {
            var currentCityState = toVisit.poll();
            if (visited.contains(currentCityState)) {
                continue;
            }
            var coordinate = currentCityState.cityBlock().coordinate();
            minHeatLosses[coordinate.y()][coordinate.x()] = Math.min(minHeatLosses[coordinate.y()][coordinate.x()], currentCityState.currentHeatLoss());
            visited.add(currentCityState);
            toVisit.addAll(addValidAdjacentCityBlocks(minSteps, maxSteps, currentCityState, visited));
        }
        return minHeatLosses[minHeatLosses.length - 1][minHeatLosses[0].length - 1];
    }

    private List<State> addValidAdjacentCityBlocks(int minSteps, int maxSteps, State currentState, Set<State> visited) {
        var adjacentCityBlocks = new ArrayList<State>();
        if (currentState.steps() < maxSteps && isInGrid(currentState.cityBlock().coordinate(), currentState.direction())) {
            addCityBlock(currentState, visited, currentState.direction(), currentState.steps() + 1, adjacentCityBlocks);
        }
        if (currentState.steps() >= minSteps) {
            Arrays.stream(Direction.values())
                    .filter(not(currentState.direction()::equals))
                    .filter(not(currentState.direction().opposite()::equals))
                    .filter(direction -> isInGrid(currentState.cityBlock().coordinate(), direction, minSteps))
                    .forEach(direction -> addCityBlock(currentState, visited, direction, 1, adjacentCityBlocks));
        }
        return adjacentCityBlocks;
    }

    private void addCityBlock(State state, Set<State> visited, Direction direction, int steps, List<State> adjacentCities) {
        var city = grid[state.cityBlock().coordinate().y() + direction.y()][state.cityBlock().coordinate().x() + direction.x()];
        var cityState = new State(city, direction, steps, state.currentHeatLoss() + city.heatLoss());
        if (!visited.contains(cityState)) {
            adjacentCities.add(cityState);
        }
    }

    private boolean isInGrid(Coordinate coordinate, Direction direction) {
        return isInGrid(coordinate, direction, 1);
    }

    private boolean isInGrid(Coordinate coordinate, Direction direction, int minSteps) {
        return coordinate.y() + direction.y() * minSteps >= 0
                && coordinate.y() + direction.y() * minSteps < grid.length
                && coordinate.x() + direction.x() * minSteps >= 0
                && coordinate.x() + direction.x() * minSteps < grid[0].length;
    }
}
