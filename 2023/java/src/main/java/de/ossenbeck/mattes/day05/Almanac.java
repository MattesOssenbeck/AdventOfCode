package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.Solveable;

import java.util.List;

public class Almanac implements Solveable<Long, Long> {
    private final List<Long> seeds;
    private final CategoryMap categoryMaps;

    public Almanac(String input) {
        var data = CategoryMapper.map(input);
        this.seeds = data.first();
        this.categoryMaps = data.second();
    }

    @Override
    public Long solvePartOne() {
        return seeds.stream()
                .map(categoryMaps::findLocationNumber)
                .reduce(Long::min)
                .orElseThrow();
    }

    @Override
    public Long solvePartTwo() {
        var minDifference = categoryMaps.getLowestDifference();
        System.out.println(minDifference);
        var min = Long.MAX_VALUE;
        for (var i = 0; i < seeds.size(); i += 2) {
            var start = seeds.get(i);
            var end = start + seeds.get(i + 1);
            for (long newSeed = start; newSeed < end; newSeed += minDifference / 10) {
                var location = categoryMaps.findLocationNumber(newSeed);
                min = Math.min(min, location);
            }
        }
        return min;
    }
    /*
    6863037
    6472133
    6515633
     */

    /*
    487758422 524336848
    2531594804 27107767
    1343486056 124327551
    1117929819 93097070
    3305050822 442320425
    2324984130 87604424
    4216329536 45038934
    1482842780 224610898
    115202033 371332058
    2845474954 192579859
     */
}
