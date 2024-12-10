package de.ossenbeck.mattes.day09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Disk {
    private final List<Space> spaces = new ArrayList<>();
    private final List<File> files = new ArrayList<>();

    public Disk(String diskMap) {
        var position = 0;
        for (var i = 0; i < diskMap.length(); i++) {
            var length = Character.getNumericValue(diskMap.charAt(i));
            var positions = IntStream.range(position, position + length).boxed()
                    .collect(Collectors.toCollection(LinkedList::new));
            if (i % 2 == 0) {
                files.add(new File(i / 2, positions));
            } else {
                spaces.add(new Space(positions));
            }
            position += length;
        }
    }

    public void compact() {
        var freePositions = getFreeAllFreePositions();
        var lowestFreePosition = freePositions.removeFirst();
        for (var file : files.reversed()) {
            var positions = file.positions();
            for (var j = file.length() - 1; j >= 0; j--) {
                if (lowestFreePosition > positions.get(j)) {
                    return;
                }
                positions.set(j, lowestFreePosition);
                lowestFreePosition = freePositions.removeFirst();
            }
        }
    }

    private List<Integer> getFreeAllFreePositions() {
        return spaces.stream()
                .map(Space::positions)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void defragment() {
        for (var file : files.reversed()) {
            var space = findEnoughFreeSpace(file.length());
            var positions = file.positions();
            if (space != null && space.start() < positions.getFirst()) {
                positions.replaceAll(_ -> space.positions().removeFirst());
                if (space.length() == 0) {
                    spaces.remove(space);
                }
            }
        }
    }

    private Space findEnoughFreeSpace(int length) {
        for (var space : spaces) {
            if (space.length() >= length) {
                return space;
            }
        }
        return null;
    }

    public long checksum() {
        return files.stream()
                .mapToLong(File::checksum)
                .sum();
    }
}