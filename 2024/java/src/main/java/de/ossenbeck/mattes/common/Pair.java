package de.ossenbeck.mattes.common;

import java.util.function.BiFunction;

public record Tuple<T, U>(T _1, U _2) {
    public <R> R apply(BiFunction<? super T, ? super U, ? extends R> f) {
        return f.apply(_1, _2);
    }

    public <R> R map1(BiFunction<? super T, ? super U, ? extends R> mapper) {
        return mapper.apply(_1, _2);
    }

    public <R> R map2(BiFunction<? super U, ? super T, ? extends R> mapper) {
        return mapper.apply(_2, _1);
    }
}