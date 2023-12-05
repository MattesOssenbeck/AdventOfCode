package de.ossenbeck.mattes.day02;

import java.util.List;

public record Grab(List<Cube> cubes) {
    public int amountOf(Color color) {
        return cubes.stream()
                .filter(cube -> cube.color().equals(color))
                .findAny()
                .map(Cube::amount)
                .orElse(0);
    }
}