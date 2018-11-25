package com.stolfa.salestaxes;

import com.stolfa.salestaxes.model.Basket;
import com.stolfa.salestaxes.model.Item;
import com.stolfa.salestaxes.service.ShoppingService;
import com.stolfa.salestaxes.util.ItemBuilder;

public class Application {	
	public static void main(String[] args) {
		
		Item item_1 = new ItemBuilder()
			.with($ ->{
				$.isExemptTax = true;
			})
			.with($ ->{
				$.name = "book";
				$.quantity = 1;
				$.price = 12.49;
			})
			.createItem(); 

		Item item_2 = new ItemBuilder()
			.with($ ->{
				$.name = "music CD";
				$.quantity = 1;
				$.price = 14.99;
			})
			.createItem(); 

		Item item_3 = new ItemBuilder()
			.with($ ->{
				$.isExemptTax = true;
			})
			.with($ ->{
				$.name = "chocolate bar";
				$.quantity = 1;
				$.price = 0.85;
			})
			.createItem(); 
		
		Basket basket = ShoppingService.getBasket().addItem(item_1).addItem(item_2).addItem(item_3);
		System.out.println(ShoppingService.getRecipe(basket));

		Item item_4 = new ItemBuilder()
				.with($ ->{
					$.isExemptTax = true;
					$.isImported = true;
				})
				.with($ ->{
					$.name = "imported box of chocolates";
					$.quantity = 1;
					$.price = 10.00;
				})
				.createItem(); 

		Item item_5 = new ItemBuilder()
				.with($ ->{
					$.isImported = true;
				})
				.with($ ->{
					$.name = "imported bottle of perfume";
					$.quantity = 1;
					$.price = 47.50;
				})
				.createItem(); 

		basket = ShoppingService.getBasket().addItem(item_4).addItem(item_5);
		System.out.println(ShoppingService.getRecipe(basket));
		
		Item item_6 = new ItemBuilder()
				.with($ ->{
					$.isImported = true;
				})
				.with($ ->{
					$.name = "imported bottle of perfume";
					$.quantity = 1;
					$.price = 27.99;
				})
				.createItem(); 	
		
		Item item_7 = new ItemBuilder()
				.with($ ->{
					$.name = "bottle of perfume";
					$.quantity = 1;
					$.price = 18.99;
				})
				.createItem(); 	
		
		Item item_8 = new ItemBuilder()
				.with($ ->{
					$.isExemptTax = true;
				})
				.with($ ->{
					$.name = "packet of headache pills";
					$.quantity = 1;
					$.price = 9.75;
				})
				.createItem(); 	
		
		Item item_9 = new ItemBuilder()
				.with($ ->{
					$.isExemptTax = true;
					$.isImported = true;
				})
				.with($ ->{
					$.name = "box of imported chocolates";
					$.quantity = 1;
					$.price = 11.25;
				})
				.createItem(); 	
			
		basket = ShoppingService.getBasket().addItem(item_6).addItem(item_7).addItem(item_8).addItem(item_9);
		System.out.println(ShoppingService.getRecipe(basket));
	}
}
