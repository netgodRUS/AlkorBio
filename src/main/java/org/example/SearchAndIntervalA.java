package org.example;
import java.io.*;
import java.text.*;
import java.util.*;
public class SearchAndIntervalA {

        public static void main(String[] args) {
            // check if the correct number of arguments are provided
            if (args.length != 1) {
                System.err.println("Usage: java SearchAndInterval <file>");
                System.exit(1);
            }

            // define the substrings to search for
            String substring1 = "info: Slot to run: ";
            String substring2 = "info: Slot to run: ";

            // define the date format for parsing the timestamps
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

            // read the file line by line and search for the substrings
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                String line;
                String prevLine = null;
                String prevTime = null;
                int id = -1;
                while ((line = br.readLine()) != null) {
                    // check if the line contains the first substring
                    if (line.contains(substring1)) {
                        int index1 = line.indexOf(substring1) + substring1.length();
                        int index2 = line.indexOf(",", index1);
                        String strId = line.substring(index1, index2);
                        int currId = Integer.parseInt(strId);

                        // check if this is the first ID or a new ID
                        if (id == -1 || currId == id + 1) {
                            id = currId;

                            // check if the previous line contains the second substring
                            if (prevLine != null && prevLine.contains(substring2)) {
                                int prevIndex1 = prevLine.indexOf(substring2) + substring2.length();
                                int prevIndex2 = prevLine.indexOf(",", prevIndex1);
                                String prevStrId = prevLine.substring(prevIndex1, prevIndex2);
                                int prevId = Integer.parseInt(prevStrId);

                                // check if the IDs match
                                if (prevId == id) {
                                    // get the timestamps from the lines
                                    String time = line.substring(0, 23);
                                    String prevTimeValue = String.valueOf(prevTime);
                                    Date time1 = format.parse(prevTimeValue);
                                    Date time2 = format.parse(time);

                                    // calculate the time interval in seconds with thousandths
                                    long diff = time2.getTime() - time1.getTime();
                                    String interval = String.format("%.3f", diff / 1000.0);

                                    // print the results
                                    System.out.printf("%d\t%s%n", id, interval);
                                }
                            }
                        }
                    }
                    // store the previous line and timestamp
                    prevLine = line;
                    prevTime = line.substring(0, 23);
                }
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
                System.exit(1);
            } catch (ParseException e) {
                System.err.format("ParseException: %s%n", e);
                System.exit(1);
            }
        }
    }

