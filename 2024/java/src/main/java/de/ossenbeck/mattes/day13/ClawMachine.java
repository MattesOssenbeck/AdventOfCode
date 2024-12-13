package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.common.Coordinate;

import static de.ossenbeck.mattes.common.Util.determinant;

public record ClawMachine(Coordinate a, Coordinate b, Coordinate p) {
    public long cost(long offset) {
        var px = p.x() + offset;
        var py = p.y() + offset;
        var detAB = determinant(a.x(), a.y(), b.x(), b.y());
        var detBP = determinant(px, py, b.x(), b.y());
        var detAP = determinant(a.x(), a.y(), px, py);

        if (detBP % detAB != 0 || detAP % detAB != 0) {
            return 0;
        }
        return detBP / detAB * 3 + detAP / detAB;
    }
}