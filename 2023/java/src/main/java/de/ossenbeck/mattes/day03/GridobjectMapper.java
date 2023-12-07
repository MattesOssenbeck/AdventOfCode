package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GridobjectMapper {
    public static Tuple<List<Symbol>, List<Number>> map(List<String> input) {
        var symbols = new ArrayList<Symbol>();
        var numbers = new ArrayList<Number>();
        IntStream.range(0, input.size()).forEach(i -> map(input.get(i), i, symbols, numbers));
        return new Tuple<>(symbols, numbers);
    }

    private static void map(String line, int y, List<Symbol> symbols, List<Number> numbers) {
        for (var i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                var xStart = i;
                var value = new StringBuilder().append(line.charAt(i));
                while (++i < line.length() && Character.isDigit(line.charAt(i))) {
                    value.append(line.charAt(i));
                }
                numbers.add(new Number(Integer.parseInt(value.toString()), xStart, --i, y));
            } else if (line.charAt(i) != '.') {
                symbols.add(new Symbol(line.charAt(i), i, y));
            }
        }
    }
}
