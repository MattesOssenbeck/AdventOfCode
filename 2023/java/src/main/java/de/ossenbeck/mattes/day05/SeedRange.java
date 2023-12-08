package de.ossenbeck.mattes.day05;

public record SeedRange(long start, long end) {
    public boolean isInRange(long seed) {
        return seed >= start && seed <= end;
    }
}
