package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.Solveable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.ossenbeck.mattes.util.Util.zip;

public class PointOfIncidence implements Solveable<Integer, Integer> {

    private final List<List<String>> patterns;

    public PointOfIncidence(String input) {
        this.patterns = Arrays.stream(input.split(System.lineSeparator() + System.lineSeparator()))
                .map(pattern -> Arrays.stream(pattern.split(System.lineSeparator())).toList())
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        return summarizePatternNotes(0);
    }


    @Override
    public Integer solvePartTwo() {
        return summarizePatternNotes(1);
    }

    private int summarizePatternNotes(int numberOfSmudges) {
        return patterns.stream()
                .map(pattern -> findMirror(pattern, numberOfSmudges)
                        .map(rows -> rows * 100)
                        .or(() -> findMirror(zip(pattern), numberOfSmudges))
                        .orElse(0))
                .reduce(0, Integer::sum);
    }

    private static Optional<Integer> findMirror(List<String> pattern, int numberOfSmudges) {
        return Stream.iterate(0, y -> y + 1)
                .limit(pattern.size() - 1)
                .filter(y -> checkRowIn(y, pattern, numberOfSmudges))
                .findFirst()
                .map(y -> y + 1);
    }

    private static boolean checkRowIn(int y, List<String> pattern, Integer numberOfSmudges) {
        return Stream.iterate(0, stepsBack -> stepsBack + 1)
                .takeWhile(stepsBack -> stepsBack <= y && y + stepsBack + 1 < pattern.size())
                .map(stepsBack -> countDifferentCharacters(pattern.get(y - stepsBack), pattern.get(y + stepsBack + 1)))
                .reduce(Integer::sum)
                .map(numberOfSmudges::equals)
                .orElse(false);
    }

    private static int countDifferentCharacters(String row, String otherRow) {
        return (int) IntStream.range(0, Math.min(row.length(), otherRow.length()))
                .filter(i -> row.charAt(i) != otherRow.charAt(i))
                .count();
    }
}
