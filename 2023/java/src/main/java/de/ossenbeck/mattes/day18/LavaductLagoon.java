package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static de.ossenbeck.mattes.util.Util.area;

public class LavaductLagoon implements Solveable<Long, Long> {
    private final List<String> input;

    public LavaductLagoon(List<String> input) {
        this.input = input;
    }

    @Override
    public Long solvePartOne() {
        var digInstructions = input.stream()
                .map(instruction -> instruction.split(" "))
                .map(data -> new DigInstruction(data[0], Integer.parseInt(data[1])))
                .toList();
        return calculateAreaOfLagoon(digInstructions);
    }

    @Override
    public Long solvePartTwo() {
        var digInstructions = input.stream()
                .map(instruction -> instruction.split(" ")[2])
                .map(hexCode -> hexCode.substring(2, hexCode.length() - 1))
                .map(hexCode -> new DigInstruction(hexCode.charAt(hexCode.length() - 1) + "",
                        Integer.parseInt(hexCode.substring(0, hexCode.length() - 1), 16)))
                .toList();
        return calculateAreaOfLagoon(digInstructions);
    }

    private static long calculateAreaOfLagoon(List<DigInstruction> digInstructions) {
        var coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(0, 0));
        var trenches = digInstructions.stream()
                .map(digInstruction -> {
                    var last = coordinates.getLast();
                    var endY = last.y() + digInstruction.totalSteps() * digInstruction.direction().y();
                    var endX = last.x() + digInstruction.totalSteps() * digInstruction.direction().x();
                    coordinates.add(new Coordinate(endX, endY));
                    return digInstruction.totalSteps();
                })
                .reduce(0, Integer::sum);
        return area(coordinates) + 1 + trenches / 2;
    }
}
