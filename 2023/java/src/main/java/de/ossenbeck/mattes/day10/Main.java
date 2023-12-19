package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new PipeMaze(InputReader.readAsList("main", "10"));
        puzzle.printParts();
    }
}
