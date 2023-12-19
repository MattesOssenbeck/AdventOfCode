package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new PointOfIncidence(InputReader.readAsString("main", "13"));
        puzzle.printParts();
    }
}
