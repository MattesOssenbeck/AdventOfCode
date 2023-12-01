package de.ossenbeck.mattes.day08;

import io.vavr.collection.List;

public class TreeMapper
{
	public static List<List<Tree>> map(List<String> grid)
	{
		return grid.map(TreeMapper::mapTreeRow);
	}

	private static List<Tree> mapTreeRow(String row)
	{
		return List.range(0, row.length())
				.map(i -> Integer.parseInt("" + row.charAt(i)))
				.map(Tree::new);
	}
}
