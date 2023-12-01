package de.ossenbeck.mattes.day07;

import io.vavr.collection.List;

public class RootDirectoryMapper
{
	public static Directory map(List<String> commands)
	{
		var rootDir = new Directory("/");
		var currentDir = rootDir;
		for (var command : commands)
		{
			if (command.equals("$ cd /"))
			{
				currentDir = rootDir;
				continue;
			}
			if (command.equals("$ cd .."))
			{
				currentDir = currentDir.getParentDir();
				continue;
			}
			if (command.startsWith("$ cd"))
			{
				currentDir = currentDir.getSubDir(command.substring(5));
				continue;
			}
			if (command.startsWith("dir"))
			{
				var dir = new Directory(command.substring(4));
				currentDir.add(dir);
				continue;
			}
			if (!command.startsWith("$ ls"))
			{
				var s = command.split(" ");
				var file = new File(s[1], Integer.parseInt(s[0]));
				currentDir.add(file);
			}
		}
		return rootDir;
	}
}
