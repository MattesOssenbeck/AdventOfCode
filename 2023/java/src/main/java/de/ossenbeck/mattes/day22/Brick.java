package de.ossenbeck.mattes.day22;

import de.ossenbeck.mattes.util.Range;

public record Brick(Range x, Range y, Range z) {
    public boolean overlap(Brick brick) {
        return Math.max(x.start(), brick.x.start()) <= Math.min(x.end(), brick.x.end())
                && Math.max(y.start(), brick.y.start()) <= Math.min(y.end(), brick.y.end());
    }
}