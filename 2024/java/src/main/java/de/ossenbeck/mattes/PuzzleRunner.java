package de.ossenbeck.mattes;

import java.io.File;
import java.nio.file.Paths;

public record PuzzleRunner(String moduleName) {
    public static void main(String[] args) {
        var puzzleRunner = new PuzzleRunner(PuzzleRunner.class.getPackageName());
        if (args.length > 0) {
            puzzleRunner.run(Integer.parseInt(args[0]));
        } else {
            puzzleRunner.runAll();
        }
    }

    public void runAll() {
        for (var i = 1; i <= 25; i++) {
            run(i);
        }
    }

    public void run(int day) {
        var formattedDay = String.format("%02d", day);
        var fullQualifiedClassName = moduleName + ".day" + formattedDay + ".Puzzle";
        var inputFile = Paths.get("src", "main", "java", moduleName.replace(".", File.separator), "day" + formattedDay, "input.txt");
        try {
            var puzzleInstance = Class.forName(fullQualifiedClassName)
                    .getDeclaredConstructor(InputReader.class)
                    .newInstance(new InputReader(inputFile));
            if (puzzleInstance instanceof Solvable<?, ?> solvable) {
                System.out.println("Day " + day);
                solvable.printParts();
                System.out.println();
            }
        } catch (Exception e) {
            //System.out.println("There was an error loading the puzzle for day " + day + "\n-> " + e);
        }
    }
}