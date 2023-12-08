package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Tuple3;

import java.util.Arrays;
import java.util.List;

public class CategoryMapper {
    public static Tuple3<List<Long>, CategoryMap, CategoryMap> map(String input) {
        var data = input.split(System.lineSeparator() + System.lineSeparator());
        var seeds = mapNumbers(data[0].split(": ")[1]);
        return new Tuple3<>(seeds, mapCategoryMap(data), mapCategoryMapReversed(data));
    }

    private static CategoryMap mapCategoryMap(String[] data) {
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
        return categoryMap;
    }

    private static CategoryMap mapCategoryMapReversed(String[] data) {
        var categoryMaps = Arrays.stream(data)
                .skip(1)
                .map(CategoryMapper::mapRanges)
                .map(CategoryMap::new)
                .toList();
        var categoryMapReversed = categoryMaps.get(categoryMaps.size() - 1);
        var tmp = categoryMapReversed;
        for (var i = categoryMaps.size() - 2; i >= 0; i--) {
            tmp.setDestination(categoryMaps.get(i));
            tmp = categoryMaps.get(i);
        }
        return categoryMapReversed;
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
