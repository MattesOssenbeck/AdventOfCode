package de.ossenbeck.mattes.day09;

import java.util.List;

public record File(int id, List<Integer> positions) {
    public int length() {
        return positions.size();
    }

    public long checksum() {
        return positions.stream()
                .mapToLong(position -> (long) position * id)
                .sum();
    }
}