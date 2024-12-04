package de.ossenbeck.mattes.common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    //Gauß'sche Trapezformel (engl. Shoelace formula)
    public static long area(List<Coordinate> c) {
        return Math.abs(IntStream.range(0, c.size())
                .mapToLong(i -> (long) c.get(i).x() * (long) (c.get((i + 1) % c.size()).y() - c.get((i - 1 + c.size()) % c.size()).y()))
                .sum()) / 2;
    }

    public static Stream<Coordinate> traverseGrid(char[][] grid) {
        return IntStream.range(0, grid.length)
                .boxed()
                .flatMap(y -> IntStream.range(0, grid[y].length).mapToObj(x -> new Coordinate(x, y)));
    }
}