package de.ossenbeck.mattes;

public class PuzzleRunner {
    private final String moduleName;

    public PuzzleRunner() {
        this.moduleName = PuzzleRunner.class.getPackageName();
    }

    public void run(int day) throws Exception {
        var formattedDay = String.format("%02d", day);
        var fullQualifiedClassName = moduleName + ".day" + formattedDay + ".Puzzle";
        var puzzleInstance = Class.forName(fullQualifiedClassName)
                .getDeclaredConstructor(InputReader.class)
                .newInstance(InputReader.builder().day(day));
        if (puzzleInstance instanceof Solvable<?, ?> solvable) {
            System.out.println("Day " + day);
            solvable.printParts();
            System.out.println();
        }
    }
}