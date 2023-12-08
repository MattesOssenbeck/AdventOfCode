package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Tuple;

import java.util.Arrays;
import java.util.List;

public class CategoryMapper {
    public static Tuple<List<Long>, CategoryMap> map(String input) {
        var data = input.split(System.lineSeparator() + System.lineSeparator());
        var seeds = mapNumbers(data[0].split(": ")[1]);
        var categoryMaps = Arrays.stream(data)
                .skip(1)
                .map(CategoryMapper::mapRanges)
                .map(CategoryMap::new)
                .toList();
        var categoryMap = categoryMaps.get(0);
        var tmp = categoryMap;
        for (var i = 1; i < categoryMaps.size(); i++) {
            tmp.setDestination(categoryMaps.get(i));
            tmp = categoryMaps.get(i);
        }
        return new Tuple<>(seeds, categoryMap);
    }

    private static List<Range> mapRanges(String category) {
        return Arrays.stream(category.split(System.lineSeparator()))
                .skip(1)
                .map(CategoryMapper::mapRange)
                .toList();
    }

    private static Range mapRange(String line) {
        var numbers = mapNumbers(line);
        return new Range(numbers.get(0), numbers.get(1), numbers.get(2));
    }

    private static List<Long> mapNumbers(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .map(Long::parseLong)
                .toList();
    }
}
