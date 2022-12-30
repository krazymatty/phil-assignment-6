package com.coderscampus.assignment6;

import java.time.LocalDate;
import java.util.Map;

public class SalesReportApplication {

	public static void main(String[] args) {
		Map<LocalDate, Integer> map = FileService.readCsvFile("model3.csv", true);
		System.out.println(map);

	}

}
