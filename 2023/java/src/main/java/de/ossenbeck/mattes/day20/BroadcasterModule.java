package de.ossenbeck.mattes.day20;

import java.util.List;
import java.util.function.Consumer;

public final class BroadcasterModule extends Module {
    public BroadcasterModule(String label, List<String> destinations) {
        super(label, destinations);
    }

    @Override
    public void handlePulse(Pulse pulse, Consumer<Pulse> sendPulse) {
        createPulses(pulse.signal()).forEach(sendPulse);
    }

    @Override
    public void register(Module module) {}

    @Override
    public Module copy() {
        return new BroadcasterModule(label(), destinations());
    }
}