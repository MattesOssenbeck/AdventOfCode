package de.ossenbeck.mattes.day12;

import java.util.List;

public record State(String conditionRecord, List<Integer> sizeOfContiguousGroups) {}