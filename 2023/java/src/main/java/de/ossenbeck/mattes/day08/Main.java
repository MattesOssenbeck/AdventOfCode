package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new HauntedWasteland(InputReader.readAsList("main", "08", ""));
        puzzle.printParts();
    }
}