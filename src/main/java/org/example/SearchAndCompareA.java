package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class SearchAndCompareA {

        public static void main(String[] args) {
            if (args.length < 2) {
                System.out.println("Usage: java SearchAndCompare <filename> <threshold>");
                return;
            }
            String filename = args[0];
            int threshold = Integer.parseInt(args[1]);
            int lineNo = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lineNo++;
                    if (line.contains("grabber : ntc reply = NTC,0000,0370,")) {
                        int startIndex = line.indexOf("grabber : ntc reply = NTC,0000,0370,") + 35;
                        int endIndex = startIndex + 4;
                        int value = Integer.parseInt(line.substring(startIndex, endIndex));
                        if (value >= threshold) {
                            System.out.println("Line " + lineNo + ": OK");
                        } else {
                            System.out.println("Line " + lineNo + ": ERR");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid threshold value: " + args[1]);
            }
        }
    }

