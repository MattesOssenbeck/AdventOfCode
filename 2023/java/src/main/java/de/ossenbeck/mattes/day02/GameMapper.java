package de.ossenbeck.mattes.day02;

import java.util.Arrays;

public class GameMapper {
    public static Game map(String game) {
        var data = game.split(": ");
        var gameId = Integer.parseInt(data[0].split(" ")[1]);
        var grabs = Arrays.stream(data[1].split("; "))
                .map(GameMapper::mapGrab)
                .toList();
        return new Game(gameId, grabs);
    }

    private static Grab mapGrab(String grab) {
        return new Grab(Arrays.stream(grab.split(", "))
                .map(GameMapper::mapCube)
                .toList());
    }

    private static Cube mapCube(String cube) {
        var data = cube.split(" ");
        return new Cube(Integer.parseInt(data[0]), Color.of(data[1]));
    }
}
