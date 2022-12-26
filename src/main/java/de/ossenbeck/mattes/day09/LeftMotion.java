package de.ossenbeck.mattes.day09;

public class LeftMotion implements Motion
{
	@Override
	public Position applyOn(Knot knot)
	{
		return new Position(knot.getX() - 1, knot.getY());
	}
}
