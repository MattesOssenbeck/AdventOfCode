package de.ossenbeck.mattes.day05;

import java.util.List;

public record AlmanacMappingResult(List<Long> seeds, CategoryMap seedToLocationMap) {}