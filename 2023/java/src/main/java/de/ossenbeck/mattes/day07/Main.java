package de.ossenbeck.mattes.day07;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new CamelCardGame(InputReader.readAsList("main", "07", ""));
        puzzle.printParts();
    }
}
