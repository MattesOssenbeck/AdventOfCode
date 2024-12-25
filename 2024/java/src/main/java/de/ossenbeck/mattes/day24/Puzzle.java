package de.ossenbeck.mattes.day24;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle implements Solvable<Long, String> {
    private final Map<String, Supplier<Integer>> wiresAndGates = new HashMap<>();
    private final Map<String, Integer> output = new HashMap<>();
    private final List<String> unparsedInput;

    public Puzzle(InputReader inputReader) {
        var input = Util.DOUBLE_LINE_SEPARATOR.split(inputReader.asString());
        Util.LINE_SEPARATOR.splitAsStream(input[0])
                .map(line -> line.split(": "))
                .forEach(a -> output.put(a[0], Integer.parseInt(a[1])));

        this.unparsedInput = Util.LINE_SEPARATOR.splitAsStream(input[1]).toList();

        unparsedInput.stream()
                .map(line -> line.split(" "))
                .forEach(wiresAndGate -> wiresAndGates.put(wiresAndGate[4], () -> {
                    var a = output.getOrDefault(wiresAndGate[0], wiresAndGates.getOrDefault(wiresAndGate[0], () -> Integer.MIN_VALUE).get());
                    var b = output.getOrDefault(wiresAndGate[2], wiresAndGates.getOrDefault(wiresAndGate[2], () -> Integer.MIN_VALUE).get());
                    return switch (wiresAndGate[1]) {
                        case "XOR" -> a ^ b;
                        case "OR" -> a | b;
                        case "AND" -> a & b;
                        default -> throw new IllegalArgumentException("Unknown operation");
                    };
                }));
    }

    @Override
    public Long solvePartOne() {
        wiresAndGates.keySet().forEach(wire -> output.computeIfAbsent(wire, _ -> wiresAndGates.get(wire).get()));
        return Long.parseLong(getWiresWith("z"), 2);
    }

    @Override
    public String solvePartTwo() {
        //var x = getWiresWith("x");
        //var y = getWiresWith("y");
        //var result = Long.parseLong(x, 2) + Long.parseLong(y, 2);
        //var z = getWiresWith("z");
        //System.out.println("x: " + x);
        //System.out.println("y: " + y);
        //System.out.println("= " + Long.toBinaryString(result) + " (" + result + " expected)");
        //System.out.println("= " + z + " (" + Long.parseLong(z, 2) + " z)");
        //for (var i = 0; i < 45; i++) {
        //    var xWire = String.format("x%02d", i);
        //    printStructure(xWire);
        //}
        return Stream.of("grf", "wpq", "z36", "nwq", "z22", "mdb", "fvw", "z18")
                .sorted()
                .collect(Collectors.joining(","));
    }

    private String getWiresWith(String prefix) {
        return output.keySet().stream()
                .filter(wire -> wire.startsWith(prefix))
                .sorted((a, b) -> Integer.compare(Integer.parseInt(b.substring(1)), Integer.parseInt(a.substring(1))))
                .map(output::get)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void printStructure(String gate) {
        var xXORy = findWire(gate, "XOR");
        var xXORyResult = xXORy.split(" -> ")[1];
        var resultXOR___ = findWire(xXORyResult, "XOR");
        var resultAND___ = findWire(xXORyResult, "AND");

        var xANDy = findWire(gate, "AND");
        var xANDyResult = xANDy.split(" -> ")[1];
        var resultOR___ = findWire(xANDyResult, "OR");
        var ___result = resultOR___.split(" -> ")[1];
        var ___resultXOR___ = findWire(___result, "XOR");

        System.out.println("""
                %s -- %s
                		   \\_ %s ---
                				           |
                %s --------------- %s -- %s
                
                
                """.formatted(xXORy, resultXOR___, resultAND___, xANDy, resultOR___, ___resultXOR___));
    }

    private String findWire(String wire, String gate) {
        return unparsedInput.stream()
                .filter(e -> e.contains(wire + " " + gate) || e.contains(gate + " " + wire))
                .findAny()
                .orElse("not found");
    }
}
