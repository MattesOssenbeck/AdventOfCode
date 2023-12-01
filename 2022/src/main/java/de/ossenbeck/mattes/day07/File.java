package de.ossenbeck.mattes.day07;

public class File extends FilesystemElement
{
	private final Integer size;

	public File(String name, Integer size)
	{
		super(name);
		this.size = size;
	}

	@Override
	public Integer getSize()
	{
		return size;
	}

	@Override
	public void accept(Visitor visitor)
	{
		visitor.visit(this);
	}
}
