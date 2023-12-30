package de.ossenbeck.mattes.day24;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new NeverTellMeTheOdds(InputReader.readAsList("main", "24"));
        puzzle.printParts();
    }
}