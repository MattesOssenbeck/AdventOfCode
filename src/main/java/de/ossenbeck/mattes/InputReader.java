package de.ossenbeck.mattes;

import io.vavr.collection.List;
import io.vavr.control.Try;

import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader
{
	public static Try<List<String>> readFromRessources(String filename)
	{
		return Try.of(() -> Files.readAllLines(Paths.get(InputReader.class.getResource("/" + filename).toURI())))
				.map(List::ofAll);
	}
}
