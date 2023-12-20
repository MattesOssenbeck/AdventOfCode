package de.ossenbeck.mattes.day19;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorkflowMapMapper {

    private static final Pattern WORKFLOW_PATTERN = Pattern.compile("^(\\w+)\\{([^}]*)}$");
    private static final Pattern CONDITIONS_PATTERN = Pattern.compile("(\\w)(<|>)(\\d+):(\\w+)");


    public static Map<String, Workflow> map(String workflows) {
        return workflows.lines()
                .map(WORKFLOW_PATTERN::matcher)
                .flatMap(Matcher::results)
                .map(matches -> new Workflow(matches.group(1), mapConditions(matches.group(2))))
                .collect(Collectors.toMap(Workflow::label, workflow -> workflow));
    }

    private static List<Function<Gear, String>> mapConditions(String allConditions) {
        var conditions = allConditions.split(",");
        var instructions = IntStream.range(0, conditions.length - 1)
                .mapToObj(i -> CONDITIONS_PATTERN.matcher(conditions[i]).results()
                        .map(values -> values.group(2).equals(">") ? greaterThan(values) : lessThan(values))
                        .toList())
                .flatMap(List::stream)
                .collect(Collectors.toList());
        instructions.add((gear) -> conditions[conditions.length - 1]);
        return instructions;
    }

    private static Function<Gear, String> greaterThan(MatchResult values) {
        return (gear) -> Category.of(values.group(1)).function().apply(gear) > Integer.parseInt(values.group(3))
                ? values.group(4) : null;
    }

    private static Function<Gear, String> lessThan(MatchResult values) {
        return (gear) -> Category.of(values.group(1)).function().apply(gear) < Integer.parseInt(values.group(3))
                ? values.group(4) : null;
    }
}
