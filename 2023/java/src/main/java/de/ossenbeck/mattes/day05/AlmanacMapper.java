package de.ossenbeck.mattes.day05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AlmanacMapper {
    public static AlmanacMappingResult map(String input) {
        var data = input.split(System.lineSeparator() + System.lineSeparator());
        var seeds = mapNumbers(data[0].split(": ")[1]);
        return new AlmanacMappingResult(seeds, mapCategoryMap(data));
    }

    private static CategoryMap mapCategoryMap(String[] data) {
        var categoryMaps = Arrays.stream(data)
                .skip(1)
                .map(AlmanacMapper::mapRanges)
                .map(CategoryMap::new)
                .toList();
        for (var i = 0; i < categoryMaps.size() - 1; i++) {
            categoryMaps.get(i).setDestination(categoryMaps.get(i + 1));
        }
        return categoryMaps.getFirst();
    }

    private static List<CategoryMapRange> mapRanges(String category) {
        return Arrays.stream(category.split(System.lineSeparator()))
                .skip(1)
                .map(AlmanacMapper::mapRange)
                .sorted(Comparator.comparingLong(Range::start))
                .toList();
    }

    private static CategoryMapRange mapRange(String line) {
        var numbers = mapNumbers(line);
        return new CategoryMapRange(numbers.get(0), numbers.get(1), numbers.get(2));
    }

    private static List<Long> mapNumbers(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .map(Long::parseLong)
                .toList();
    }
}
