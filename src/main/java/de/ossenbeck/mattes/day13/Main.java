package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.InputReader;
import de.ossenbeck.mattes.PuzzleSolver;
import io.vavr.control.Try;

public class Main
{
	public static void main(String[] args)
	{
		Try.of(() -> InputReader.readAsString("day13.txt"))
				.map(PacketMapper::map)
				.map(Decoder::new)
				.andThen(PuzzleSolver::solve)
				.onFailure(System.err::println);
	}
}
