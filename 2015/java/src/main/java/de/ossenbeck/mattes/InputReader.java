package de.ossenbeck.mattes;

import de.ossenbeck.mattes.common.Grid;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class InputReader {
    private static final Path COMMON_PATH = Paths.get("src", "main", "java", "de", "ossenbeck", "mattes");
    private static final Path COMMON_PATH_TEST = Paths.get("src", "test", "java", "de", "ossenbeck", "mattes");
    private final Path input;

    private InputReader(Path path, int day) {
        this(path, day, "");
    }

    private InputReader(Path path, int day, String prefix) {
        this.input = path.resolve("day%02d".formatted(day), "%sinput.txt".formatted(prefix));
    }

    public static DayStep builder() {
        return day -> new InputReader(COMMON_PATH, day);
    }

    public static PrefixStep testBuilder() {
        return prefix -> day -> new InputReader(COMMON_PATH_TEST, day, prefix);
    }

    @FunctionalInterface
    public interface PrefixStep {
        DayStep prefix(String prefix);
    }

    @FunctionalInterface
    public interface DayStep {
        InputReader day(int day);
    }

    public String asString() {
        try {
            return Files.readString(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public List<String> asList() {
        try {
            return Files.readAllLines(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Stream<String> asStream() {
        try {
            return Files.lines(input, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Grid asGrid() {
        return new Grid(
                asStream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new)
        );
    }

}
