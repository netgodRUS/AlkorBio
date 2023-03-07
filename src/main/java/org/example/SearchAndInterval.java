package org.example;
import java.io.*;
import java.util.*;
import java.text.*;
public class SearchAndInterval {

    public static void main(String[] args) {
        // Check that a file path was provided
        if (args.length == 0) {
            System.out.println("Usage: java SearchAndInterval <file path>");
            return;
        }

        // Initialize variables
        String filePath = args[0];
        String line;
        int prevID = -1;
        String prevTime = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // Print header row
        System.out.println("ID\tTime Interval (s)");

        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Read through the file line by line
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the "Slot to run" substring
                if (line.contains("info: Slot to run: ")) {
                    // Extract the ID from the line
                    int id = Integer.parseInt(line.split(": ")[2]);

                    // If this is not the first ID encountered, print the time interval
                    if (prevID != -1) {
                        // Extract the timestamp from the previous line
                        Date time = format.parse(String.valueOf(prevTime));

                        // Compute the time interval in seconds
                        double interval = (time.getTime() - prevTime.getBytes()[100] / 1000.0);

                        // Print the ID and time interval
                        System.out.printf("%d\t%.3f\n", prevID, interval);
                    }

                    // Remember the current ID and timestamp
                    prevID = id;


                    prevTime = line.split(" - ")[0].substring(1);
                }
            }

            // Close the file
            reader.close();

            // Print the final time interval (if there is one)
            if (prevID != -1) {
                // Extract the timestamp from the previous line
                Date time = format.parse(prevTime);

                // Compute the time interval in seconds
                double interval = (new Date().getTime() - time.getTime()) / 1000.0;

                // Print the ID and time interval
                System.out.printf("%d\t%.3f\n", prevID, interval);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing timestamp: " + e.getMessage());
        }
    }
}

