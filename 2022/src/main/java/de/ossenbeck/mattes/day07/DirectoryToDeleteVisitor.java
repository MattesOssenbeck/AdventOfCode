package de.ossenbeck.mattes.day07;

public class DirectoryToDeleteVisitor implements Visitor
{
	private static final Integer UNUSED_DISK_SPACE_NEEDED = 30_000_000;

	private final Integer unusedDiskSpace;
	private Integer sizeOfDirectoryToDelete = Integer.MAX_VALUE;

	public DirectoryToDeleteVisitor(Integer unusedDiskSpace)
	{
		this.unusedDiskSpace = unusedDiskSpace;
	}

	@Override
	public void visit(FilesystemElement element)
	{
		if (element instanceof Directory dir
				&& dir.getSize() + unusedDiskSpace > UNUSED_DISK_SPACE_NEEDED
				&& dir.getSize() < sizeOfDirectoryToDelete)
		{
			sizeOfDirectoryToDelete = dir.getSize();
		}
	}

	public Integer getSizeOfDirectoryToDelete()
	{
		return sizeOfDirectoryToDelete;
	}
}
