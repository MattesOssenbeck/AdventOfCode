package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new Almanac(InputReader.readAsString("main", "05", ""));
        puzzle.printParts();
    }
}
