package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new ScratchcardChecker(InputReader.readAsList("main", "04"));
        puzzle.printParts();
    }
}
