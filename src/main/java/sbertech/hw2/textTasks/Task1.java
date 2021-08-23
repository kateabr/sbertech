package sbertech.hw2.textTasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.stream.Stream;

public class Task1 {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("text.txt"), StandardCharsets.UTF_8)) {
            lines.flatMap(line -> Stream.of(line.toLowerCase(Locale.ROOT).split("[\s,.:!\n()]")))
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
        }
    }
}
