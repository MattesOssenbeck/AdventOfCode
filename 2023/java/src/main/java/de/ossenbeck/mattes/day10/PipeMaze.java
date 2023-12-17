package de.ossenbeck.mattes.day10;

import de.ossenbeck.mattes.Solveable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PipeMaze implements Solveable<Integer, Integer> {
    private static final char GROUND = '.';
    private final Map<Coordinate, Pipe> sketch;
    private final Coordinate endOfGrid;

    public PipeMaze(List<String> input) {
        this.sketch = IntStream.range(0, input.size())
                .mapToObj(y -> IntStream.range(0, input.get(y).length())
                        .filter(x -> input.get(y).charAt(x) != GROUND)
                        .mapToObj(x -> Pipe.of(input.get(y).charAt(x), new Coordinate(x, y))))
                .flatMap(pipes -> pipes)
                .collect(Collectors.toMap(Pipe::coordinate, pipe -> pipe));
        this.endOfGrid = new Coordinate(input.getLast().length() - 1, input.size() - 1);
    }

    @Override
    public Integer solvePartOne() {
        return findLoop().size() / 2;
    }

    @Override
    public Integer solvePartTwo() {
        var loop = findLoop();
        var loopMap = loop.stream().collect(Collectors.toMap(Pipe::coordinate,
                pipe -> pipe.identifier() != StartingPipe.identifier ? pipe
                        : Pipe.determinePipeWhichConnectsTo(pipe.coordinate(), loop.get(1), loop.getLast())));
        for (var y = 0; y <= endOfGrid.y(); y++) {
            for (var x = 0; x <= endOfGrid.x(); x++) {
                var c = new Coordinate(x, y);
                if (loopMap.containsKey(c)) {
                    continue;
                }
                //look left for every point, if hit walls is even == OUTSIDE -> ray casting algorithm
                //maybe caching already checked coordinates + result (if performance is bad)
            }
            System.out.println();
        }
        return null;
    }

    public List<Pipe> findLoop() {
        var start = sketch.values().stream()
                .filter(pipe -> pipe instanceof StartingPipe)
                .findAny()
                .orElseThrow();
        var allDirections = new ArrayList<>(List.of(Direction.values()));
        var loop = new ArrayList<Pipe>();
        var currentDirection = allDirections.removeFirst();
        var currentPipe = start;
        while (true) {
            loop.add(currentPipe);
            var coordinate = currentPipe.passThrough(currentDirection);
            currentDirection = currentPipe.determineDirectionToGoIn(currentDirection).opposite();
            currentPipe = sketch.get(coordinate);
            if (start.equals(currentPipe)) {
                break;
            }
            if (currentPipe == null) {
                loop.clear();
                currentPipe = start;
                currentDirection = allDirections.removeFirst();
            }
        }
        return loop;
    }
}
