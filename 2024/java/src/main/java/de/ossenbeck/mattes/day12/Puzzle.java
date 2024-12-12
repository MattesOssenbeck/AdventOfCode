package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Puzzle implements Solvable<Integer, Integer> {
    private final Grid grid;
    private final List<Region> regions;

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.regions = findAllRegions();
    }

    private List<Region> findAllRegions() {
        var regions = new ArrayList<Region>();
        var visited = new HashSet<>();

        for (var current : grid.traverse().toList()) {
            if (visited.contains(current)) {
                continue;
            }
            var region = findRegion(current);
            visited.addAll(region.coordinates());
            regions.add(region);
        }
        return regions;
    }

    private Region findRegion(Coordinate startOfRegion) {
        var coordinates = new HashSet<Coordinate>();
        var toVisit = new ArrayList<Coordinate>();
        coordinates.add(startOfRegion);
        findAdjacentGardenPlots(startOfRegion).forEach(toVisit::add);

        while (!toVisit.isEmpty()) {
            var current = toVisit.removeFirst();
            if (coordinates.contains(current)) {
                continue;
            }
            coordinates.add(current);
            findAdjacentGardenPlots(current).forEach(toVisit::add);
        }
        return new Region(coordinates);
    }

    private Stream<Coordinate> findAdjacentGardenPlots(Coordinate current) {
        var type = grid.charAt(current);
        return Direction.cardinalDirections().stream()
                .map(current::move)
                .filter(grid::isInBounds)
                .filter(coordinate -> grid.charAt(coordinate) == type);
    }

    @Override
    public Integer solvePartOne() {
        return regions.stream()
                .mapToInt(region -> region.area() * region.perimeter())
                .sum();
    }

    @Override
    public Integer solvePartTwo() {
        return regions.stream()
                .mapToInt(region -> region.area() * region.sites())
                .sum();
    }
}
