package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.ossenbeck.mattes.common.Util.SPACE_SEPARATOR;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Long> stones;
    private final Map<State, Long> cache = new HashMap<>();

    public Puzzle(InputReader inputReader) {
        this.stones = SPACE_SEPARATOR.splitAsStream(inputReader.asString())
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    @Override
    public Long solvePartOne() {
        return stones.stream()
                .mapToLong(stone -> blink(stone, 25))
                .sum();
    }

    @Override
    public Long solvePartTwo() {
        return stones.stream()
                .mapToLong(stone -> blink(stone, 75))
                .sum();
    }

    private long blink(long stone, int times) {
        if (times == 0) {
            return 1;
        }
        var state = new State(stone, times);
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        if (stone == 0) {
            var amountOfStones = blink(1, times - 1);
            cache.put(state, amountOfStones);
            return amountOfStones;
        }
        var stoneString = String.valueOf(stone);
        if (stoneString.length() % 2 == 0) {
            var left = stoneString.substring(0, stoneString.length() / 2);
            var right = stoneString.substring(stoneString.length() / 2);
            var amountOfStones = blink(Integer.parseInt(left), times - 1)
                    + blink(Integer.parseInt(right), times - 1);
            cache.put(state, amountOfStones);
            return amountOfStones;
        }
        var amountOfStones = blink(stone * 2024, times - 1);
        cache.put(state, amountOfStones);
        return amountOfStones;
    }

}
