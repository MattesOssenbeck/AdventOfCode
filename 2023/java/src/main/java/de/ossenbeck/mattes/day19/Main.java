package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new Aplenty(InputReader.readAsString("main", "19"));
        puzzle.printParts();
    }
}