package de.ossenbeck.mattes.day01;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new CalibrationValueProcessor(InputReader.readAsList("main", "01"));
        puzzle.printParts();
    }
}
