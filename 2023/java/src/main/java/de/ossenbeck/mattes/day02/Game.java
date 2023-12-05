package de.ossenbeck.mattes.day02;

import java.util.Comparator;
import java.util.List;

public record Game(int id, List<Grab> grabs) {
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    public boolean isPossible() {
        return grabs.stream()
                .noneMatch(grab -> grab.amountOf(Color.RED) > MAX_RED
                        || grab.amountOf(Color.GREEN) > MAX_GREEN
                        || grab.amountOf(Color.BLUE) > MAX_BLUE);
    }

    public int highestAmountOf(Color color) {
        return grabs.stream()
                .map(grab -> grab.amountOf(color))
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}