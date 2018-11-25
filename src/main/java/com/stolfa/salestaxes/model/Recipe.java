package com.stolfa.salestaxes.model;

import java.math.BigDecimal;

public class Recipe {

	private Basket basket;
	private BigDecimal totalCost;
	private BigDecimal totalTaxesAmount;

	public Recipe(Basket basket) {
		this.basket = basket;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getTotalTaxesAmount() {
		return totalTaxesAmount;
	}

	public void setTotalTaxesAmount(BigDecimal totalTaxesAmount) {
		this.totalTaxesAmount = totalTaxesAmount;
	}

	@Override
	public String toString() {
		return basket + "Sales Taxes: " + this.totalTaxesAmount + "\n" + "Total: " + this.getTotalCost();
	}

}
