package de.ossenbeck.mattes.day08;

public class Tree
{
	public final Integer height;
	private Integer scenicScore = 1;

	public Tree(Integer height)
	{
		this.height = height;
	}

	public void increaseScenicScore(Integer viewDistance)
	{
		scenicScore *= viewDistance;
	}

	public Integer getScenicScore()
	{
		return scenicScore;
	}
}
