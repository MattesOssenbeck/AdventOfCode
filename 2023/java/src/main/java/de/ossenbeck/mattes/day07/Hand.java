package de.ossenbeck.mattes.day07;

import java.util.List;
import java.util.stream.IntStream;

public class Hand implements Comparable<Hand> {
    private final List<CamelCard> cards;
    private final int bid;
    private HandType handType;
    private boolean playingWithJoker;

    public Hand(List<CamelCard> cards, int bid) {
        this.cards = cards;
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }

    public void checkHand(boolean playingWithJoker) {
        this.handType = HandType.of(cards, playingWithJoker);
        this.playingWithJoker = playingWithJoker;
    }

    @Override
    public int compareTo(Hand otherHand) {
        var comparisonResult = this.handType != null ? this.handType.compareTo(otherHand.handType) : 0;
        if (comparisonResult != 0) {
            return comparisonResult;
        }
        return IntStream.range(0, cards.size())
                .mapToObj(i -> cards.get(i).compareToWithRule(otherHand.cards.get(i), playingWithJoker))
                .filter(i -> i != 0)
                .findFirst()
                .orElse(0);
    }
}
