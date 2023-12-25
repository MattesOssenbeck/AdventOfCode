package de.ossenbeck.mattes.day20;

import de.ossenbeck.mattes.Solveable;
import de.ossenbeck.mattes.util.Util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
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
        var modules = copyModules();
        var pulses = new HashMap<>(Map.of(Signal.LOW, 0L, Signal.HIGH, 0L));

        pushButton(modules,
                (times) -> times < 1_000,
                (pulse, timess) -> pulses.compute(pulse.signal(), (signal, amount) -> amount + 1)
        );
        return pulses.values().stream().reduce(1L, Math::multiplyExact);
    }


    @Override
    public Long solvePartTwo() {
        var modules = copyModules();
        var inputModulesCycle = modules.values().stream()
                .filter(module -> module.destinations().contains("rx"))
                .findFirst()
                .map(module -> (ConjunctionModule) module)
                .map(ConjunctionModule::inputModules)
                .map(inputModules -> inputModules.stream().collect(Collectors.toMap(label -> label, __ -> 0L)))
                .orElseThrow();

        pushButton(modules,
                (times) -> inputModulesCycle.containsValue(0L),
                (pulse, times) -> {
                    if (inputModulesCycle.containsKey(pulse.sender()) && pulse.signal().equals(Signal.HIGH)) {
                        inputModulesCycle.put(pulse.sender(), times);
                    }
                }
        );
        return inputModulesCycle.values().stream().reduce(1L, Util::lcm);
    }

    private void pushButton(Map<String, Module> modules, Predicate<Long> shouldContinue, BiConsumer<Pulse, Long> consumer) {
        var queue = new ArrayDeque<Pulse>();
        var broadcastPulse = new Pulse("button", Signal.LOW, "broadcaster");
        var numberOfButtonPushes = 0L;
        while (shouldContinue.test(numberOfButtonPushes)) {
            numberOfButtonPushes++;
            queue.add(broadcastPulse);
            while (!queue.isEmpty()) {
                var pulse = queue.pop();
                var destinationModule = modules.get(pulse.destination());
                if (destinationModule != null) {
                    destinationModule.handlePulse(pulse, queue::add);
                }
                consumer.accept(pulse, numberOfButtonPushes);
            }
        }
    }

    private Map<String, Module> copyModules() {
        return mappedModules.values().stream()
                .map(Module::copy)
                .collect(Collectors.toMap(Module::label, module -> module));
    }
}