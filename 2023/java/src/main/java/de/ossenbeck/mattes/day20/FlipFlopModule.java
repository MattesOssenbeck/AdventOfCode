package de.ossenbeck.mattes.day20;

import java.util.List;
import java.util.function.Consumer;

public final class FlipFlopModule extends Module {
    private boolean on;

    public FlipFlopModule(String label, List<String> destinations) {
        super(label, destinations);
    }

    @Override
    public void handlePulse(Pulse pulse, Consumer<Pulse> sendPulse) {
        if (Signal.LOW.equals(pulse.signal())) {
            on = !on;
            var signalToSend = on ? Signal.HIGH : Signal.LOW;
            createPulses(signalToSend).forEach(sendPulse);
        }
    }

    @Override
    public void register(Module module) {}

    @Override
    public Module copy() {
        return new FlipFlopModule(label(), destinations());
    }
}