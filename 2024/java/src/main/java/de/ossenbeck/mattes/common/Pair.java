package de.ossenbeck.mattes.common;

import java.util.function.BiFunction;

public record Pair<T, U>(T left, U right) {
    public <R> R applyLR(BiFunction<? super T, ? super U, ? extends R> f) {
        return f.apply(left, right);
    }

    public <R> R applyRL(BiFunction<? super U, ? super T, ? extends R> f) {
        return f.apply(right, left);
    }
}