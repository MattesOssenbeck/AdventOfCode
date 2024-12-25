package de.ossenbeck.mattes.day23;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static de.ossenbeck.mattes.common.Util.intersect;

public class Puzzle implements Solvable<Long, String> {
    private final Map<String, Set<String>> network = new HashMap<>();

    public Puzzle(InputReader inputReader) {
        inputReader.asStream()
                .map(line -> line.split("-"))
                .forEach(connection -> {
                    network.computeIfAbsent(connection[0], _ -> new HashSet<>()).add(connection[1]);
                    network.computeIfAbsent(connection[1], _ -> new HashSet<>()).add(connection[0]);
                });
    }

    @Override
    public Long solvePartOne() {
        return network.keySet().stream()
                .flatMap(a -> network.get(a).stream()
                        .filter(b -> b.compareTo(a) > 0)
                        .flatMap(b -> network.get(b).stream()
                                .filter(c -> c.compareTo(b) > 0)
                                .filter(c -> network.get(a).contains(c))
                                .filter(c -> a.startsWith("t") || b.startsWith("t") || c.startsWith("t"))
                        )
                ).count();
    }

    @Override
    public String solvePartTwo() {
        var largestNetwork = new HashSet<String>();
        bronKerbosch(new HashSet<>(), new HashSet<>(network.keySet()), new HashSet<>(), largestNetwork);
        return largestNetwork.stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    private void bronKerbosch(Set<String> r, Set<String> p, Set<String> x, Set<String> maxClique) {
        if (p.isEmpty() && x.isEmpty()) {
            if (r.size() > maxClique.size()) {
                maxClique.clear();
                maxClique.addAll(r);
            }
            return;
        }
        var pivot = p.isEmpty() ? x.iterator().next() : p.iterator().next();
        var p_ = new HashSet<>(p);
        p_.removeAll(network.get(pivot));
        for (var v : p_) {
            var connections = network.get(v);
            r.add(v);
            bronKerbosch(r, intersect(p, connections), intersect(x, connections), maxClique);
            r.remove(v);
            p.remove(v);
            x.add(v);
        }
    }
}
