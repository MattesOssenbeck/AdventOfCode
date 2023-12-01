package de.ossenbeck.mattes.day01;

import io.vavr.Value;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

public class ElfMapper
{
	static List<Elf> map(String calories)
	{
		return Stream.of(calories.split("\n\n"))
				.map(s -> Stream.of(s.split("\n"))
						.map(Integer::parseInt))
				.map(Value::toList)
				.map(Elf::new).toList();
	}
}
