package sbertech.hw2.textTasks;

import sbertech.hw2.MyBackwardsListIterator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task5 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("text.txt"), StandardCharsets.UTF_8);
        MyBackwardsListIterator<String> mbli = new MyBackwardsListIterator<>(lines);
        mbli.forEachRemaining(System.out::println);
    }
}
