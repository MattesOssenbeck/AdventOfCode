package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.List;
import java.util.regex.Pattern;

public class Puzzle implements Solvable<Long, Long> {
    private final List<String> strings;

    public Puzzle(InputReader inputReader) {
        this.strings = inputReader.asList();
    }

    @Override
    public Long solvePartOne() {
        var hasNoForbiddenPairs = Pattern.compile("ab|cd|pq|xy").asPredicate().negate();
        var hasThreeVowels = Pattern.compile("([^aeiou]*[aeiou]){3}").asPredicate();
        var hasRepeatingLetterWithoutGap = Pattern.compile("(.)\\1").asPredicate();
        return strings.stream()
                .filter(hasNoForbiddenPairs)
                .filter(hasThreeVowels)
                .filter(hasRepeatingLetterWithoutGap)
                .count();
    }
    
    @Override
    public Long solvePartTwo() {
        var hasNonOverlappingPairs = Pattern.compile("(..).*\\1").asPredicate();
        var hasRepeatingLetterWithGapOfLengthOne = Pattern.compile("(.).\\1").asPredicate();
        return strings.stream()
                .filter(hasNonOverlappingPairs)
                .filter(hasRepeatingLetterWithGapOfLengthOne)
                .count();
    }
}
