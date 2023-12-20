package de.ossenbeck.mattes.day19;

public record Rule(Category category, char comparisonSign, int number, String target, boolean isFallback) {}