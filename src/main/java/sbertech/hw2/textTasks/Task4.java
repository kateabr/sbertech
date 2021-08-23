package sbertech.hw2.textTasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("text.txt"), StandardCharsets.UTF_8);
        Collections.reverse(lines);
        lines.forEach(System.out::println);
    }
}
