package de.ossenbeck.mattes.day09;

import static io.vavr.API.*;

public interface Motion
{
	static Motion of(String letter)
	{
		return Match(letter).of(
				Case($("R"), new RightMotion()),
				Case($("L"), new LeftMotion()),
				Case($("U"), new UpMotion()),
				Case($("D"), new DownMotion()),
				Case($(), o -> {throw new IllegalArgumentException();})
		);
	}

	Position applyOn(Knot knot);
}
