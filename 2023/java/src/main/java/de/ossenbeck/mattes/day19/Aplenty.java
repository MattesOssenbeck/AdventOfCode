package de.ossenbeck.mattes.day19;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Range;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Aplenty implements Solveable<Long, Long> {
    private static final String ACCEPTED = "A";
    private static final String REJECTED = "R";
    private static final String START_WORKFLOW = "in";
    private final Map<String, Workflow> workflows;
    private final List<Gear> gears;

    public Aplenty(String input) {
        var data = input.split(System.lineSeparator() + System.lineSeparator());
        this.workflows = WorkflowMapMapper.map(data[0]);
        this.gears = GearMapper.map(data[1]);
    }

    @Override
    public Long solvePartOne() {
        return gears.stream()
                .flatMap(gear -> isAcceptedByWorkflow(gear, START_WORKFLOW))
                .map(gear -> gear.looking().end() + gear.musical().end() + gear.aerodynamic().end() + gear.shiny().end())
                .reduce(0L, Long::sum);
    }

    @Override
    public Long solvePartTwo() {
        var soManyGears = new Gear(
                new Range(1, 4000),
                new Range(1, 4000),
                new Range(1, 4000),
                new Range(1, 4000)
        );
        return isAcceptedByWorkflow(soManyGears, START_WORKFLOW)
                .map(gear -> gear.looking().length() * gear.musical().length() * gear.aerodynamic().length() * gear.shiny().length())
                .reduce(0L, Long::sum);
    }

    private Stream<Gear> isAcceptedByWorkflow(Gear gear, String workflow) {
        if (workflow.equals(ACCEPTED)) {
            return Stream.of(gear);
        }
        if (workflow.equals(REJECTED)) {
            return Stream.empty();
        }
        var results = workflows.get(workflow).processGear(gear);
        return results.stream().flatMap(result -> isAcceptedByWorkflow(result.first(), result.second()));
    }
}

