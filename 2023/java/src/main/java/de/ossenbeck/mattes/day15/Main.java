package de.ossenbeck.mattes.day15;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new LensLibrary(InputReader.readAsString("main", "15"));
        puzzle.printParts();
    }
}