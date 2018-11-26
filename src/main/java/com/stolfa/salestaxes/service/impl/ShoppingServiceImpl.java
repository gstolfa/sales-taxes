package com.stolfa.salestaxes.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.stolfa.salestaxes.model.Basket;
import com.stolfa.salestaxes.model.Item;
import com.stolfa.salestaxes.model.Recipe;
import com.stolfa.salestaxes.service.ShoppingService;

import static com.stolfa.salestaxes.util.ServiceUtil.*;

public class ShoppingServiceImpl implements ShoppingService{

	/**
	 * return new Instance of Basket.
	 * 
	 * @return
	 */
	@Override
	public Basket getBasket(){
		return new Basket();
	}
	
	/**
	 * Create recipe from Basket items.
	 * 
	 * @param basket
	 * @return
	 */
	@Override
	public Recipe getRecipe(Basket basket){
		
		List<Item> taxedItems = basket.getItems().stream().map( item -> computesTax(item) ).collect(Collectors.toList());

		BigDecimal totalCost = taxedItems.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalTaxesAmount = taxedItems.stream().map(i -> i.getTaxAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Recipe recipe = new Recipe(basket.items(taxedItems));
		recipe.setTotalTaxesAmount(totalTaxesAmount);
		recipe.setTotalCost(totalCost);
		
		return recipe;
	}
	
}
