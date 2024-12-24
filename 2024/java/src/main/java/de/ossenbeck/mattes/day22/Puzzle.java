package de.ossenbeck.mattes.day22;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;
import de.ossenbeck.mattes.common.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Puzzle implements Solvable<Long, Long> {
    private final List<Long> initialSecrets;

    public Puzzle(InputReader inputReader) {
        this.initialSecrets = inputReader.asStream()
                .map(Util::parseLong)
                .toList();
    }

    @Override
    public Long solvePartOne() {
        return initialSecrets.stream()
                .map(this::calculateSecrets)
                .mapToLong(List::getLast)
                .sum();
    }

    @Override
    public Long solvePartTwo() {
        return initialSecrets.parallelStream()
                .mapMulti(this::calculateSequences)
                .collect(Collectors.toMap(sequence -> sequence, Sequence::price, Long::sum))
                .values().stream()
                .reduce(0L, Long::max);
    }

    private void calculateSequences(long input, Consumer<Sequence> consumer) {
        var calculatedSequences = new HashSet<Sequence>();
        var prices = new ArrayList<Long>();
        prices.add(price(input));

        var secret = input;
        for (var i = 0; i < 2000; i++) {
            secret = calculateSecret(secret);
            prices.add(price(secret));
            if (prices.size() == 5) {
                var sequence = Sequence.of(prices);
                prices.removeFirst();
                if (calculatedSequences.add(sequence)) {
                    consumer.accept(sequence);
                }
            }
        }
    }

    private List<Long> calculateSecrets(long value) {
        return Stream.iterate(value, this::calculateSecret)
                .limit(2001)
                .collect(Collectors.toList());
    }

    private long calculateSecret(long input) {
        var secret = prune(mix(input, input * 64));
        secret = prune(mix(secret, secret / 32));
        return prune(mix(secret, secret * 2048));
    }

    private long mix(long secret, long number) {
        return secret ^ number;
    }

    private long prune(long secret) {
        return secret % 16777216;
    }

    private long price(long secret) {
        return secret % 10;
    }
}
