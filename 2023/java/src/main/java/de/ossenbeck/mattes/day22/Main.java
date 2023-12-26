package de.ossenbeck.mattes.day22;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new SandSlabs(InputReader.readAsList("main", "22"));
        puzzle.printParts();
    }
}