package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.day10.pipetype.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

public class PipeMaze implements Solveable<Integer, Integer> {
    private static final char GROUND = '.';
    private final Map<Coordinate, Pipe> sketch;

    public PipeMaze(List<String> input) {
        this.sketch = IntStream.range(0, input.size())
                .mapToObj(y -> IntStream.range(0, input.get(y).length())
                        .filter(x -> input.get(y).charAt(x) != GROUND)
                        .mapToObj(x -> Pipe.of(input.get(y).charAt(x), new Coordinate(x, y))))
                .flatMap(pipes -> pipes)
                .collect(Collectors.toMap(Pipe::coordinate, pipe -> pipe));
    }

    @Override
    public Integer solvePartOne() {
        return findLoop().size() / 2;
    }

    @Override
    public Integer solvePartTwo() {
        var loop = findLoop();
        var loopMap = loop.stream()
                .collect(Collectors.toMap(Pipe::coordinate, pipe -> Pipe.toValidPipe(pipe, loop.get(1), loop.getLast())));
        var lowerBound = Coordinate.determineLowerBound(loopMap.keySet());
        var upperBound = Coordinate.determineUpperBound(loopMap.keySet());

        return IntStream.rangeClosed(lowerBound.y(), upperBound.y())
                .map(y -> (int) IntStream.rangeClosed(lowerBound.x(), upperBound.x())
                        .mapToObj(x -> new Coordinate(x, y))
                        .filter(not(loopMap::containsKey))
                        .map(tileCoordinate -> calculateNumberOfWallsHitByRay(lowerBound.x(), tileCoordinate, loopMap))
                        .filter(PipeMaze::isEnclosedByLoop)
                        .count())
                .reduce(0, Integer::sum);
    }

    private List<Pipe> findLoop() {
        var start = sketch.values().stream()
                .filter(pipe -> pipe instanceof StartingPipe)
                .findAny()
                .orElseThrow();
        var allDirections = new ArrayList<>(List.of(Direction.values()));
        var loop = new ArrayList<Pipe>();
        var currentDirection = allDirections.removeFirst();
        var currentPipe = start;
        do {
            if (currentPipe == null) {
                loop.clear();
                currentPipe = start;
                currentDirection = allDirections.removeFirst();
            }
            loop.add(currentPipe);
            var coordinate = currentPipe.passThrough(currentDirection);
            currentDirection = currentPipe.determineDirectionToGoIn(currentDirection).opposite();
            currentPipe = sketch.get(coordinate);
        } while (!start.equals(currentPipe));
        return loop;
    }

    private static int calculateNumberOfWallsHitByRay(int lowerBoundX, Coordinate coordinate, Map<Coordinate, Pipe> loop) {
        var walls = createPotentialWallsMap();
        IntStream.rangeClosed(lowerBoundX, coordinate.x())
                .mapToObj(rayX -> new Coordinate(rayX, coordinate.y()))
                .filter(loop::containsKey)
                .map(loop::get)
                .forEach(pipe -> walls.computeIfPresent(pipe.identifier(), (__, i) -> i + 1));

        return walls.get(NorthAndSouthPipe.identifier)
                + Math.min(walls.get(NorthAndEastPipe.identifier), walls.get(SouthAndWestPipe.identifier))
                + Math.min(walls.get(SouthAndEastPipe.identifier), walls.get(NorthAndWestPipe.identifier));
    }

    private static boolean isEnclosedByLoop(int numberOfWallHits) {
        return numberOfWallHits % 2 != 0;
    }

    private static Map<Character, Integer> createPotentialWallsMap() {
        return new HashMap<>(Map.of(
                NorthAndSouthPipe.identifier, 0,
                SouthAndWestPipe.identifier, 0,
                NorthAndEastPipe.identifier, 0,
                SouthAndEastPipe.identifier, 0,
                NorthAndWestPipe.identifier, 0
        ));
    }
}
