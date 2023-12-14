package de.ossenbeck.mattes;

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
}
