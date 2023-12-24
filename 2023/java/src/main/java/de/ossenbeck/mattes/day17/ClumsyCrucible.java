package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static de.ossenbeck.mattes.util.Direction.EAST;
import static de.ossenbeck.mattes.util.Direction.SOUTH;

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
        var destination = grid[grid.length - 1][grid[0].length - 1];
        var visited = new HashSet<State>();
        var toVisit = new PriorityQueue<State>();
        toVisit.add(new State(grid[0][1], EAST, 1, grid[0][1].heatLoss()));
        toVisit.add(new State(grid[1][0], SOUTH, 1, grid[1][0].heatLoss()));
        while (!toVisit.isEmpty()) {
            var currentCityState = toVisit.poll();
            if (visited.contains(currentCityState)) {
                continue;
            }
            if (currentCityState.cityBlock().equals(destination)) {
                return currentCityState.currentHeatLoss();
            }
            visited.add(currentCityState);
            var currentDirection = currentCityState.direction();
            var currentCoordinate = currentCityState.cityBlock().coordinate();
            var currentSteps = currentCityState.steps();
            for (var possibleDirection : allPossibleDirections(minSteps, maxSteps, currentSteps, currentDirection)) {
                var minRequiredSteps = possibleDirection.equals(currentDirection) ? Math.max(1, minSteps - currentSteps) : minSteps;
                if (isInGrid(currentCoordinate, possibleDirection, minRequiredSteps)) {
                    var nextY = currentCoordinate.y() + possibleDirection.y();
                    var nextX = currentCoordinate.x() + possibleDirection.x();
                    toVisit.add(new State(
                            grid[nextY][nextX],
                            possibleDirection,
                            currentDirection.equals(possibleDirection) ? currentSteps + 1 : 1,
                            currentCityState.currentHeatLoss() + grid[nextY][nextX].heatLoss())
                    );
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static List<Direction> allPossibleDirections(int minSteps, int maxSteps, int currentSteps, Direction currentDirection) {
        var possibleDirections = new ArrayList<Direction>();
        if (currentSteps < maxSteps) {
            possibleDirections.add(currentDirection);
        }
        if (currentSteps >= minSteps) {
            possibleDirections.add(currentDirection.turnLeft());
            possibleDirections.add(currentDirection.turnRight());
        }
        return possibleDirections;
    }

    private boolean isInGrid(Coordinate coordinate, Direction direction, int minSteps) {
        return coordinate.y() + direction.y() * minSteps >= 0
                && coordinate.y() + direction.y() * minSteps < grid.length
                && coordinate.x() + direction.x() * minSteps >= 0
                && coordinate.x() + direction.x() * minSteps < grid[0].length;
    }
}
