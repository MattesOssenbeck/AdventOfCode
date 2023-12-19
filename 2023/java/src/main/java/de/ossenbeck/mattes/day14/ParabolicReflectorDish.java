package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ParabolicReflectorDish implements Solveable<Integer, Integer> {
    private static final char EMPTY = '.';
    private static final char ROUNDED_ROCK = 'O';
    private final char[][] platform;
    private final Map<State, Integer> platformCache = new HashMap<>();

    public ParabolicReflectorDish(List<String> input) {
        this.platform = input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solvePartOne() {
        tiltPlatform(Direction.NORTH);
        return measureLoad(platform);
    }


    @Override
    public Integer solvePartTwo() {
        var cycle = 0;
        do {
            platformCache.put(new State(platform), cycle++);
            completeOneCycle();
        } while (!platformCache.containsKey(new State(platform)));
        var firstEncounter = platformCache.get(new State(platform));
        var cycleLength = cycle - firstEncounter;
        return platformCache.entrySet().stream()
                .filter(e -> e.getValue() == (1_000_000_000 - firstEncounter) % cycleLength + firstEncounter)
                .findFirst()
                .map(Map.Entry::getKey)
                .map(State::platform)
                .map(this::measureLoad)
                .orElseThrow();
    }

    private int measureLoad(char[][] platform) {
        return IntStream.range(0, platform.length)
                .map(y -> IntStream.range(0, platform[y].length)
                        .filter(x -> platform[y][x] == ROUNDED_ROCK)
                        .map(__ -> platform.length - y)
                        .sum())
                .sum();
    }

    private void completeOneCycle() {
        tiltPlatform(Direction.NORTH);
        tiltPlatform(Direction.WEST);
        tiltPlatform(Direction.SOUTH);
        tiltPlatform(Direction.EAST);
    }

    private void tiltPlatform(Direction direction) {
        for (var y = direction.equals(Direction.SOUTH) ? platform.length - 1 : 0;
             direction.equals(Direction.SOUTH) ? y >= 0 : y < platform.length;
             y += direction.equals(Direction.SOUTH) ? -1 : 1) {
            for (var x = direction.equals(Direction.EAST) ? platform[y].length - 1 : 0;
                 direction.equals(Direction.EAST) ? x >= 0 : x < platform[y].length;
                 x += direction.equals(Direction.EAST) ? -1 : 1) {
                if (platform[y][x] == ROUNDED_ROCK) {
                    for (int stepsX = x + direction.x(), stepsY = y + direction.y();
                         (direction.equals(Direction.WEST) ? stepsX >= 0 : stepsX < platform[y].length)
                                 && (direction.equals(Direction.NORTH) ? stepsY >= 0 : stepsY < platform.length);
                         stepsX += direction.x(), stepsY += direction.y()) {
                        if (platform[stepsY][stepsX] != EMPTY) {
                            break;
                        }
                        platform[stepsY][stepsX] = ROUNDED_ROCK;
                        platform[stepsY - direction.y()][stepsX - direction.x()] = EMPTY;
                    }
                }
            }
        }
    }
}
