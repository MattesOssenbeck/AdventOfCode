package de.ossenbeck.mattes.day06;


public record Instruction(int xs, int ys, int xe, int ye, Action action) {
    public boolean contains(int x, int y) {
        return x >= xs && x <= xe && y >= ys && y <= ye;
    }
}