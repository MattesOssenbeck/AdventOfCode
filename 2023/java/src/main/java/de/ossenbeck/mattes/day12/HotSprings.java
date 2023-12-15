package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.Tuple;

import java.util.*;
import java.util.stream.IntStream;

public class HotSprings implements Solveable<Long, Long> {
    private final List<String> conditionRecords;
    private final List<List<Integer>> sizesOfContiguousGroups;
    private final Map<State, Long> cache = new HashMap<>();

    public HotSprings(List<String> input) {
        var data = input.stream()
                .map(line -> line.split(" "))
                .toList();
        this.conditionRecords = data.stream()
                .map(split -> split[0])
                .toList();
        this.sizesOfContiguousGroups = data.stream()
                .map(split -> Arrays.stream(split[1].split(",")).map(Integer::parseInt).toList())
                .toList();
    }

    @Override
    public Long solvePartOne() {
        return IntStream.range(0, conditionRecords.size())
                .mapToLong(i -> calculateValidArrangements(conditionRecords.get(i), sizesOfContiguousGroups.get(i)))
                .sum();
    }

    @Override
    public Long solvePartTwo() {
        return IntStream.range(0, conditionRecords.size())
                .mapToObj(i -> unfold(conditionRecords.get(i), sizesOfContiguousGroups.get(i)))
                .mapToLong(tuple -> calculateValidArrangements(tuple.first(), tuple.second()))
                .sum();
    }

    private long calculateValidArrangements(String conditionRecord, List<Integer> sizeOfGroups) {
        var state = new State(conditionRecord, sizeOfGroups);
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        if (conditionRecord.isEmpty()) {
            return sizeOfGroups.isEmpty() ? 1 : 0;
        }
        if (sizeOfGroups.isEmpty()) {
            return conditionRecord.contains(Spring.DAMAGED.identifierAsString()) ? 0 : 1;
        }
        var numberOfValidArrangements = 0L;
        if (isOperationalSpring(conditionRecord.charAt(0))) {
            numberOfValidArrangements += calculateValidArrangements(conditionRecord.substring(1), sizeOfGroups);
        }
        if (isDamagedSpring(conditionRecord.charAt(0))
                && isContiguousGroupPossible(sizeOfGroups.get(0), conditionRecord)
                && isContiguousGroupFollowedByOperationalSpring(sizeOfGroups.get(0), conditionRecord)) {
            var remainingGroups = new ArrayList<>(sizeOfGroups);
            var contiguousGroupSize = remainingGroups.remove(0);
            var remainingConditionRecord = contiguousGroupSize == conditionRecord.length() ? ""
                    : conditionRecord.substring(contiguousGroupSize + 1);
            numberOfValidArrangements += calculateValidArrangements(remainingConditionRecord, remainingGroups);
        }
        cache.put(state, numberOfValidArrangements);
        return numberOfValidArrangements;
    }

    private static boolean isDamagedSpring(char spring) {
        return spring == Spring.DAMAGED.identifier() || spring == Spring.UNKNOWN.identifier();
    }

    private static boolean isOperationalSpring(char spring) {
        return spring == Spring.OPERATIONAL.identifier() || spring == Spring.UNKNOWN.identifier();
    }

    private static boolean isContiguousGroupPossible(int contiguousGroupSize, String conditionRecord) {
        return contiguousGroupSize <= conditionRecord.length()
                && !conditionRecord.substring(0, contiguousGroupSize).contains(Spring.OPERATIONAL.identifierAsString());
    }

    private static boolean isContiguousGroupFollowedByOperationalSpring(int contiguousGroupSize, String conditionRecord) {
        return contiguousGroupSize == conditionRecord.length()
                || conditionRecord.charAt(contiguousGroupSize) != Spring.DAMAGED.identifier();
    }

    private static Tuple<String, List<Integer>> unfold(String foldedConditionRecord, List<Integer> foldedGroups) {
        var unfoldedConditionRecords = new StringBuilder();
        var unfoldedGroups = new ArrayList<Integer>();
        for (var i = 0; i < 5; i++) {
            unfoldedConditionRecords.append(foldedConditionRecord).append("?");
            unfoldedGroups.addAll(foldedGroups);
        }
        unfoldedConditionRecords.deleteCharAt(unfoldedConditionRecords.length() - 1);
        return new Tuple<>(unfoldedConditionRecords.toString(), unfoldedGroups);
    }
}
