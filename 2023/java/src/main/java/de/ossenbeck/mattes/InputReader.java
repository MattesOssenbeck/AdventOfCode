package de.ossenbeck.mattes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {
    private static final String PATH_TO_INPUT_FILE_TEMPLATE = "src/%s/java/de/ossenbeck/mattes/day%s/input%s.txt";

    public static List<String> readAsList(String directory, String day) {
        return readAsList(directory, day, "");
    }

    public static List<String> readAsList(String directory, String day, String postfix) {
        try {
            return Files.readAllLines(getPath(directory, day, postfix), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readAsString(String directory, String day) {
        return readAsString(directory, day, "");
    }

    public static String readAsString(String directory, String day, String postfix) {
        try {
            return Files.readString(getPath(directory, day, postfix), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getPath(String directory, String day, String postfix) {
        return new File(PATH_TO_INPUT_FILE_TEMPLATE.formatted(directory, day, postfix)).toPath();
    }
}
