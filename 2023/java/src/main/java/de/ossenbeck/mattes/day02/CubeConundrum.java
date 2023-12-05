package de.ossenbeck.mattes.day02;

import de.ossenbeck.mattes.Solveable;

import java.util.List;
import java.util.stream.Collectors;

public class CubeConundrum implements Solveable<Integer, Integer> {
    private final List<Game> games;

    protected CubeConundrum(List<String> input) {
        this.games = input.stream()
                .map(GameMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Integer solvePartOne() {
        return games.stream()
                .filter(Game::isPossible)
                .map(Game::id)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    @Override
    public Integer solvePartTwo() {
        return games.stream()
                .map(g -> g.highestAmountOf(Color.RED) * g.highestAmountOf(Color.GREEN) * g.highestAmountOf(Color.BLUE))
                .reduce(Integer::sum)
                .orElseThrow();
    }
}