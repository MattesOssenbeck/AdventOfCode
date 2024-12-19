package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.common.Coordinate;

import java.util.HashSet;
import java.util.Set;

public record Step(Coordinate coordinate, Step previous) {
    public Integer count() {
        return previous != null ? previous.count() + 1 : 0;
    }

    public Set<Coordinate> path() {
        var path = new HashSet<Coordinate>();
        addSelf(path);
        return path;
    }

    private void addSelf(Set<Coordinate> path) {
        path.add(coordinate);
        if (previous != null) {
            previous.addSelf(path);
        }
    }
}