package de.ossenbeck.mattes.day09;

import io.vavr.collection.List;

public class Rope
{
	private List<Knot> knots;

	public Rope(Integer size)
	{
		knots = List.fill(size, Knot::new);
	}

	public void followMotion(Motion motion)
	{
		knots.head().setPosition(motion.applyOn(knots.head()));
		knots.tail().forEachWithIndex((tail, i) -> {
			var head = knots.get(i);
			if (!tail.isTouching(head))
			{
				tail.pullTowards(head);
			}
		});
	}

	public List<Knot> getKnots()
	{
		return knots;
	}
}
