package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Pair;
import de.ossenbeck.mattes.common.Util;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle implements Solvable<Integer, Integer> {
    private final List<Robot> robots;
    private final Grid grid;

    public Puzzle(InputReader inputReader, int width, int height) {
        this.robots = inputReader.asStream()
                .map(Util::parseNumbers)
                .map(numbers -> new Robot(new Coordinate(numbers.get(0), numbers.get(1)), numbers.get(2), numbers.get(3)))
                .toList();
        this.grid = new Grid(new char[height][width]);
    }

    public Puzzle(InputReader inputReader) {
        this(inputReader, 101, 103);
    }

    @Override
    public Integer solvePartOne() {
        return robots.stream()
                .map(robot -> robot.move(100))
                .map(grid::wrapAround)
                .collect(Collectors.toMap(this::getQuadrant, _ -> 1, Math::addExact))
                .entrySet().stream()
                .filter(e -> e.getKey() != -1)
                .mapToInt(Map.Entry::getValue)
                .reduce(1, Math::multiplyExact);
    }

    @Override
    public Integer solvePartTwo() {
        return IntStream.rangeClosed(1, grid.width() * grid.height())
                .parallel()
                .mapToObj(i -> new Pair<>(i, calculateAverageManhattanDistance(i)))
                .min(Comparator.comparingDouble(Pair::right))
                .map(Pair::left)
                .orElse(0);
    }

    private double calculateAverageManhattanDistance(int step) {
        var robotCoordinates = robots.stream()
                .map(robot -> robot.move(step))
                .map(grid::wrapAround)
                .toList();
        return getAverageManhattanDistance(robotCoordinates);
    }

    private double getAverageManhattanDistance(List<Coordinate> coordinates) {
        var average = 0.0;
        for (var i = 0; i < coordinates.size() - 1; i++) {
            for (var j = i + 1; j < coordinates.size(); j++) {
                average += coordinates.get(i).manhattanDistance(coordinates.get(j));
            }
        }
        return average / coordinates.size() / (coordinates.size() - 1) / 2;
    }

    private int getQuadrant(Coordinate c) {
        return grid.isInQuadrantI(c) ? 1
                : grid.isInQuadrantII(c) ? 2
                : grid.isInQuadrantIII(c) ? 3
                : grid.isInQuadrantIV(c) ? 4
                : -1;
    }
}
