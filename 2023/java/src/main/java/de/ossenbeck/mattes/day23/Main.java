package de.ossenbeck.mattes.day23;

import de.ossenbeck.mattes.InputReader;

public class Main {
    public static void main(String[] args) {
        var puzzle = new ALongWalk(InputReader.readAsList("main", "23"));
        puzzle.printParts();
    }
}
