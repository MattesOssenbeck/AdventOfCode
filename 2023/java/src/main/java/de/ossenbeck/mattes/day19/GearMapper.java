package de.ossenbeck.mattes.day19;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearMapper {
    private static final Pattern GEAR_PATTERN = Pattern.compile("(\\d+)");

    public static List<Gear> map(String gears) {
        return gears.lines()
                .map(GEAR_PATTERN::matcher)
                .map(Matcher::results)
                .map(ratings -> ratings.map(MatchResult::group).map(Integer::parseInt).toList())
                .map(g -> new Gear(g.get(0), g.get(1), g.get(2), g.get(3)))
                .toList();
    }
}
