package de.ossenbeck.mattes.day21;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.*;

import java.util.*;
import java.util.stream.IntStream;

public class Puzzle implements Solvable<Long, Long> {
    private static final Grid NUMERIC_KEYPAD = new Grid(new char[][]{
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'},
            {' ', '0', 'A'}
    });
    private static final Grid DIRECTIONAL_KEYPAD = new Grid(new char[][]{
            {' ', '^', 'A'},
            {'<', 'v', '>'}
    });

    private final Map<Pair<String, Integer>, Long> cache = new HashMap<>();
    private final Map<String, Set<String>> shortestSequences = new HashMap<>();
    private final List<String> inputs;

    public Puzzle(InputReader inputReader) {
        this.inputs = inputReader.asList();
        precalculateShortestSequences(NUMERIC_KEYPAD);
        precalculateShortestSequences(DIRECTIONAL_KEYPAD);
    }

    private void precalculateShortestSequences(Grid keypad) {
        var keys = keypad.findAll(c -> keypad.charAt(c) != ' ');
        for (var start : keys) {
            for (var end : keys) {
                var targetKeys = keypad.charAt(start) + "" + keypad.charAt(end);
                if (start.equals(end)) {
                    shortestSequences.computeIfAbsent(targetKeys, _ -> new HashSet<>()).add("A");
                    continue;
                }
                precalculateShortestSequence(keypad, start, end, targetKeys);
            }
        }
    }

    private void precalculateShortestSequence(Grid keypad, Coordinate start, Coordinate end, String targetKeys) {
        var shortestSequence = Integer.MAX_VALUE;
        var minTurns = Integer.MAX_VALUE;
        var toVisit = new ArrayList<Step>();
        toVisit.add(new Step(start, "", null, 0));

        while (!toVisit.isEmpty()) {
            var current = toVisit.removeFirst();
            for (var direction : Direction.cardinalDirections()) {
                var next = current.next(direction);
                if (!isValidStep(keypad, next, minTurns)) {
                    continue;
                }
                if (!next.coordinate().equals(end)) {
                    toVisit.add(next);
                    continue;
                }
                if (next.sequence().length() > shortestSequence) {
                    return;
                }
                if (next.turns() < minTurns && shortestSequences.containsKey(targetKeys)) {
                    shortestSequences.put(targetKeys, new HashSet<>());
                }
                minTurns = next.turns();
                shortestSequence = next.sequence().length();
                shortestSequences.computeIfAbsent(targetKeys, _ -> new HashSet<>()).add(next.sequence() + "A");
            }
        }
    }

    private boolean isValidStep(Grid keypad, Step current, int minTurns) {
        return current.turns() <= minTurns
                && keypad.isInBounds(current.coordinate())
                && keypad.charAt(current.coordinate()) != ' ';
    }

    private long findShortestSequence(String targetKeys, int robots) {
        var sequences = shortestSequences.get(targetKeys);
        if (robots == 0) {
            return sequences.iterator().next().length();
        }
        var pair = new Pair<>(targetKeys, robots);
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        }
        var shortestSequence = sequences.stream()
                .mapToLong(sequence -> shortestSequence(sequence, robots - 1))
                .min()
                .orElse(Long.MAX_VALUE);
        cache.put(pair, shortestSequence);
        return shortestSequence;
    }

    private long shortestSequence(String sequence, int robots) {
        var seq = "A" + sequence;
        return IntStream.range(0, seq.length() - 1)
                .mapToObj(i -> seq.substring(i, i + 2))
                .mapToLong(targetKeys -> findShortestSequence(targetKeys, robots))
                .sum();
    }

    private long calculateComplexity(int robots) {
        return inputs.stream()
                .map(sequence -> new Pair<>(Util.parseLong(sequence), shortestSequence(sequence, robots)))
                .mapToLong(result -> result.applyLR(Math::multiplyExact))
                .sum();
    }

    @Override
    public Long solvePartOne() {
        return calculateComplexity(2);
    }

    @Override
    public Long solvePartTwo() {
        return calculateComplexity(25);
    }
}
