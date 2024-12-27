package de.ossenbeck.mattes;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class BenchmarkState {
    private final String moduleName;
    @Param("1")
    public int day;

    Solvable<?, ?> solvable;

    public BenchmarkState() {
        this.moduleName = BenchmarkState.class.getPackageName();
    }

    @Setup
    public void createPuzzle() throws Exception {
        var formattedDay = String.format("%02d", day);
        var fullQualifiedClassName = moduleName + ".day" + formattedDay + ".Puzzle";
        var puzzleInstance = Class.forName(fullQualifiedClassName)
                .getDeclaredConstructor(InputReader.class)
                .newInstance(InputReader.builder().day(day));
        if (puzzleInstance instanceof Solvable<?, ?> s) {
            this.solvable = s;
        }
    }
}