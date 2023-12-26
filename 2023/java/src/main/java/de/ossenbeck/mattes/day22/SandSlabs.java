package de.ossenbeck.mattes.day22;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Range;
import de.ossenbeck.mattes.util.Tuple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

public class SandSlabs implements Solveable<Integer, Integer> {

    private final List<Brick> bricks;
    private final Map<Long, Set<Brick>> bricksOnZ = new HashMap<>();
    private final Map<Brick, Integer> chainReactionResult;

    public SandSlabs(List<String> input) {
        this.bricks = input.stream()
                .map(line -> line.replace("~", ",").split(","))
                .map(nums -> IntStream.rangeClosed(0, nums.length / 3)
                        .mapToObj(i -> new Range(Long.parseLong(nums[i]), Long.parseLong(nums[i + 3])))
                        .collect(Collectors.toList()))
                .map(ranges -> new Brick(ranges.removeFirst(), ranges.removeFirst(), ranges.removeFirst()))
                .sorted(Comparator.comparingLong(brick -> brick.z().start()))
                .collect(Collectors.toList());
        chainReactionResult = simulateChainReaction();
    }


    @Override
    public Integer solvePartOne() {
        return (int) chainReactionResult.values().stream()
                .filter(fallingBricks -> fallingBricks == 0)
                .count();
    }

    @Override
    public Integer solvePartTwo() {
        return chainReactionResult.values().stream()
                .reduce(0, Integer::sum);
    }

    private Tuple<Map<Brick, Set<Brick>>, Map<Brick, Set<Brick>>> simulateFallingBricks() {
        var supportedBy = new HashMap<Brick, Set<Brick>>();
        var supportedBricks = new HashMap<Brick, Set<Brick>>();
        for (var brick : bricks) {
            for (var z = brick.z().start() - 1; z > 0; z--) {
                var bricksAtZ = bricksOnZ.getOrDefault(z, Set.of());
                if (bricksAtZ.stream().anyMatch(brick::overlap)) {
                    bricksAtZ.stream().filter(brick::overlap).forEach(supportingBrick -> {
                        supportedBy.putIfAbsent(brick, new HashSet<>());
                        supportedBy.get(brick).add(supportingBrick);
                        supportedBricks.putIfAbsent(supportingBrick, new HashSet<>());
                        supportedBricks.get(supportingBrick).add(brick);
                    });
                    break;
                }
                brick.z().setStart(z);
                brick.z().setEnd(brick.z().end() - 1);
            }
            bricksOnZ.putIfAbsent(brick.z().end(), new HashSet<>());
            bricksOnZ.get(brick.z().end()).add(brick);
        }
        return new Tuple<>(supportedBy, supportedBricks);
    }

    private Map<Brick, Integer> simulateChainReaction() {
        var simulationResult = simulateFallingBricks();
        var supportedBy = simulationResult.first();
        var supportedBricks = simulationResult.second();
        var chainReactionResult = new HashMap<Brick, Integer>();
        for (var brickToDesintegrate : bricks) {
            var fallingBricks = new HashSet<Brick>();
            var queue = new ArrayDeque<Brick>();
            queue.add(brickToDesintegrate);
            while (!queue.isEmpty()) {
                var brickToAnalyze = queue.poll();
                supportedBricks.getOrDefault(brickToAnalyze, Set.of()).stream()
                        .filter(supportedBrick -> supportedBy.get(supportedBrick).stream()
                                .filter(not(brickToDesintegrate::equals))
                                .allMatch(fallingBricks::contains))
                        .forEach(supportedBrick -> {
                            queue.add(supportedBrick);
                            fallingBricks.add(supportedBrick);
                        });
            }
            chainReactionResult.put(brickToDesintegrate, fallingBricks.size());
        }
        return chainReactionResult;
    }
}
