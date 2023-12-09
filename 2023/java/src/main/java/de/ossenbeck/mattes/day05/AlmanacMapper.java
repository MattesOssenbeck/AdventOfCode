package de.ossenbeck.mattes.day05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AlmanacMapper {
    public static AlmanacMappingResult map(String input) {
        var data = input.split(System.lineSeparator() + System.lineSeparator());
        var seeds = mapNumbers(data[0].split(": ")[1]);
        var categoryMaps = mapCategoryMap(data);
        return new AlmanacMappingResult(seeds, categoryMaps.getFirst(), categoryMaps.getLast());
    }

    private static List<CategoryMap> mapCategoryMap(String[] data) {
        var categoryMaps = Arrays.stream(data)
                .skip(1)
                .map(AlmanacMapper::mapRanges)
                .map(CategoryMap::new)
                .toList();
        for (var i = 0; i < categoryMaps.size() - 1; i++) {
            var source = categoryMaps.get(i);
            var destination = categoryMaps.get(i + 1);
            source.setDestination(destination);
            destination.setSource(source);
        }
        return categoryMaps;
    }

    private static List<Range> mapRanges(String category) {
        return Arrays.stream(category.split(System.lineSeparator()))
                .skip(1)
                .map(AlmanacMapper::mapRange)
                .sorted(Comparator.comparingLong(Range::getSource))
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
