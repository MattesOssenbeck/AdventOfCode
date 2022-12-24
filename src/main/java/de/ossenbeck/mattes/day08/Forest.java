package de.ossenbeck.mattes.day08;

import de.ossenbeck.mattes.Solveable;
import io.vavr.Value;
import io.vavr.collection.List;

public record Forest(List<List<Tree>> trees) implements Solveable<Integer>
{
	@Override
	public Integer solvePartOne()
	{
		return trees.appendAll(List.transpose(trees))
				.flatMap(this::findVisibleTrees)
				.distinct()
				.size();
	}

	@Override
	public Integer solvePartTwo()
	{
		trees.forEach(this::determineScenicScores);
		List.transpose(trees).forEach(this::determineScenicScores);
		return trees.toStream()
				.flatMap(Value::toStream)
				.map(Tree::getScenicScore)
				.reduce(Integer::max);
	}

	private void determineScenicScores(List<Tree> rowOfTrees)
	{
		rowOfTrees.forEachWithIndex((tree, value) -> {
			var left = rowOfTrees.slice(0, value)
					.findLast(t -> t.height >= tree.height)
					.map(rowOfTrees::indexOf)
					.getOrElse(0);
			tree.increaseScenicScore(value - left);
			var right = rowOfTrees.slice(value + 1, rowOfTrees.size())
					.find(t -> t.height >= tree.height)
					.map(rowOfTrees::indexOf)
					.getOrElse(rowOfTrees.size() - 1);
			tree.increaseScenicScore(right - value);
		});
	}

	private List<Tree> findVisibleTrees(List<Tree> rowOfTrees)
	{
		return List.range(0, rowOfTrees.size())
				.filter(i -> visible(rowOfTrees.slice(0, i), rowOfTrees.get(i))
						|| visible(rowOfTrees.slice(i + 1, rowOfTrees.size()), rowOfTrees.get(i)))
				.map(rowOfTrees::get);
	}

	private Boolean visible(List<Tree> trees, Tree tree)
	{
		return trees.find(t -> t.height >= tree.height).isEmpty();
	}
}
