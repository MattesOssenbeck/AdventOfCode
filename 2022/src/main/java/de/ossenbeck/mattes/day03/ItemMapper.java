package de.ossenbeck.mattes.day03;

import io.vavr.collection.CharSeq;
import io.vavr.collection.List;

public class ItemMapper
{
	public static List<List<Item>> map(List<String> contents)
	{
		return contents.map(itemlist -> CharSeq.of(itemlist).map(Item::new).toList()).toList();
	}
}
