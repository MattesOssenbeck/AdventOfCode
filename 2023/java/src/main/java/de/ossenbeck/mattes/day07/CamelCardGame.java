package de.ossenbeck.mattes.day07;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.stream.IntStream;

public class CamelCardGame implements Solveable<Integer, Integer> {
    private final List<Hand> hands;

    public CamelCardGame(List<String> input) {
        this.hands = HandMapper.map(input).stream().sorted().toList();
    }

    @Override
    public Integer solvePartOne() {
        return determineTotalWinnings(false);
    }

    @Override
    public Integer solvePartTwo() {
        return determineTotalWinnings(true);
    }

    private Integer determineTotalWinnings(boolean playingWithJoker) {
        hands.forEach(hand -> hand.checkHand(playingWithJoker));
        var handsLowestToHighestRank = hands.stream().sorted().toList();
        return IntStream.range(0, handsLowestToHighestRank.size())
                .map(rank -> handsLowestToHighestRank.get(rank).getBid() * (rank + 1))
                .reduce(0, Integer::sum);
    }
}