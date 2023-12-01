package de.ossenbeck.mattes.day07;

import de.ossenbeck.mattes.Solveable;

public record Filesystem(Directory rootDir) implements Solveable<Integer>
{
	private static final Integer TOTAL_DISK_SPACE = 70_000_000;

	@Override
	public Integer solvePartOne()
	{
		var visitor = new DirectoriesLessThan100000Visitor();
		rootDir.accept(visitor);
		return visitor.getTotalSize();
	}

	@Override
	public Integer solvePartTwo()
	{
		var visitor = new DirectoryToDeleteVisitor(TOTAL_DISK_SPACE - rootDir.getSize());
		rootDir.accept(visitor);
		return visitor.getSizeOfDirectoryToDelete();
	}
}
