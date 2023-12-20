package de.ossenbeck.mattes.day19;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public record Workflow(String label, List<Function<Gear, String>> conditions) {
    public String processGear(Gear gear) {
        return conditions.stream()
                .map(condition -> condition.apply(gear))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow();
    }
}