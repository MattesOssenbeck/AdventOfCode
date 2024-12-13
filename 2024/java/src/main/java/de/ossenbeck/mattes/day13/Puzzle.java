package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Util;

import java.util.List;

import static de.ossenbeck.mattes.common.Util.DOUBLE_LINE_SEPARATOR;
import static de.ossenbeck.mattes.common.Util.LINE_SEPARATOR;

public class Puzzle implements Solvable<Long, Long> {
    private final List<ClawMachine> clawMachines;

    public Puzzle(InputReader inputReader) {
        this.clawMachines = DOUBLE_LINE_SEPARATOR.splitAsStream(inputReader.asString())
                .map(line -> LINE_SEPARATOR.splitAsStream(line)
                        .map(Util::parseNumbers)
                        .map(n -> new Coordinate(n.getFirst(), n.getLast()))
                        .toList())
                .map(c -> new ClawMachine(c.getFirst(), c.get(1), c.getLast()))
                .toList();
    }

    @Override
    public Long solvePartOne() {
        return clawMachines.stream()
                .mapToLong(cm -> cm.cost(0))
                .sum();
    }

    @Override
    public Long solvePartTwo() {
        return clawMachines.stream()
                .mapToLong(cm -> cm.cost(10_000_000_000_000L))
                .sum();
    }
}
