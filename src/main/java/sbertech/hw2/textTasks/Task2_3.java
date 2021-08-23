package sbertech.hw2.textTasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2_3 {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("text.txt"), StandardCharsets.UTF_8)) {
            lines.flatMap(line -> Stream.of(line.toLowerCase(Locale.ROOT).split("[\s,.:!\n()]")))
                    .filter(e -> e.length() > 0)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                    .forEach(System.out::println);
        }
    }
}
