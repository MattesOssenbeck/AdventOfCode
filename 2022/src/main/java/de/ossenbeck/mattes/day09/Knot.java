package de.ossenbeck.mattes.day09;

import io.vavr.collection.List;

public class Knot
{
	private Position position;
	private List<Position> visitedPositions;

	public Knot()
	{
		position = new Position(0, 0);
		visitedPositions = List.of(new Position(0, 0));
	}

	public void setPosition(Position position)
	{
		visitedPositions = visitedPositions.push(position);
		this.position = position;
	}

	public Integer getX()
	{
		return position.x();
	}

	public Integer getY()
	{
		return position.y();
	}

	public Boolean isTouching(Knot head)
	{
		return Math.abs(head.getX() - position.x()) <= 1
				&& Math.abs(head.getY() - position.y()) <= 1;
	}

	public void pullTowards(Knot head)
	{
		var distanceToMoveX = (head.getX() - position.x()) / 2 + (head.getX() - position.x()) % 2;
		var distanceToMoveY = (head.getY() - position.y()) / 2 + (head.getY() - position.y()) % 2;
		setPosition(new Position(position.x() + distanceToMoveX, position.y() + distanceToMoveY));
	}

	public List<Position> getVisitedPositions()
	{
		return visitedPositions;
	}
}
