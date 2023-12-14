package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScratchcardChecker implements Solveable<Integer, Integer> {
    private final List<Scratchcard> scratchcards;
    private final Map<Integer, Integer> copies;

    public ScratchcardChecker(List<String> input) {
        this.scratchcards = ScratchcardMapper.map(input);
        this.copies = scratchcards.stream()
                .collect(Collectors.toMap(Scratchcard::getId, (__) -> 1));
    }

    @Override
    public Integer solvePartOne() {
        return scratchcards.stream()
                .map(Scratchcard::getNumberOfMatchingNumbers)
                .map(number -> (int) Math.pow(2.0, (double) number - 1))
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solvePartTwo() {
        scratchcards.forEach(this::checkScratchcards);
        return copies.values().stream()
                .reduce(0, Integer::sum);
    }

    private void checkScratchcards(Scratchcard scratchcard) {
        var cardId = scratchcard.getId();
        var matchingNumbers = scratchcard.getNumberOfMatchingNumbers();
        IntStream.rangeClosed(cardId + 1, cardId + matchingNumbers)
                .forEach(id -> copies.computeIfPresent(
                        id, (__, numberOfCopies) -> numberOfCopies + copies.get(scratchcard.getId())));
    }
}
