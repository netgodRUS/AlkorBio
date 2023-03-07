package org.example;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SearchFileB {

        private static final Pattern COMMAND_PATTERN = Pattern.compile("info: (grabber|wash|tip|reagents) : Sending command: (\\d+)");
        private static final Pattern REPLY_PATTERN = Pattern.compile("info: (grabber|wash|tip|reagents) : Got reply: (\\d+) NAK");

        public static void main(String[] args) {
            if (args.length != 1) {
                System.err.println("Usage: java SearchFile <file_path>");
                System.exit(1);
            }

            String filePath = args[0];
            File file = new File(filePath);

            if (!file.exists()) {
                System.err.println("File not found: " + filePath);
                System.exit(1);
            }

            try {
                Scanner scanner = new Scanner(file);
                HashMap<String, String> commandIds = new HashMap<>();
                String currentBoard = null;

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Matcher commandMatcher = COMMAND_PATTERN.matcher(line);
                    Matcher replyMatcher = REPLY_PATTERN.matcher(line);

                    if (commandMatcher.find()) {
                        currentBoard = commandMatcher.group(1);
                        String id = commandMatcher.group(2);
                        commandIds.put(currentBoard, id);
                    } else if (replyMatcher.find()) {
                        String board = replyMatcher.group(1);
                        String id = replyMatcher.group(2);
                        String expectedId = commandIds.get(board);
                        if (expectedId != null && !expectedId.equals(id)) {
                            System.out.println("ERROR: Mismatched ID on " + board + " board");
                        }
                    }
                }

                scanner.close();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                System.exit(1);
            }
        }
    }

