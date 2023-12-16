package de.ossenbeck.mattes.day09;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new MirageMaintenance(InputReader.readAsList("main", "09", ""));
        puzzle.printParts();
    }
}
