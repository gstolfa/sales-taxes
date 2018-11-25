package com.stolfa.salestaxes.model;

import java.math.BigDecimal;

public class Item {

	private Integer quantity;
	private Boolean isImported;
	private Boolean isExemptTax;
	private String name;
	private BigDecimal price;
	private BigDecimal taxAmount;

	public Item(Integer quantity, Boolean isImported, Boolean isExemptTax, String name, double price, double taxAmount) {
		super();
		this.quantity = quantity;
		this.isImported = isImported;
		this.isExemptTax = isExemptTax;
		this.name = name;
		this.price = new BigDecimal(String.valueOf(price));
		this.taxAmount = new BigDecimal(String.valueOf(taxAmount));
	}

	public Boolean isExemptTax() {
		return isExemptTax;
	}

	public void setIsExemptTax(Boolean isExemptTax) {
		this.isExemptTax = isExemptTax;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean isImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

}
