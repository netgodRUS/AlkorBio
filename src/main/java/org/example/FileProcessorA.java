package org.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class FileProcessorA {

        public static void main(String[] args) {
            String path = "/home/dexter/Documents/Tester/Logs/200731.LOG";
            String searchString = "Worklist name";
            String linePrefix = "CYCLE complete";

            try {
                Map<String, Integer> results = new HashMap<>();

                File file = new File(path);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                String currentWorklist = null;
                int count = 0;

                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains(searchString)) {
                        currentWorklist = line.substring(line.indexOf(searchString) + searchString.length() + 2);
                        count = 0;
                    } else if (line.startsWith(linePrefix)) {
                        if (currentWorklist != null) {
                            count++;
                            results.put(currentWorklist, count);
                        }
                    }
                }

                fileReader.close();

                System.out.println("File path\t\tNumber of lines");
                for (Map.Entry<String, Integer> entry : results.entrySet()) {
                    System.out.println(entry.getKey() + "\t\t" + entry.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

