package com.stolfa.salestaxes.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.stolfa.salestaxes.model.Basket;
import com.stolfa.salestaxes.model.Item;
import com.stolfa.salestaxes.model.Recipe;

public class ShoppingService {
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
	
	public static final BigDecimal BASIC_TAX_PERCENTAGE = new BigDecimal("10");

	public static final BigDecimal DUTY_TAX_PERCENTAGE = new BigDecimal("5");

	/**
	 * return new Instance of Basket.
	 * 
	 * @return
	 */
	public static Basket getBasket(){
		return new Basket();
	}

	/**
	 *  Add taxes of 10% to all goods except item marked as "exempt tax".
	 *  Add duty tax of 5% to all goods marked as "imported".
	 * 
	 * @param item
	 * @return
	 */
	private static Item computesTax(Item item){
		
		BigDecimal taxAmount = new BigDecimal("0");
		
		if(!item.isExemptTax()){
			BigDecimal basicTax = item.getPrice()
					.divide(ONE_HUNDRED)
					.multiply(BASIC_TAX_PERCENTAGE)
					.multiply(new BigDecimal(item.getQuantity()))
					.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			
			taxAmount = taxAmount.add(basicTax);
		}

		if (item.isImported()) {
			BigDecimal dutyTax = item.getPrice()
					.divide(ONE_HUNDRED)
					.multiply(DUTY_TAX_PERCENTAGE)
					.multiply(new BigDecimal(item.getQuantity()))
					.setScale(1, BigDecimal.ROUND_HALF_DOWN);
			taxAmount = taxAmount.add(dutyTax);
		}

		item.setPrice(item.getPrice().multiply(new BigDecimal(item.getQuantity())).add(taxAmount));
		item.setTaxAmount(taxAmount);
		
		return item;
	}
	
	/**
	 * Create recipe from Basket items.
	 * 
	 * @param basket
	 * @return
	 */
	public static Recipe getRecipe(Basket basket){
		
		List<Item> taxedItems = basket.getItems().stream().map( item -> computesTax(item) ).collect(Collectors.toList());

		BigDecimal totalCost = taxedItems.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalTaxesAmount = taxedItems.stream().map(i -> i.getTaxAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Recipe recipe = new Recipe(basket.items(taxedItems));
		recipe.setTotalTaxesAmount(totalTaxesAmount);
		recipe.setTotalCost(totalCost);
		
		return recipe;
	}
	
}
