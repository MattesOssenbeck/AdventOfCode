package de.ossenbeck.mattes.day11;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new GalaxyAnalyzer(InputReader.readAsList("main", "11", ""));
        puzzle.printParts();
    }
}
