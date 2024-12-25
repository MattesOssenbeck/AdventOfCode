package de.ossenbeck.mattes.day25;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static de.ossenbeck.mattes.common.Util.*;

public class Puzzle implements Solvable<Integer, String> {
    private final List<List<Integer>> locks = new ArrayList<>();
    private final List<List<Integer>> keys = new ArrayList<>();

    public Puzzle(InputReader inputReader) {
        DOUBLE_LINE_SEPARATOR.splitAsStream(inputReader.asString())
                .map(schematic -> LINE_SEPARATOR.splitAsStream(schematic).toList())
                .forEach(this::parseSchematic);
    }

    private void parseSchematic(List<String> schematic) {
        var isKey = schematic.getFirst().contains(".");
        var heights = zip(schematic).stream()
                .map(line -> isKey ? line.length() - 1 - line.indexOf('#') : line.lastIndexOf('#'))
                .toList();
        if (isKey) {
            keys.add(heights);
        } else {
            locks.add(heights);
        }
    }

    @Override
    public Integer solvePartOne() {
        return locks.stream()
                .mapToInt(lock -> (int) keys.stream()
                        .filter(key -> IntStream.range(0, key.size()).allMatch(i -> lock.get(i) + key.get(i) <= 5))
                        .count()
                ).sum();
    }

    @Override
    public String solvePartTwo() {
        return "Merry Christmas 🎄";
    }
}