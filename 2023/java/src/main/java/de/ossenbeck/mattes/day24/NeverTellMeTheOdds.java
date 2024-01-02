package de.ossenbeck.mattes.day24;

import de.ossenbeck.mattes.Solveable;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class NeverTellMeTheOdds implements Solveable<Integer, Long> {
    private static final Pattern HAILSTONE_PATTERN = Pattern.compile("-?[1-9]\\d*|0");
    private final List<Hailstone> hailstones;

    public NeverTellMeTheOdds(List<String> input) {
        this.hailstones = input.stream()
                .map(HAILSTONE_PATTERN::matcher)
                .map(Matcher::results)
                .map(results -> results.map(MatchResult::group).map(Long::parseLong).toList())
                .map(values -> new Hailstone(values.get(0), values.get(1), values.get(2),
                        values.get(3), values.get(4), values.get(5)))
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        var lower = 200_000_000_000_000L;
        var upper = 400_000_000_000_000L;
        return IntStream.range(0, hailstones.size())
                .mapToObj(i -> IntStream.range(i + 1, hailstones.size())
                        .mapToObj(hailstones::get)
                        .map(hailstones.get(i)::calculateIntersection)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .filter(intersection -> intersection.x() >= lower && intersection.x() <= upper
                                && intersection.y() >= lower && intersection.y() <= upper)
                        .toList())
                .map(List::size)
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solvePartTwo() {
        var rock = calculateRockXY();
        return rock.px() + rock.py() + calculateRockZ(rock);
    }

    /*
     * My first brute force solution took way too long and since I wanted to try to find the solution without solving
     * linear equations, I looked for other approaches on Reddit. There I read several times that you can determine the
     * position of the stone by treating the problem relatively. I'm not a big math or physics guy, so it took me a
     * few hours to figure out what I needed to do.
     * If the x- and y-velocities of the rock are subtracted from the velocities of the hailstones, they all intersect
     * at the initial position of the rock.
     * I noticed this when I simulated the paths of the hailstones and subtracted the velocities of the rock from the
     * example. The website I used: https://phydemo.app/ray-optics/simulator/
     */
    private Hailstone calculateRockXY() {
        var deltaVxQueue = new ArrayDeque<Long>();
        for (var delta = 1L; ; delta++) {
            deltaVxQueue.add(delta);
            deltaVxQueue.add(-delta);
            while (!deltaVxQueue.isEmpty()) {
                var deltaVx = deltaVxQueue.poll();
                for (var deltaVy = -delta; deltaVy <= delta; deltaVy++) {
                    var intersections = calculateIntersectionsWithDeltaVelocities(deltaVx, deltaVy);
                    if (!intersections.isEmpty()) {
                        var numberOfIntersectionsPerCoordinate = new HashMap<DoubleCoordinate, Integer>();
                        intersections.forEach(intersection -> {
                            numberOfIntersectionsPerCoordinate.computeIfPresent(intersection, (__, i) -> i + 1);
                            numberOfIntersectionsPerCoordinate.putIfAbsent(intersection, 1);
                        });
                        var positionRock = numberOfIntersectionsPerCoordinate.entrySet().stream()
                                .reduce((e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2)
                                .map(Map.Entry::getKey)
                                .orElseThrow();
                        return new Hailstone((long) positionRock.x(), (long) positionRock.y(), 0, deltaVx, deltaVy, 0);
                    }
                }
            }
        }
    }

    /*
     * Using two xy-intersections between rock and hailstone, the z-velocity of the rock can be calculated based on the
     * time passed and the distance traveled between these two intersections.
     */
    private long calculateRockZ(Hailstone rock) {
        var h1 = hailstones.getFirst();
        var h2 = hailstones.getLast();
        var t1 = rock.calculateTimeOfIntersection(h1);
        var t2 = rock.calculateTimeOfIntersection(h2);
        var zDistance = Math.max(h1.z(t1), h2.z(t2)) - Math.min(h1.z(t1), h2.z(t2));
        var timePassed = Math.max(t1, t2) - Math.min(t1, t2);
        return h1.z(t1) - t1 * (zDistance / timePassed);
    }

    private List<DoubleCoordinate> calculateIntersectionsWithDeltaVelocities(long deltaVx, long deltaVy) {
        var intersections = new ArrayList<DoubleCoordinate>();
        for (var i = 0; i < hailstones.size(); i++) {
            for (var j = i + 1; j < hailstones.size(); j++) {
                var h1 = hailstones.get(i).substractDeltaVelocity(deltaVx, deltaVy);
                var h2 = hailstones.get(j).substractDeltaVelocity(deltaVx, deltaVy);
                var intersection = h1.calculateIntersection(h2);
                if (intersection.isEmpty()) {
                    return List.of();
                }
                intersections.add(intersection.get());
            }
        }
        return intersections;
    }
}
