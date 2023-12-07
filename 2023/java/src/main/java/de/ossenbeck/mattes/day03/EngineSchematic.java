package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.Solveable;

import java.util.List;

import static java.util.function.Predicate.not;

public class EngineSchematic implements Solveable<Integer, Integer> {
    private final List<Symbol> symbols;
    private final List<Number> numbers;

    public EngineSchematic(List<String> input) {
        var tuple = GridobjectMapper.map(input);
        this.symbols = tuple.first();
        this.numbers = tuple.second();
        checkCollisions();
    }

    @Override
    public Integer solvePartOne() {
        return symbols.stream()
                .map(Symbol::getAdjacentNumbers)
                .filter(not(List::isEmpty))
                .flatMap(List::stream)
                .map(Number::value)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    @Override
    public Integer solvePartTwo() {
        return symbols.stream()
                .map(Symbol::getGearRatio)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    private void checkCollisions() {
        symbols.forEach(symbol -> findAdjacentNumbers(symbol, numbers));
    }

    private static void findAdjacentNumbers(Symbol symbol, List<Number> numbers) {
        numbers.stream()
                .filter(symbol::isAdjacent)
                .forEach(symbol::addAdjacentNumber);
    }
}
