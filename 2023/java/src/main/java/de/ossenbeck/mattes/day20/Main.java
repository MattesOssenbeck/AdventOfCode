package de.ossenbeck.mattes.day20;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new PulsePropagation(InputReader.readAsList("main", "20"));
        puzzle.printParts();
    }
}