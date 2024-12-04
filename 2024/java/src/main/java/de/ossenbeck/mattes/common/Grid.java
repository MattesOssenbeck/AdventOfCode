package de.ossenbeck.mattes.common;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Grid(char[][] grid) {
    public boolean isInBounds(Coordinate coordinate) {
        return coordinate.y() >= 0 && coordinate.y() < grid.length
                && coordinate.x() >= 0 && coordinate.x() < grid[coordinate.y()].length;
    }

    public boolean areInBounds(Coordinate coordinate, List<Direction> directions) {
        return directions.stream()
                .map(coordinate::move)
                .allMatch(this::isInBounds);
    }

    public Stream<Coordinate> traverse() {
        return IntStream.range(0, grid.length)
                .boxed()
                .flatMap(y -> IntStream.range(0, grid[y].length).mapToObj(x -> new Coordinate(x, y)));
    }

    public char charAt(Coordinate coordinate) {
        return grid[coordinate.y()][coordinate.x()];
    }
}
