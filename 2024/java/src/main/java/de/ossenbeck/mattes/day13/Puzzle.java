package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;

import java.util.List;

import static de.ossenbeck.mattes.common.Util.*;

public class Puzzle implements Solvable<Long, Long> {
    private final List<ClawMachine> clawMachines;

    public Puzzle(InputReader inputReader) {
        this.clawMachines = DOUBLE_LINE_SEPARATOR.splitAsStream(inputReader.asString())
                .map(LINE_SEPARATOR::split)
                .map(machineBehavior -> {
                    var btnA = parseNumbers(machineBehavior[0]);
                    var btnB = parseNumbers(machineBehavior[1]);
                    var prize = parseNumbers(machineBehavior[2]);
                    return new ClawMachine(
                            new Coordinate(btnA.getFirst(), btnA.getLast()),
                            new Coordinate(btnB.getFirst(), btnB.getLast()),
                            new Coordinate(prize.getFirst(), prize.getLast())
                    );
                }).toList();
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
