package de.ossenbeck.mattes.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class ConjunctionModule extends Module {
    private Map<String, Signal> recentPulses = new HashMap<>();

    public ConjunctionModule(String label, List<String> destinations) {
        super(label, destinations);
    }

    private ConjunctionModule(String label, List<String> destinations, Map<String, Signal> recentPulses) {
        super(label, destinations);
        this.recentPulses = recentPulses;
    }

    @Override
    public List<Pulse> handlePulse(Pulse pulse) {
        recentPulses.put(pulse.sender(), pulse.signal());
        var signalToSend = recentPulses.containsValue(Signal.LOW) ? Signal.HIGH : Signal.LOW;
        return createPulses(signalToSend);
    }

    @Override
    public void register(Module module) {
        recentPulses.putIfAbsent(module.label(), Signal.LOW);
    }

    @Override
    public Module copy() {
        var recentPulsesCopy = recentPulses.keySet().stream().collect(Collectors.toMap(label -> label, __ -> Signal.LOW));
        return new ConjunctionModule(label(), destinations(), recentPulsesCopy);
    }

    public Set<String> inputModules() {
        return recentPulses.keySet();
    }
}