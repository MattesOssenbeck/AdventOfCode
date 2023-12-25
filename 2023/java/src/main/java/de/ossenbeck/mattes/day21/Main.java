package de.ossenbeck.mattes.day21;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new StepCounter(InputReader.readAsList("main", "21"));
        puzzle.printParts();
    }
}
