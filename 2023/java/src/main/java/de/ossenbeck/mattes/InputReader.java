package de.ossenbeck.mattes;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class InputReader {
    public static Stream<String> readAsStream(String filename) throws Exception {
        return Files.lines(
                Paths.get(InputReader.class.getResource("/" + filename).toURI()),
                StandardCharsets.UTF_8);
    }

    public static List<String> readAsList(String filename) throws Exception {
        return Files.readAllLines(
                Paths.get(InputReader.class.getResource("/" + filename).toURI()),
                StandardCharsets.UTF_8);
    }

    public static String readAsString(String filename) throws Exception {
        return Files.readString(
                Paths.get(InputReader.class.getResource("/" + filename).toURI()),
                StandardCharsets.UTF_8);
    }
}
