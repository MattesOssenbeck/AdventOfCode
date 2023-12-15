package de.ossenbeck.mattes.day08;

public class NodeMapper {
    public static Node map(String line) {
        var data = line.split(" = ");
        var nextElements = data[1].substring(1, data[1].length() - 1).split(", ");
        return new Node(data[0], nextElements[0], nextElements[1]);
    }
}
