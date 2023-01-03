package com.coderscampus.assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileService {

	public List<CarSales> readCsvFile(String filePath, Boolean hasHeaders) {
		List<CarSales> carSales = new ArrayList<>();
		// Create a DateTimeFormatter object with the "MMM-yy" pattern
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");

		// Read the .csv file
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isHeader = hasHeaders; // flag to skip the header line
			while ((line = reader.readLine()) != null) {
				if (isHeader) {
					// Skip the header line
					isHeader = false;
					continue;
				}
				// Split the line into columns using the "," delimiter
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				if (tokenizer.countTokens() >= 2) {
					String yearMonthString = tokenizer.nextToken();
					YearMonth yearMonth = YearMonth.parse(yearMonthString, formatter);
					int monthlySales = Integer.parseInt(tokenizer.nextToken());
					carSales.add(new CarSales(yearMonth, monthlySales));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carSales;

	}

}