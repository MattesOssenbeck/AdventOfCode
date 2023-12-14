package de.ossenbeck.mattes.day07;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    private static final Map<List<Integer>, HandType> MAPPINGS = Map.of(
            List.of(5), FIVE_OF_A_KIND,
            List.of(4, 1), FOUR_OF_A_KIND,
            List.of(3, 2), FULL_HOUSE,
            List.of(3, 1, 1), THREE_OF_A_KIND,
            List.of(2, 2, 1), TWO_PAIR,
            List.of(2, 1, 1, 1), ONE_PAIR
    );

    public static HandType of(List<CamelCard> cards, boolean playingWithJoker) {
        var occurrences = cards.stream().collect(Collectors.toMap(card -> card, initialOccurrence -> 1, Integer::sum));
        var numberOfJokers = playingWithJoker && occurrences.containsKey(CamelCard.J) && occurrences.size() > 1
                ? occurrences.remove(CamelCard.J) : 0;
        var sortedOccurences = occurrences.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sortedOccurences.set(0, sortedOccurences.getFirst() + numberOfJokers);
        return MAPPINGS.getOrDefault(sortedOccurences, HIGH_CARD);
    }
}
