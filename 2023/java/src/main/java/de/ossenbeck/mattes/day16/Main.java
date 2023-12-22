package de.ossenbeck.mattes.day16;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new TheFloorWillBeLava(InputReader.readAsList("main", "16"));
        puzzle.printParts();
    }
}