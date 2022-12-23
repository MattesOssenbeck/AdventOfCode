package de.ossenbeck.mattes.day07;

import io.vavr.collection.List;

public class Directory extends FilesystemElement
{
	private List<FilesystemElement> subDirs;

	public Directory(String name)
	{
		super(name);
		subDirs = List.empty();
	}

	public Directory getSubDir(String name)
	{
		return subDirs.filter(l -> l instanceof Directory)
				.map(e -> (Directory) e)
				.find(childDir -> childDir.name.equals(name))
				.getOrNull();
	}

	public void add(FilesystemElement element)
	{
		element.setParentDir(this);
		subDirs = subDirs.push(element);
	}

	@Override
	public Integer getSize()
	{
		return subDirs.map(FilesystemElement::getSize)
				.reduce(Integer::sum);
	}

	@Override
	public void accept(Visitor visitor)
	{
		visitor.visit(this);
		subDirs.forEach(dir -> dir.accept(visitor));
	}
}
