package de.ossenbeck.mattes.day04;

import java.util.List;

public class Scratchcard {
    private final int id;
    private final List<Integer> winningNumbers;
    private final List<Integer> scratchedNumbers;

    public Scratchcard(int id, List<Integer> winningNumbers, List<Integer> scratchedNumbers) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.scratchedNumbers = scratchedNumbers;
    }

    public int getNumberOfMatchingNumbers() {
        return (int) scratchedNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public int getId() {
        return id;
    }
}
