package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.util.Direction;

import java.util.Objects;

public record State(CityBlock cityBlock, Direction direction, int steps, int currentHeatLoss)
        implements Comparable<State> {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var state = (State) o;
        return steps == state.steps && Objects.equals(cityBlock, state.cityBlock) && direction.equals(state.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityBlock, direction, steps);
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(currentHeatLoss, o.currentHeatLoss);
    }
}