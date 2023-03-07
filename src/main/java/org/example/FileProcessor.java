package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class FileProcessor {
   public static void main(String[] args) {

            // Replace with the actual file path
            String filePath = "/home/dexter/Documents/Тестер/Logs/200731.LOG";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                String worklistName = null;
                Map<String, Integer> resultMap = new HashMap<>();

                while ((line = br.readLine()) != null) {
                    if (line.contains("Worklist name : filePath")) {
                        worklistName = line.substring(line.indexOf(":") + 1).trim();
                        resultMap.put(worklistName, 0);
                    } else if (line.contains("CYCLE complete")) {
                        if (worklistName != null) {
                            int count = resultMap.get(worklistName);
                            resultMap.put(worklistName, count + 1);
                        }
                    }
                }

                // Output the results
                System.out.println("File path\t\tNumber of lines");
                for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
                    System.out.println(entry.getKey() + "\t\t" + entry.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


