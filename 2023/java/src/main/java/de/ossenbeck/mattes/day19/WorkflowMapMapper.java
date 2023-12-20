package de.ossenbeck.mattes.day19;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorkflowMapMapper {

    private static final Pattern WORKFLOW_PATTERN = Pattern.compile("^(\\w+)\\{([^}]*)}$");
    private static final Pattern RULE_PATTERN = Pattern.compile("(\\w)(<|>)(\\d+):(\\w+)");

    public static Map<String, Workflow> map(String workflows) {
        return workflows.lines()
                .map(WORKFLOW_PATTERN::matcher)
                .flatMap(Matcher::results)
                .map(matches -> new Workflow(matches.group(1), mapRules(matches.group(2))))
                .collect(Collectors.toMap(Workflow::label, workflow -> workflow));
    }

    private static List<Rule> mapRules(String allRules) {
        var rules = allRules.split(",");
        var instructions = IntStream.range(0, rules.length - 1)
                .mapToObj(i -> RULE_PATTERN.matcher(rules[i]).results()
                        .map(values -> new Rule(
                                Category.of(values.group(1)),
                                values.group(2).charAt(0),
                                Integer.parseInt(values.group(3)),
                                values.group(4),
                                false))
                        .toList())
                .flatMap(List::stream)
                .collect(Collectors.toList());
        instructions.add(new Rule(null, '_', Integer.MIN_VALUE, rules[rules.length - 1], true));
        return instructions;
    }
}