package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Util;

import java.util.ArrayList;

public class Puzzle implements Solvable<String, Long> {
    private final long initialA;
    private final String program;

    public Puzzle(InputReader inputReader) {
        var input = inputReader.asList();
        this.initialA = Util.parseNumbers(input.getFirst()).getFirst();
        this.program = input.getLast().split(" ")[1];
    }

    @Override
    public String solvePartOne() {
        return runOperations(initialA);
    }

    @Override
    public Long solvePartTwo() {
        var initA = 0L;
        var n = 1;
        while (true) {
            var result = runOperations(initA);
            if (result.equals(program)) {
                return initA;
            }
            if (n <= result.length() && program.endsWith(result.substring(result.length() - n))) {
                n += 2;
                initA *= 8;
                continue;
            }
            initA++;
        }
    }

    public String runOperations(long initA) {
        var output = new ArrayList<String>();
        var a = initA;
        var b = 0L;
        var c = 0L;
        for (var pointer = 0; pointer <= program.length() - 3; pointer += 4) {
            var opcode = program.charAt(pointer);
            var operand = Character.getNumericValue(program.charAt(pointer + 2));
            switch (opcode) {
                //adv
                case '0' -> a = a >> combo(operand, a, b, c);
                //bxl
                case '1' -> b = b ^ operand;
                //bst
                case '2' -> b = combo(operand, a, b, c) & 7;
                //jnz
                case '3' -> pointer = a != 0 ? operand - 4 : pointer;
                //bxc
                case '4' -> b = b ^ c;
                //out
                case '5' -> output.add(String.valueOf(combo(operand, a, b, c) & 7));
                //bdv
                case '6' -> b = a >> combo(operand, a, b, c);
                //cdv
                case '7' -> c = a >> combo(operand, a, b, c);
                default -> throw new IllegalArgumentException("Unknown opcode " + opcode);
            }
        }
        return String.join(",", output);
    }

    private long combo(int operand, long a, long b, long c) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> a;
            case 5 -> b;
            case 6 -> c;
            default -> throw new IllegalArgumentException("Unknown operand: " + operand);
        };
    }
}