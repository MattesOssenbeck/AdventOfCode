package de.ossenbeck.mattes.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
    public static List<String> zip(List<String> rows) {
        return IntStream.range(0, rows.getFirst().length())
                .mapToObj(column -> rows.stream()
                        .map(row -> row.charAt(column))
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .toList();
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}