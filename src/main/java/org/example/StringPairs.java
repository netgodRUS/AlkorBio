package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class StringPairs {

        public static void main(String[] args) throws IOException {
            if (args.length < 2) {
                System.out.println("Usage: java StringPairs <filename> <board>");
                System.exit(1);
            }

            String filename = args[0];
            String board = args[1];

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();

            List<String[]> pairs = new ArrayList<>();

            for (int i = 0; i < lines.size() - 1; i++) {
                String currentLine = lines.get(i);
                String nextLine = lines.get(i + 1);

                if (currentLine.contains("info: " + board + " : Sending command: ") &&
                        nextLine.contains("info: " + board + " : Got reply: ")) {
                    String[] pair = {currentLine, nextLine};
                    pairs.add(pair);
                }
            }

            for (String[] pair : pairs) {
                System.out.println(pair[0] + "\n" + pair[1]);
            }
        }
    }

