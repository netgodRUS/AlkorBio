package org.example;
import java.io.*;
import java.util.*;
public class SearchAndCompare {

        public static void main(String[] args) throws IOException {
            // Check if the number of command-line arguments is correct
            if (args.length != 2) {
                System.out.println("Usage: java SearchAndCompare <filename> <number>");
                System.exit(1);
            }

            // Store the command-line arguments
            String filename = args[0];
            int number = Integer.parseInt(args[1]);

            // Create a FileReader object to read the input file
            FileReader fileReader = new FileReader(filename);

            // Create a BufferedReader object to read the lines from the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Initialize variables for storing the previous and current lines
            String prevLine = null;
            String currLine = null;

            // Loop through the lines in the file
            while ((currLine = bufferedReader.readLine()) != null) {
                // Check if the current line contains the target substring
                if (currLine.contains("grabber : ntc reply = NTC,0000,0370,")) {
                    // Extract the numeric parameter from the current line
                    String[] parts = currLine.split(",");
                    int param = Integer.parseInt(parts[3]);

                    // Compare the numeric parameter with the target number
                    if (param >= number) {
                        System.out.println(currLine + " - OK");
                    } else {
                        System.out.println(currLine + " - ERR");
                    }
                }

                // Store the current line as the previous line for the next iteration
                prevLine = currLine;
            }

            // Close the file reader
            fileReader.close();
        }
    }

