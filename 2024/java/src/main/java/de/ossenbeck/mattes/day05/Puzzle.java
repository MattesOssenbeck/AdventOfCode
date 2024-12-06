package de.ossenbeck.mattes.day05;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static de.ossenbeck.mattes.common.Util.DOUBLE_LINE_SEPARATOR;
import static de.ossenbeck.mattes.common.Util.LINE_SEPARATOR;
import static java.util.function.Predicate.not;

public class Puzzle implements Solvable<Integer, Integer> {
    private static final Pattern RULE_DELIMITER = java.util.regex.Pattern.compile("\\|");
    private static final Pattern UPDATE_DELIMITER = Pattern.compile(",");
    private final Set<Rule> rules;
    private final List<Update> updates;

    public Puzzle(InputReader inputReader) {
        var input = DOUBLE_LINE_SEPARATOR.split(inputReader.asString());
        this.rules = LINE_SEPARATOR.splitAsStream(input[0])
                .map(line -> RULE_DELIMITER.splitAsStream(line).mapToInt(Integer::parseInt).toArray())
                .map(pages -> new Rule(pages[0], pages[1]))
                .collect(Collectors.toSet());
        this.updates = LINE_SEPARATOR.splitAsStream(input[1])
                .map(line -> UPDATE_DELIMITER.splitAsStream(line).map(Integer::parseInt).toList())
                .map(Update::new)
                .toList();
    }

    @Override
    public Integer solvePartOne() {
        return updates.stream()
                .filter(update -> update.compliesWith(rules))
                .mapToInt(Update::middlePage)
                .sum();
    }

    @Override
    public Integer solvePartTwo() {
        return updates.stream()
                .filter(not(update -> update.compliesWith(rules)))
                .map(update -> update.sortBy(rules))
                .mapToInt(Update::middlePage)
                .sum();
    }
}
