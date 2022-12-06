package de.ossenbeck.mattes;

import io.vavr.collection.List;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader
{
	public static List<String> readAsList(String filename) throws Exception
	{
		return List.ofAll(Files.readAllLines(
				Paths.get(InputReader.class.getResource("/" + filename).toURI()),
				StandardCharsets.UTF_8));
	}

	public static String readAsString(String filename) throws Exception
	{
		return Files.readString(
				Paths.get(InputReader.class.getResource("/" + filename).toURI()),
				StandardCharsets.UTF_8);
	}
}
