package de.ossenbeck.mattes.day04;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.Solvable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Puzzle implements Solvable<Integer, Integer> {
    private final String secret;
    private final MessageDigest md5;

    public Puzzle(InputReader inputReader) {
        this.secret = inputReader.asString();
        try {
            this.md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer solvePartOne() {
        return findHash(4);
    }

    @Override
    public Integer solvePartTwo() {
        return findHash(0);
    }

    private int findHash(int shift) {
        for (var i = 0; ; i++) {
            var hash = md5.digest((secret + i).getBytes());
            if (hash[0] == 0 && hash[1] == 0 && hash[2] >> shift == 0) {
                return i;
            }
        }
    }
}
