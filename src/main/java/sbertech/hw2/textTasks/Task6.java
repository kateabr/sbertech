package sbertech.hw2.textTasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("text.txt"), StandardCharsets.UTF_8);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            if (id < lines.size() && id >= 0) System.out.println(lines.get(id));
            else System.out.printf("Invalid line id (must be 0-%d)\n", lines.size() - 1);
        }
    }
}
