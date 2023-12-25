package de.ossenbeck.mattes.day20;

import java.util.List;

public final class BroadcasterModule extends Module {
    public BroadcasterModule(String label, List<String> destinations) {
        super(label, destinations);
    }

    @Override
    public List<Pulse> handlePulse(Pulse pulse) {
        return createPulses(pulse.signal());
    }

    @Override
    public void register(Module module) {}

    @Override
    public Module copy() {
        return new BroadcasterModule(label(), destinations());
    }
}