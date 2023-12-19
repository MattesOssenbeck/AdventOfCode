package de.ossenbeck.mattes.day14;

import java.util.Arrays;

public record State(char[][] platform) {
    public State(char[][] platform) {
        this.platform = Arrays.stream(platform)
                .map(char[]::clone)
                .toArray(char[][]::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var state = (State) o;
        return Arrays.deepEquals(platform, state.platform);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(platform);
    }
}
