package de.ossenbeck.mattes.day07;

public class DirectoriesLessThan100000Visitor implements Visitor
{
	private Integer totalSize = 0;

	@Override
	public void visit(FilesystemElement element)
	{
		if (element instanceof Directory dir && dir.getSize() <= 100_000)
		{
			totalSize += dir.getSize();
		}
	}

	public Integer getTotalSize()
	{
		return totalSize;
	}
}
