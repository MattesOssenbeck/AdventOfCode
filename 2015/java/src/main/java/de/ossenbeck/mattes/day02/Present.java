package de.ossenbeck.mattes.day02;

public record Present(long length, long width, long height) {
    public long requiredWrappingPaper() {
        var a = length * width;
        var b = width * height;
        var c = height * length;
        var slack = Math.min(a, Math.min(b, c));
        return 2 * (a + b + c) + slack;
    }

    public long requiredRibbon() {
        return smallestPerimeter() + volume();
    }

    private long smallestPerimeter() {
        var smallestSide = Math.min(length, Math.min(width, height));
        var secondSmallestSide = Math.min(Math.max(length, width), Math.min(Math.max(width, height), Math.max(length, height)));
        return 2 * (smallestSide + secondSmallestSide);
    }

    private long volume() {
        return length * width * height;
    }
}