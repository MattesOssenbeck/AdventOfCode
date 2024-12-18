package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;
import de.ossenbeck.mattes.common.Position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final char WALL = '#';
    private final Grid grid;
    private final Position start;
    private final Coordinate end;

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.start = new Position(grid.find('S'), Direction.EAST);
        this.end = grid.find('E');
    }

    @Override
    public Integer solvePartOne() {
        return findPathWithLowestScore().score();
    }

    private State findPathWithLowestScore() {
        var visited = new HashSet<Position>();
        var toVisit = new PriorityQueue<State>();
        toVisit.add(new State(start, 0, null));
        while (!toVisit.isEmpty()) {
            var current = toVisit.poll();
            if (visited.contains(current.position())) {
                continue;
            }
            if (current.position().coordinate().equals(end)) {
                return current;
            }
            visited.add(current.position());
            getAdjacentTiles(current).forEach(toVisit::offer);
        }
        throw new IllegalStateException("No path found");
    }

    private Stream<State> getAdjacentTiles(State current) {
        return Stream.of(current.position().move(), current.position().moveRight(), current.position().moveLeft())
                .filter(next -> grid.charAt(next.coordinate()) != WALL)
                .map(next -> {
                    var score = next.direction().equals(current.position().direction()) ? 1 : 1001;
                    score += current.score();
                    return new State(next, score, current);
                });
    }

    @Override
    public Integer solvePartTwo() {
        var endTile = findPathWithLowestScore();
        var seats = new HashSet<Position>();
        var lowestScores = new HashMap<Position, Integer>();
        addAll(endTile, seats, lowestScores);

        var previousTile = endTile.previous();
        while (previousTile != null) {
            var toVisit = getAdjacentTiles(previousTile)
                    .filter(not(next -> seats.contains(next.position())))
                    .collect(Collectors.toCollection(LinkedList::new));

            while (!toVisit.isEmpty()) {
                var current = toVisit.removeFirst();
                if (lowestScores.getOrDefault(current.position(), endTile.score()) < current.score()) {
                    continue;
                }
                if (seats.contains(current.position())) {
                    addAll(current, seats, lowestScores);
                    continue;
                }
                lowestScores.put(current.position(), current.score());
                getAdjacentTiles(current).forEach(toVisit::addFirst);
            }
            previousTile = previousTile.previous();
        }
        return (int) seats.stream()
                .map(Position::coordinate)
                .distinct()
                .count();
    }

    private void addAll(State state, Set<Position> seats, Map<Position, Integer> lowestScores) {
        var current = state;
        while (current != null) {
            seats.add(current.position());
            lowestScores.put(current.position(), current.score());
            current = current.previous();
        }
    }
}
