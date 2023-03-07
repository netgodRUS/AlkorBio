package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class SearchAndCompareC {


        public static void main(String[] args) {
            if (args.length < 1) {
                System.out.println("Usage: java SearchAndCompare <filename>");
                System.exit(1);
            }

            String filename = args[0];

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                String prevLine = null;
                String prevId = null;
                long prevTimestamp = 0;
                System.out.println("ID\tTime interval (s)");
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(" ");
                    if (tokens.length >= 5 && tokens[2].equals("Slot") && tokens[3].equals("to") && tokens[4].equals("run:")) {
                        String id = tokens[5];
                        if (prevLine != null && prevId != null && id.equals(prevId)) {
                            long timestamp = System.currentTimeMillis();
                            long interval = (timestamp - prevTimestamp);
                            System.out.println(prevId + "\t" + (interval / 1000.0));
                        }
                        prevId = id;
                        prevTimestamp = System.currentTimeMillis();
                    }
                    prevLine = line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

