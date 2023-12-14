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

    private static final Map<Integer, HandType> MAPPINGS = Map.of(
            50, FIVE_OF_A_KIND,
            41, FOUR_OF_A_KIND,
            32, FULL_HOUSE,
            31, THREE_OF_A_KIND,
            22, TWO_PAIR,
            21, ONE_PAIR
    );

    public static HandType of(List<CamelCard> cards, boolean playingWithJoker) {
        var occurrences = cards.stream().collect(Collectors.toMap(card -> card, initialOccurrence -> 1, Integer::sum));
        var numberOfJokers = playingWithJoker && occurrences.containsKey(CamelCard.J) ? occurrences.remove(CamelCard.J) : 0;
        var sortedOccurences = occurrences.values().stream().sorted(Comparator.reverseOrder()).toList();
        var key = (sortedOccurences.isEmpty() ? numberOfJokers : sortedOccurences.getFirst() + numberOfJokers) * 10;
        key += sortedOccurences.size() > 1 ? sortedOccurences.get(1) : 0;
        return MAPPINGS.getOrDefault(key, HIGH_CARD);
    }
}
