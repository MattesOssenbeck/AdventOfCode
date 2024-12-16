package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Position;
import de.ossenbeck.mattes.common.Ranges;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public record Region(Set<Coordinate> coordinates) {
    public int area() {
        return coordinates.size();
    }

    public int perimeter() {
        return (int) coordinates.stream()
                .flatMap(this::findAdjacentCoordinatesOutsideOfRegion)
                .count();
    }

    public int sides() {
        return coordinates.stream()
                .flatMap(this::findFencePositions)
                .map(FencePiece::new)
                .collect(Collectors.toMap(
                        FencePiece::identity,
                        fencePiece -> new Ranges().add(fencePiece.value()),
                        Ranges::add
                )).values().stream()
                .mapToInt(Ranges::size)
                .sum();
    }

    private Stream<Coordinate> findAdjacentCoordinatesOutsideOfRegion(Coordinate current) {
        return Direction.cardinalDirections().stream()
                .map(current::move)
                .filter(not(coordinates::contains));
    }

    private Stream<Position> findFencePositions(Coordinate current) {
        return Direction.cardinalDirections().stream()
                .map(direction -> new Position(current.move(direction), direction.opposite()))
                .filter(not(position -> coordinates.contains(position.coordinate())));
    }
}