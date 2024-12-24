package de.ossenbeck.mattes.day22;

import java.util.List;

public record Sequence(long a, long b, long c, long d, long price) {
    public static Sequence of(List<Long> prices) {
        return new Sequence(
                prices.get(1) - prices.get(0),
                prices.get(2) - prices.get(1),
                prices.get(3) - prices.get(2),
                prices.get(4) - prices.get(3),
                prices.get(4)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sequence sequence)) return false;
        return a == sequence.a && b == sequence.b && c == sequence.c && d == sequence.d;
    }

    @Override
    public int hashCode() {
        var result = Long.hashCode(a);
        result = 31 * result + Long.hashCode(b);
        result = 31 * result + Long.hashCode(c);
        result = 31 * result + Long.hashCode(d);
        return result;
    }
}