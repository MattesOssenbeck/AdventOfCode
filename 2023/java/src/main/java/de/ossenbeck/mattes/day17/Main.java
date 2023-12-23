package de.ossenbeck.mattes.day17;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new ClumsyCrucible(InputReader.readAsList("main", "17"));
        puzzle.printParts();
    }
}
