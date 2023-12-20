package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Aplenty implements Solveable<Integer, Long> {
    private static final String ACCEPTED = "A";
    private static final String REJECTED = "R";
    private final Map<String, Workflow> workflows;
    private final List<Gear> gears;

    public Aplenty(String input) {
        var data = input.split(System.lineSeparator() + System.lineSeparator());
        this.workflows = WorkflowMapMapper.map(data[0]);
        this.gears = GearMapper.map(data[1]);
    }

    @Override
    public Integer solvePartOne() {
        return gears.stream()
                .filter(this::isAcceptedByWorkflow)
                .map(gear -> gear.looking() + gear.musical() + gear.aerodynamic() + gear.shiny())
                .reduce(0, Integer::sum);
    }

    private boolean isAcceptedByWorkflow(Gear gear) {
        return Stream.iterate("in", currentWorkflow -> workflows.get(currentWorkflow).processGear(gear))
                .filter(currentWorkflow -> currentWorkflow.equals(ACCEPTED) || currentWorkflow.equals(REJECTED))
                .findFirst()
                .map(currentWorkflow -> currentWorkflow.equals(ACCEPTED))
                .orElse(false);
    }

    @Override
    public Long solvePartTwo() {
        return 0L;
    }
}

