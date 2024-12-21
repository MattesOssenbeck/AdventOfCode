package de.ossenbeck.mattes.day20;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;
import de.ossenbeck.mattes.common.Grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class Puzzle implements Solvable<Long, Long> {
    private static final int MIN_CHEAT_DURATION = 2;
    private static final int MAX_CHEAT_DURATION = 20;
    private static final int MIN_CHEAT_ADVANTAGE = 100;
    private final Grid grid;
    private final List<Coordinate> track;
    private final Map<Coordinate, Integer> steps = new HashMap<>();
    private final List<Cheat> cheats = new ArrayList<>();

    public Puzzle(InputReader inputReader) {
        this.grid = new Grid(
                inputReader.asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
        this.track = grid.findAll(c -> grid.charAt(c) != '#');
        recordSteps(grid.find('S'), grid.find('E'));
        createCheats();
    }

    private void recordSteps(Coordinate start, Coordinate end) {
        var toVisit = new ArrayList<Step>();
        toVisit.add(new Step(start, 0));

        var previous = start;
        while (!toVisit.isEmpty()) {
            var current = toVisit.removeFirst();
            steps.put(current.coordinate(), current.steps());
            if (current.coordinate().equals(end)) {
                return;
            }
            Direction.cardinalDirections().stream()
                    .map(current.coordinate()::move)
                    .filter(c -> grid.charAt(c) != '#')
                    .filter(not(previous::equals))
                    .map(coordinate -> new Step(coordinate, current.steps() + 1))
                    .forEach(toVisit::add);
            previous = current.coordinate();
        }
        throw new IllegalStateException("No path found");
    }

    private void createCheats() {
        for (var i = 0; i < track.size() - 1; i++) {
            var a = track.get(i);
            for (var j = i + 1; j < track.size(); j++) {
                var b = track.get(j);
                var duration = a.manhattanDistance(b);
                if (duration > MAX_CHEAT_DURATION) {
                    continue;
                }
                cheats.add(new Cheat(a, b, duration));
            }
        }
    }

    @Override
    public Long solvePartOne() {
        return countCheats(cheat -> cheat.duration() == MIN_CHEAT_DURATION);
    }

    @Override
    public Long solvePartTwo() {
        return countCheats(cheat -> cheat.duration() >= MIN_CHEAT_DURATION);
    }

    private long countCheats(Predicate<Cheat> cheatFilter) {
        return cheats.stream()
                .filter(cheatFilter)
                .filter(cheat -> getCheatAdvantage(cheat) >= MIN_CHEAT_ADVANTAGE)
                .count();
    }

    private int getCheatAdvantage(Cheat cheat) {
        return Math.abs(steps.get(cheat.a()) - steps.get(cheat.b())) - cheat.duration();
    }
}
