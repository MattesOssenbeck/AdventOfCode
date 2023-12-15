package de.ossenbeck.mattes.day12;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new HotSprings(InputReader.readAsList("main", "12", ""));
        puzzle.printParts();
    }
}