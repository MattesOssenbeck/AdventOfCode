package de.ossenbeck.mattes.day14;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Coordinate;
import de.ossenbeck.mattes.util.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParabolicReflectorDish implements Solveable<Integer, Integer> {
    private static final char EMPTY = '.';
    private final Map<Coordinate, Rock> platform;
    private final Coordinate endOfPlatform;
    private final Map<Map<Coordinate, Rock>, Integer> platformCache = new HashMap<>();

    public ParabolicReflectorDish(List<String> input) {
        this.platform = IntStream.range(0, input.size())
                .mapToObj(y -> IntStream.range(0, input.get(y).length())
                        .filter(x -> input.get(y).charAt(x) != EMPTY)
                        .mapToObj(x -> new Coordinate(x, y)))
                .flatMap(coordinates -> coordinates)
                .collect(Collectors.toMap(coordinate -> coordinate, c -> Rock.of(input.get(c.y()).charAt(c.x()))));
        this.endOfPlatform = new Coordinate(input.getLast().length() - 1, input.size() - 1);
    }

    @Override
    public Integer solvePartOne() {
        tiltPlatform(Direction.NORTH);
        return measureLoad(platform);
    }


    @Override
    public Integer solvePartTwo() {
        var cycle = 0;
        do {
            platformCache.put(new HashMap<>(platform), cycle++);
            completeOneCycle();
        } while (!platformCache.containsKey(platform));
        var firstEncounter = platformCache.get(platform);
        var cycleLength = cycle - firstEncounter;
        return platformCache.entrySet().stream()
                .filter(e -> e.getValue() == (1_000_000_000 - firstEncounter) % cycleLength + firstEncounter)
                .findFirst()
                .map(Map.Entry::getKey)
                .map(this::measureLoad)
                .orElseThrow();
    }

    private int measureLoad(Map<Coordinate, Rock> platform) {
        return IntStream.rangeClosed(0, endOfPlatform.y())
                .map(y -> IntStream.rangeClosed(0, endOfPlatform.x())
                        .filter(x -> Rock.ROUNDED.equals(platform.get(new Coordinate(x, y))))
                        .map(__ -> endOfPlatform.y() + 1 - y)
                        .sum())
                .sum();
    }

    private void completeOneCycle() {
        tiltPlatform(Direction.NORTH);
        tiltPlatform(Direction.WEST);
        tiltPlatform(Direction.SOUTH);
        tiltPlatform(Direction.EAST);
    }

    private void tiltPlatform(Direction direction) {
        for (var y = direction.equals(Direction.SOUTH) ? endOfPlatform.y() : 0;
             direction.equals(Direction.SOUTH) ? y >= 0 : y <= endOfPlatform.y();
             y += direction.equals(Direction.SOUTH) ? -1 : 1) {
            for (var x = direction.equals(Direction.EAST) ? endOfPlatform.x() : 0;
                 direction.equals(Direction.EAST) ? x >= 0 : x <= endOfPlatform.x();
                 x += direction.equals(Direction.EAST) ? -1 : 1) {
                var rock = platform.get(new Coordinate(x, y));
                if (Rock.ROUNDED.equals(rock)) {
                    for (int stepsX = x + direction.x(), stepsY = y + direction.y();
                         (direction.equals(Direction.WEST) ? stepsX >= 0 : stepsX <= endOfPlatform.x())
                                 && (direction.equals(Direction.NORTH) ? stepsY >= 0 : stepsY <= endOfPlatform.y());
                         stepsX += direction.x(), stepsY += direction.y()) {
                        var newPosition = new Coordinate(stepsX, stepsY);
                        if (platform.containsKey(newPosition)) {
                            break;
                        }
                        platform.put(newPosition, rock);
                        platform.remove(new Coordinate(newPosition.x() - direction.x(), newPosition.y() - direction.y()));
                    }
                }
            }
        }
    }
}
