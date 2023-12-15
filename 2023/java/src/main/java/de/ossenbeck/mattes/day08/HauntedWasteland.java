package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.Util;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HauntedWasteland implements Solveable<Integer, Long> {
    private final String instructions;
    private final Map<String, Node> nodes;

    public HauntedWasteland(List<String> input) {
        this.instructions = input.getFirst();
        this.nodes = input.stream().skip(2)
                .map(NodeMapper::map)
                .collect(Collectors.toMap(Node::label, node -> node));
    }

    @Override
    public Integer solvePartOne() {
        return calculateSteps(nodes.get("AAA"), node -> node.label().equals("ZZZ"));
    }

    @Override
    public Long solvePartTwo() {
        return nodes.keySet().stream()
                .filter(label -> label.endsWith("A"))
                .map(label -> calculateSteps(nodes.get(label), node -> node.label().endsWith("Z")))
                .mapToLong(i -> i)
                .reduce(1, Util::lcm);
    }

    private int calculateSteps(Node start, Predicate<Node> destinationCondition) {
        var steps = 0;
        var currentNode = start;
        while (!destinationCondition.test(currentNode)) {
            var newNodeLabel = instructions.charAt(steps++ % instructions.length()) == 'L'
                    ? currentNode.left() : currentNode.right();
            currentNode = nodes.get(newNodeLabel);
        }
        return steps;
    }
}
