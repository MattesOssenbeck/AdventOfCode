package de.ossenbeck.mattes.util;

import java.util.function.BiFunction;

public record Tuple<T, U>(T first, U second) {
    public <R> R apply(BiFunction<? super T, ? super U, ? extends R> f) {
        return f.apply(first, second);
    }
}