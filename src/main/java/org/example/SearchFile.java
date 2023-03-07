package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class SearchFile {


        public static void main(String[] args) throws IOException {
            String filePath = "/home/dexter/Documents/Tester/Logs/sc_device.log"; // replace with your file path
            int param = 300; // replace with your numeric parameter

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("grabber : ntc reply = NTC,0000,0370,")) {
                    int value = Integer.parseInt(line.substring(line.length() - 4));
                    if (value >= param) {
                        System.out.println(line + " - OK");
                    } else {
                        System.out.println(line + " - ERR");
                    }
                }
            }
            reader.close();
        }
    }


