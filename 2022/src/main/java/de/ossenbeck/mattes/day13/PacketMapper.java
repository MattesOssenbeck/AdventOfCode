package de.ossenbeck.mattes.day13;

import io.vavr.collection.List;
import io.vavr.collection.Stream;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class PacketMapper
{
	private static final Pattern DIGIT_OR_LIST_PATTERN = Pattern.compile("\\d+|\\[[^]]*]");

	public static List<PacketPair> map(String input)
	{
		return List.of(input.split("\n\n"))
				.map(PacketMapper::mapPacketPairs);
	}

	private static PacketPair mapPacketPairs(String pair)
	{
		var packets = List.of(pair.split("\n"))
				.map(s -> s.substring(1))
				.map(PacketMapper::match);
		return new PacketPair(packets.get(0), packets.get(1));
	}

	public static List<?> match(String packet)
	{
		return List.ofAll(DIGIT_OR_LIST_PATTERN.matcher(packet)
				.results()
				.map(MatchResult::group)
				.map(PacketMapper::parse));
	}

	public static List<?> parse(String element)
	{
		if (element.startsWith("["))
		{
			return List.of(match(element.substring(1)));
		}
		return List.ofAll(Stream.of(element.split(",")).map(Integer::parseInt));
	}
}
