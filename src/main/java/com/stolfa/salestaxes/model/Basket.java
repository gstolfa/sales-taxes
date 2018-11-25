package com.stolfa.salestaxes.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<Item> items = new ArrayList<>();

	public Basket addItem(Item item) {
		this.items.add(item);
		return this;
	}

	public Basket removeItem(Item item) {
		this.items.remove(item);
		return this;
	}

	public Basket empty() {
		this.items.clear();
		return this;
	}

	public List<Item> getItems() {
		return items;
	}

	public Basket items(List<Item> items) {
		this.items = items;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		this.items.forEach(i -> {
			builder.append(i.getQuantity() + " " + i.getName() + ": " + i.getPrice() + "\n");
		});

		return builder.toString();
	}

}
