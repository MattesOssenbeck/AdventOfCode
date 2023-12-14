package de.ossenbeck.mattes.day07;

import java.util.Arrays;

public enum CamelCard {
    JOKER('_'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    T('T'),
    J('J'),
    Q('Q'),
    K('K'),
    A('A');

    private final char identifier;

    CamelCard(char identifier) {
        this.identifier = identifier;
    }

    public static CamelCard of(char identifier) {
        return Arrays.stream(values())
                .filter(camelCard -> camelCard.identifier == identifier)
                .findAny()
                .orElseThrow();
    }

    public int compareToWithRule(CamelCard otherCamelCard, boolean playingWithJoker) {
        if (!playingWithJoker) {
            return this.compareTo(otherCamelCard);
        }
        return determineStrength(this) - determineStrength(otherCamelCard);
    }

    private static int determineStrength(CamelCard camelCard) {
        return camelCard.equals(J) ? JOKER.ordinal() : camelCard.ordinal();
    }
}