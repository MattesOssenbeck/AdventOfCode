package de.ossenbeck.mattes.day04;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ScratchcardMapper {
    private static final Pattern MULTIPLE_SPACES = Pattern.compile("\\s+");

    public static List<Scratchcard> map(List<String> input) {
        return input.stream()
                .map(ScratchcardMapper::map)
                .toList();
    }

    private static Scratchcard map(String card) {
        var data = card.split(": ");
        var numbers = data[1].split(" \\| ");
        var cardId = Integer.parseInt(clean(data[0]).split(" ")[1]);
        return new Scratchcard(cardId, mapNumbers(numbers[0]), mapNumbers(numbers[1]));
    }

    private static List<Integer> mapNumbers(String numbers) {
        return Arrays.stream(clean(numbers).split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    private static String clean(String line) {
        return MULTIPLE_SPACES.matcher(line).replaceAll(" ").trim();
    }
}
