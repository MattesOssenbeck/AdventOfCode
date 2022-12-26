package de.ossenbeck.mattes.day09;

public class DownMotion implements Motion
{
	@Override
	public Position applyOn(Knot knot)
	{
		return new Position(knot.getX(), knot.getY() - 1);
	}
}
