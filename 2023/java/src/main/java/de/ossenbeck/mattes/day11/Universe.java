package de.ossenbeck.mattes.day11;

import java.util.List;
import java.util.stream.IntStream;

import static de.ossenbeck.mattes.Util.zip;

public class Universe {
    private final static char GALAXY = '#';
    private final List<String> rows;
    private final List<String> columns;
    private final List<Galaxy> galaxies;

    public Universe(List<String> rows) {
        this.rows = rows;
        this.columns = zip(rows);
        this.galaxies = findAllGalaxies();
    }

    private List<Galaxy> findAllGalaxies() {
        return IntStream.range(0, rows.size())
                .mapToObj(row -> IntStream.range(0, rows.get(row).length())
                        .filter(column -> rows.get(row).charAt(column) == GALAXY)
                        .mapToObj(column -> new Galaxy(row, column))
                        .toList())
                .flatMap(List::stream)
                .toList();
    }

    public boolean rowContainsGalaxy(int index) {
        return rows.get(index).contains(GALAXY + "");
    }

    public boolean columnContainsGalaxy(int index) {
        return columns.get(index).contains(GALAXY + "");
    }

    public List<Galaxy> getGalaxies() {
        return galaxies;
    }
}
