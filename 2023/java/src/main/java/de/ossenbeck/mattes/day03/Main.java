package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new EngineSchematic(InputReader.readAsList("main", "03"));
        puzzle.printParts();
    }
}