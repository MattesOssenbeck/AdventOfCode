package de.ossenbeck.mattes.day05;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Update(List<Integer> pages) {
    public int middlePage() {
        return pages.get(pages.size() / 2);
    }

    public boolean compliesWith(Set<Rule> rules) {
        return IntStream.range(0, pages.size())
                .mapToObj(before -> IntStream.range(before + 1, pages.size())
                        .mapToObj(after -> new Rule(pages.get(after), pages.get(before)))
                        .toList())
                .flatMap(List::stream)
                .noneMatch(rules::contains);
    }

    public Update sortBy(Set<Rule> rules) {
        return new Update(pages.stream()
                .sorted((a, b) -> rules.contains(new Rule(a, b)) ? -1 : 1)
                .collect(Collectors.toList()));
    }
}