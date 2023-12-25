package de.ossenbeck.mattes.day20;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class PulsePropagation implements Solveable<Long, Long> {
    private final Map<String, Module> mappedModules;

    public PulsePropagation(List<String> input) {
        this.mappedModules = input.stream()
                .map(line -> line.split(" -> "))
                .map(data -> Module.of(data[0].charAt(0), data[0].substring(1), Arrays.stream(data[1].split(", ")).toList()))
                .collect(Collectors.toMap(Module::label, module -> module));
        mappedModules.forEach((__, sender) -> sender.destinations().stream()
                .filter(mappedModules::containsKey)
                .forEach(destination -> mappedModules.get(destination).register(sender)));
    }

    @Override
    public Long solvePartOne() {
        var modules = mappedModules.values().stream()
                .map(Module::copy)
                .collect(Collectors.toMap(Module::label, module -> module));

        var pulses = new HashMap<>(Map.of(Signal.LOW, 0L, Signal.HIGH, 0L));
        var queue = new ArrayDeque<Pulse>();
        for (var times = 0; times < 1_000; times++) {
            queue.add(new Pulse("button", Signal.LOW, "broadcaster"));
            while (!queue.isEmpty()) {
                var pulse = queue.pop();
                var destinationModule = modules.get(pulse.destination());
                if (destinationModule != null) {
                    queue.addAll(destinationModule.handlePulse(pulse));
                }
                pulses.compute(pulse.signal(), (signal, amount) -> amount + 1);
            }
        }
        return pulses.values().stream().reduce(1L, Math::multiplyExact);
    }

    @Override
    public Long solvePartTwo() {
        // This solution assumes that only one conjunction module is attached to rx and
        // that each connected input module for this conjunction module enters a cycle after sending a HIGH pulse once
        var modules = mappedModules.values().stream()
                .map(Module::copy)
                .collect(Collectors.toMap(Module::label, module -> module));

        var inputModulesCycle = modules.values().stream()
                .filter(module -> module.destinations().contains("rx"))
                .findFirst()
                .map(module -> (ConjunctionModule) module)
                .map(ConjunctionModule::inputModules)
                .map(inputModules -> inputModules.stream().collect(Collectors.toMap(label -> label, __ -> 0L)))
                .orElseThrow();
        var queue = new ArrayDeque<Pulse>();
        var times = 0L;
        while (true) {
            queue.add(new Pulse("button", Signal.LOW, "broadcaster"));
            times++;
            while (!queue.isEmpty()) {
                var pulse = queue.pop();
                var destinationModule = modules.get(pulse.destination());
                if (destinationModule != null) {
                    queue.addAll(destinationModule.handlePulse(pulse));
                }
                if (inputModulesCycle.containsKey(pulse.sender()) && pulse.signal().equals(Signal.HIGH)) {
                    inputModulesCycle.put(pulse.sender(), times);
                }
            }
            if (!inputModulesCycle.containsValue(0L)) {
                return inputModulesCycle.values().stream().reduce(1L, Util::lcm);
            }
        }
    }
}
