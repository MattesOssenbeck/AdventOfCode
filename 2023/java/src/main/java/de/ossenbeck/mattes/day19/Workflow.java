package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.util.Tuple;

import java.util.ArrayList;
import java.util.List;

public record Workflow(String label, List<Rule> rules) {
    public List<Tuple<Gear, String>> processGear(Gear gear) {
        var processed = new ArrayList<Tuple<Gear, String>>();
        var current = gear;
        for (var rule : rules) {
            if (rule.isFallback()) {
                processed.add(new Tuple<>(current, rule.target()));
                break;
            }
            var range = current.rangeFor(rule.category());
            var result = range.match(rule.comparisonSign(), rule.number());
            if (result.first() != null) {
                processed.add(new Tuple<>(current.newWith(rule.category(), result.first()), rule.target()));
            }
            if (result.second() == null) {
                break;
            }
            current = current.newWith(rule.category(), result.second());
        }
        return processed;
    }
}