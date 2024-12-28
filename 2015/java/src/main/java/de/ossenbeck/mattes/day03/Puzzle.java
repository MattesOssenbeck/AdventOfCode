package de.ossenbeck.mattes.day03;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Coordinate;
import de.ossenbeck.mattes.common.Direction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import static de.ossenbeck.mattes.common.Util.SINGLE_CHAR_SEPARATOR;

public class Puzzle implements Solvable<Integer, Integer> {
    private final List<Direction> directions;

    public Puzzle(InputReader inputReader) {
        this.directions = SINGLE_CHAR_SEPARATOR.splitAsStream(inputReader.asString())
                .map(Direction::of)
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        return deliverHouses(_ -> true);
    }

    @Override
    public Integer solvePartTwo() {
        return deliverHouses(isSanta -> !isSanta);
    }

    private int deliverHouses(Function<Boolean, Boolean> next) {
        var houses = new HashSet<Coordinate>();
        var positions = new HashMap<Boolean, Coordinate>();
        positions.put(true, new Coordinate(0, 0));
        positions.put(false, new Coordinate(0, 0));
        var isSanta = true;
        houses.add(positions.get(isSanta));
        for (var direction : directions) {
            houses.add(positions.compute(isSanta, (_, current) -> current.move(direction)));
            isSanta = next.apply(isSanta);
        }
        return houses.size();
    }
}
