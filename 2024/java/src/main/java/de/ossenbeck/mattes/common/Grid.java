package de.ossenbeck.mattes.common;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Grid(char[][] grid) {
    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.y() >= 0 && coordinate.y() < height()
                && coordinate.x() >= 0 && coordinate.x() < width();
    }

    public boolean areInBounds(Coordinate coordinate, List<Direction> directions) {
        return directions.stream()
                .map(coordinate::move)
                .allMatch(this::isInBounds);
    }

    public Stream<Coordinate> traverse() {
        return IntStream.range(0, height())
                .boxed()
                .flatMap(y -> IntStream.range(0, width()).mapToObj(x -> new Coordinate(x, y)));
    }

    public char charAt(Coordinate coordinate) {
        return grid[coordinate.y()][coordinate.x()];
    }

    public void replaceCharAt(Coordinate coordinate, char newChar) {
        grid[coordinate.y()][coordinate.x()] = newChar;
    }

    public void print() {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public void print(Set<Coordinate> toHighlight) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (toHighlight.contains(new Coordinate(x, y))) {
                    System.out.print('X');
                    continue;
                }
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public void printCount(Set<Position> toHighlight) {
        for (var y = 0; y < height(); y++) {
            for (var x = 0; x < width(); x++) {
                var hits = toHighlight.stream()
                        .map(Position::coordinate)
                        .filter(new Coordinate(x, y)::equals)
                        .count();
                if (hits != 0) {
                    System.out.print(hits);
                    continue;
                }
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public Coordinate find(char charToFind) {
        return traverse()
                .filter(coordinate -> charAt(coordinate) == charToFind)
                .findAny()
                .orElseThrow();
    }

    public List<Coordinate> findAll(char charToFind) {
        return traverse()
                .filter(coordinate -> charAt(coordinate) == charToFind)
                .collect(Collectors.toList());
    }

    public List<Coordinate> findAll(Predicate<Coordinate> filter) {
        return traverse()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public boolean isInQuadrantI(Coordinate c) {
        return c.x() > width() / 2 && c.y() < height() / 2;
    }

    public boolean isInQuadrantII(Coordinate c) {
        return c.x() < width() / 2 && c.y() < height() / 2;
    }

    public boolean isInQuadrantIII(Coordinate c) {
        return c.x() < width() / 2 && c.y() > height() / 2;
    }

    public boolean isInQuadrantIV(Coordinate c) {
        return c.x() > width() / 2 && c.y() > height() / 2;
    }

    public int width() {
        return grid[0].length;
    }

    public int height() {
        return grid.length;
    }

    public Coordinate wrapAround(Coordinate c) {
        return new Coordinate(
                (c.x() % width() + width()) % width(),
                (c.y() % height() + height()) % height()
        );
    }

    public Grid copy() {
        return new Grid(
                IntStream.range(0, height())
                        .mapToObj(y -> grid[y].clone())
                        .toArray(char[][]::new)
        );
    }
}
