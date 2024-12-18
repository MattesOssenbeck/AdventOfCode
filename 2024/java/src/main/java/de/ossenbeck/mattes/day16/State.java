package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.common.Position;

public record State(Position position, int score, State previous) implements Comparable<State> {
    @Override
    public int compareTo(State o) {
        return Integer.compare(score, o.score);
    }
}