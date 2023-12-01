package de.ossenbeck.mattes.day16;

import java.util.Collection;

public record State(Valve valve, int minute, Collection<Valve> toVisit, boolean isElephant)
{
}
