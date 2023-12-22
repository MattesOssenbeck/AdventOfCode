package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static de.ossenbeck.mattes.util.Direction.*;

public class TheFloorWillBeLava implements Solveable<Integer, Integer> {
    private final char[][] grid;

    public TheFloorWillBeLava(List<String> input) {
        this.grid = input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solvePartOne() {
        return debugContraption(new Coordinate(-1, 0), EAST, new HashSet<>()).stream()
                .map(State::coordinate)
                .collect(Collectors.toSet()).size();
    }

    @Override
    public Integer solvePartTwo() {
        var entries = new ArrayList<State>();
        for (var y = 0; y < grid.length; y++) {
            entries.add(new State(new Coordinate(-1, y), EAST));
            entries.add(new State(new Coordinate(grid[y].length, y), WEST));
        }
        for (var x = 0; x < grid[0].length; x++) {
            entries.add(new State(new Coordinate(x, -1), SOUTH));
            entries.add(new State(new Coordinate(x, grid.length), NORTH));
        }
        return entries.stream().parallel()
                .map(state -> debugContraption(state.coordinate(), state.direction(), new HashSet<>()))
                .map(energizedTiles -> energizedTiles.stream().map(State::coordinate).collect(Collectors.toSet()).size())
                .reduce(0, Integer::max);
    }

    private Set<State> debugContraption(Coordinate lastTile, Direction direction, Set<State> visited) {
        var currentTile = new Coordinate(lastTile.x() + direction.x(), lastTile.y() + direction.y());
        var state = new State(currentTile, direction);
        if (visited.contains(state)
                || currentTile.y() < 0 || currentTile.y() >= grid.length
                || currentTile.x() < 0 || currentTile.x() >= grid[currentTile.y()].length) {
            return visited;
        }
        var tile = grid[currentTile.y()][currentTile.x()];
        visited.add(state);
        if (tile == '.'
                || ((direction.equals(NORTH) || direction.equals(SOUTH)) && tile == '|')
                || ((direction.equals(EAST) || direction.equals(WEST)) && tile == '-')) {
            return debugContraption(currentTile, direction, visited);
        }
        if (tile == '|') {
            visited.addAll(debugContraption(currentTile, NORTH, visited));
            return debugContraption(currentTile, SOUTH, visited);
        }
        if (tile == '-') {
            visited.addAll(debugContraption(currentTile, EAST, visited));
            return debugContraption(currentTile, WEST, visited);
        }
        if (tile == '/') {
            return debugContraption(currentTile, Direction.of(direction.y() * -1, direction.x() * -1), visited);
        }
        return debugContraption(currentTile, Direction.of(direction.y(), direction.x()), visited);
    }
}
