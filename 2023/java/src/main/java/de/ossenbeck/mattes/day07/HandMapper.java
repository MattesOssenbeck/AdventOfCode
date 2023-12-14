package de.ossenbeck.mattes.day07;

import java.util.List;
import java.util.stream.IntStream;

public class HandMapper {
    public static List<Hand> map(List<String> input) {
        return input.stream()
                .map(line -> line.split(" "))
                .map(data -> new Hand(map(data[0]), Integer.parseInt(data[1])))
                .toList();
    }

    private static List<CamelCard> map(String cards) {
        return IntStream.range(0, cards.length())
                .mapToObj(cards::charAt)
                .map(CamelCard::of)
                .toList();
    }
}
