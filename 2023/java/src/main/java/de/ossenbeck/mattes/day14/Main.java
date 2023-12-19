package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new ParabolicReflectorDish(InputReader.readAsList("main", "14"));
        puzzle.printParts();
    }
}
