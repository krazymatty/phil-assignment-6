package com.coderscampus.assignment6;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SalesReportService {
	private FileService fileService = new FileService();
	private List<CarSales> carSales = new ArrayList<CarSales>();

	//This is the Main Starting point of the program.
	public void generateSalesReports() {
		returnSalesReport("Model 3", "model3.csv", true);
		returnSalesReport("Model S", "modelS.csv", true);
		returnSalesReport("Model X", "modelX.csv", true);
	}

	// This method was broken out of the print method for debugging.
	private void returnSalesReport(String title, String fileName, Boolean headers) {
		carSales = loadData(fileName, headers);
		printSalesReport(title, carSales);
	}
	// FileService reader returns the Array List of CarSales objects.
	private List<CarSales> loadData(String fileName, Boolean headers) {
		return carSales = fileService.readCsvFile(fileName, headers);
	}

	private void printSalesReport(String title, List<CarSales> carSales) {
		System.out.println(title + " Yearly Sales Report");
		System.out.println("--------------------------");
		Map<Object, Integer> totalSalesByYear = carSales.stream().collect(Collectors
				.groupingBy(sales -> sales.getDate().getYear(), Collectors.summingInt(CarSales::getMonthlySales)));
		
		totalSalesByYear.forEach((t, u) -> System.out.println(t + " -> " + u));

		Optional<CarSales> maxSales = carSales.stream().max(Comparator.comparingInt(CarSales::getMonthlySales));
		Optional<CarSales> minSales = carSales.stream().min(Comparator.comparingInt(CarSales::getMonthlySales));
		if (maxSales.isPresent()) {
			YearMonth maxSalesDate = maxSales.get().getDate();
			System.out.println("The best month for " + title + " was: " + maxSalesDate);
		}
		if (minSales.isPresent()) {
			YearMonth minSalesDate = minSales.get().getDate();
			System.out.println("The worst month for " + title + " was: " + minSalesDate + "\n");
		}
	}

}
