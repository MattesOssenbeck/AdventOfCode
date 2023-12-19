package de.ossenbeck.mattes.day18;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new LavaductLagoon(InputReader.readAsList("main", "18"));
        puzzle.printParts();
    }
}
