package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.Solveable;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;


public class LensLibrary implements Solveable<Integer, Integer> {
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("=|-");
    private final List<String> initializationSequence;

    public LensLibrary(String input) {
        this.initializationSequence = Arrays.stream(input.split(",")).toList();
    }

    @Override
    public Integer solvePartOne() {
        return initializationSequence.stream()
                .map(this::hash)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solvePartTwo() {
        var hashMap = new HashMap<Integer, List<Lens>>();
        initializationSequence.forEach(line -> installLense(line, hashMap));
        return IntStream.range(0, 256)
                .filter(hashMap::containsKey)
                .map(box -> IntStream.range(0, hashMap.get(box).size())
                        .map(i -> i + 1)
                        .map(slot -> hashMap.get(box).get(slot - 1).focalLength() * slot * (box + 1))
                        .sum())
                .sum();
    }

    private void installLense(String line, Map<Integer, List<Lens>> hashMap) {
        var data = OPERATOR_PATTERN.split(line);
        var key = hash(data[0]);
        var lenses = hashMap.computeIfAbsent(key, __ -> new ArrayList<>());
        if (line.contains("-")) {
            lenses.removeIf(lens -> lens.label().equals(data[0]));
        } else {
            var newLens = new Lens(data[0], Integer.parseInt(data[1]));
            lenses.stream()
                    .filter(lens -> lens.label().equals(newLens.label()))
                    .findFirst()
                    .map(lenses::indexOf)
                    .ifPresentOrElse(lensIndex -> lenses.set(lensIndex, newLens), () -> lenses.add(newLens));
        }
    }

    private int hash(String line) {
        return IntStream.range(0, line.length())
                .map(line::charAt)
                .reduce(0, (currentValue, ascii) -> (currentValue + ascii) * 17 % 256);
    }
}
