package de.ossenbeck.mattes.day20;

import java.util.List;

public final class FlipFlopModule extends Module {
    private boolean on;

    public FlipFlopModule(String label, List<String> destinations) {
        super(label, destinations);
    }

    @Override
    public List<Pulse> handlePulse(Pulse pulse) {
        if (Signal.LOW.equals(pulse.signal())) {
            on = !on;
            var signalToSend = on ? Signal.HIGH : Signal.LOW;
            return createPulses(signalToSend);
        }
        return List.of();
    }

    @Override
    public void register(Module module) {}

    @Override
    public Module copy() {
        return new FlipFlopModule(label(), destinations());
    }
}