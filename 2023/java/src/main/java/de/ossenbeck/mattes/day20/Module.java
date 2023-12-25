package de.ossenbeck.mattes.day20;

import java.util.List;
import java.util.function.Consumer;

public abstract sealed class Module permits FlipFlopModule, ConjunctionModule, BroadcasterModule {
    private final String label;
    private final List<String> destinations;

    public Module(String label, List<String> destinations) {
        this.label = label;
        this.destinations = destinations;
    }

    public static Module of(char prefix, String label, List<String> destinations) {
        return switch (prefix) {
            case '%' -> new FlipFlopModule(label, destinations);
            case '&' -> new ConjunctionModule(label, destinations);
            case 'b' -> new BroadcasterModule("b" + label, destinations);
            default -> throw new IllegalArgumentException();
        };
    }

    protected List<Pulse> createPulses(Signal signal) {
        return destinations().stream()
                .map(destination -> new Pulse(label(), signal, destination))
                .toList();
    }

    public abstract void handlePulse(Pulse pulse, Consumer<Pulse> sendPulse);

    public abstract void register(Module module);

    public abstract Module copy();

    public String label() {
        return label;
    }

    public List<String> destinations() {
        return destinations;
    }
}