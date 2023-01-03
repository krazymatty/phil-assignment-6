package com.coderscampus.assignment6;

import java.time.YearMonth;

public class CarSales {
	private YearMonth yearMonth;
	private Integer monthlySales;

	public CarSales(YearMonth yearMonth, Integer monthlySales) {
		this.yearMonth = yearMonth;
		this.monthlySales = monthlySales;
	}

	public YearMonth getDate() {
		return yearMonth;
	}

	public Integer getMonthlySales() {
		return monthlySales;
	}

	@Override
	public String toString() {
		return yearMonth + " -> " + monthlySales;
	}

}
