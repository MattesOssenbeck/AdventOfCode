package de.ossenbeck.mattes.day07;

public abstract class FilesystemElement
{
	public final String name;
	private Directory parentDir;

	public FilesystemElement(String name)
	{
		this.name = name;
	}

	public abstract Integer getSize();

	public abstract void accept(Visitor visitor);

	public Directory getParentDir()
	{
		return parentDir;
	}

	public void setParentDir(Directory parentDir)
	{
		this.parentDir = parentDir;
	}
}
