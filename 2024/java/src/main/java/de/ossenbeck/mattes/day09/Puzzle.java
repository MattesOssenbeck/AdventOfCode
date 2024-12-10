package de.ossenbeck.mattes.day09;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

public class Puzzle implements Solvable<Long, Long> {
    private final Disk disk1;
    private final Disk disk2;

    public Puzzle(InputReader inputReader) {
        var diskMap = inputReader.asString();
        this.disk1 = new Disk(diskMap);
        this.disk2 = new Disk(diskMap);
    }

    @Override
    public Long solvePartOne() {
        disk1.compact();
        return disk1.checksum();
    }

    @Override
    public Long solvePartTwo() {
        disk2.defragment();
        return disk2.checksum();
    }
}
