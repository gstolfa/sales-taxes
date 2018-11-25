package com.stolfa.salestaxes.util;

import java.util.function.Consumer;

import com.stolfa.salestaxes.model.Item;

public class ItemBuilder {
	
	public Integer quantity;
	public Boolean isImported = false;
	public Boolean isExemptTax = false;
	public String name;
	public double price;
	public double taxAmount;

	public ItemBuilder with(Consumer<ItemBuilder> builderFunction) {
		builderFunction.accept(this);
		return this;
	}

	public Item createItem() {
        return new Item(quantity, isImported, isExemptTax, name, price, taxAmount);
    }

}
