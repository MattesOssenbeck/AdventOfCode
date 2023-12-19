package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new CubeConundrum(InputReader.readAsList("main", "02"));
        puzzle.printParts();
    }
}
