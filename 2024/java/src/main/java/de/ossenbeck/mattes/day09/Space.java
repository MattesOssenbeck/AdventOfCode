package de.ossenbeck.mattes.day09;

import java.util.List;

public record Space(List<Integer> positions) {
    public int start() {
        return positions.getFirst();
    }

    public int length() {
        return positions.size();
    }
}