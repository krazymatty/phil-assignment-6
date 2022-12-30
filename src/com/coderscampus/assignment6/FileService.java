package com.coderscampus.assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class FileService {

    public static Map<LocalDate, Integer> readCsvFile(String filePath, Boolean hasHeader) {
        // Create a Map to store the data from the .csv file
        Map<LocalDate, Integer> map = new LinkedHashMap<>();

        // Read the .csv file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = hasHeader; // flag to skip the header line
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    // Skip the header line
                    isHeader = false;
                    continue;
                }
                // Split the line into columns using the "," delimiter
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                if (tokenizer.countTokens() >= 2) {
                    // Read the first column as a LocalDate
                    String dateString = tokenizer.nextToken();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy", Locale.US);
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    // Read the second column as an int
                    int value = Integer.parseInt(tokenizer.nextToken());
                    // Add the date and value to the map
                    map.put(date, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}