package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle implements Solvable<Integer, Integer> {
    private final Grid grid;
    private final Map<Character, List<Coordinate>> antennas = new HashMap<>();

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        grid.traverse()
                .filter(coordinate -> grid.charAt(coordinate) != '.')
                .forEach(coordinate -> antennas.computeIfAbsent(
                                grid.charAt(coordinate),
                                _ -> new ArrayList<>()
                        ).add(coordinate)
                );
    }

    @Override
    public Integer solvePartOne() {
        return (int) antennas.values().stream()
                .flatMap(this::buildPairs)
                .<Coordinate>mapMulti((pair, stream) -> {
                    stream.accept(pair.applyLR(Coordinate::addDistanceTo));
                    stream.accept(pair.applyRL(Coordinate::addDistanceTo));
                })
                .filter(grid::isInBounds)
                .distinct()
                .count();
    }


    @Override
    public Integer solvePartTwo() {
        return (int) antennas.values().stream()
                .flatMap(this::buildPairs)
                .<Coordinate>mapMulti((pair, stream) -> {
                    stream.accept(pair.left());
                    stream.accept(pair.right());
                    findAllAntinodes(pair.left(), pair.right()).forEach(stream);
                    findAllAntinodes(pair.right(), pair.left()).forEach(stream);
                })
                .distinct()
                .count();
    }

    private Stream<Pair<Coordinate, Coordinate>> buildPairs(List<Coordinate> antennas) {
        return IntStream.range(0, antennas.size())
                .mapToObj(i -> IntStream.range(i + 1, antennas.size())
                        .mapToObj(j -> new Pair<>(antennas.get(i), antennas.get(j))))
                .flatMap(Function.identity());
    }

    private Stream<Coordinate> findAllAntinodes(Coordinate a, Coordinate b) {
        return Stream.iterate(
                new Pair<>(a, a.addDistanceTo(b)),
                pair -> grid.isInBounds(pair.right()),
                pair -> new Pair<>(pair.right(), pair.applyRL(Coordinate::addDistanceTo))
        ).map(Pair::right);
    }
}