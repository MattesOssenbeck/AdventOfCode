package de.ossenbeck.mattes.day06;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new RaceRecordCalculator(InputReader.readAsString("main", "06", ""));
        puzzle.printParts();
    }
}
