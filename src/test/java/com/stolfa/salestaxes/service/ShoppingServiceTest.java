package com.stolfa.salestaxes.service;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.stolfa.salestaxes.model.Basket;
import com.stolfa.salestaxes.model.Item;
import com.stolfa.salestaxes.model.Recipe;
import com.stolfa.salestaxes.util.ItemBuilder;

public class ShoppingServiceTest extends TestBase {

	@DataProvider(name = "test-recipes")
	public Iterator<Object[]> getRecipeOneData() {
		return getFromJson("/test-recipes.json");
	}
	
	@Test(dataProvider = "test-recipes")
	public void testRecipes(JSONObject dato) throws JSONException, InterruptedException {
		
		/* Create Basket.  */
		Basket basket = ShoppingService.getBasket();
		
		/* Put items in the basket. */
		for (int i = 1; i <= dato.getInt("item.count"); i++) {
			basket.addItem(readItemFromJson(dato, i));
		}
		
		/* Create recipe form items in the basket. */
		Recipe recipe = ShoppingService.getRecipe(basket);

		/* Test items expected output price. */
		for (int i = 1; i <= dato.getInt("item.count"); i++) {
			Item item = recipe.getBasket().getItems().get(i-1);
			Assert.assertEquals(item.getPrice(), dato.getBigDecimal("item_" + i + ".expected.price"));
		}
		
		/* Test total tax and price. */
		Assert.assertEquals(recipe.getTotalCost(), dato.getBigDecimal("expected.totalCost"));
		Assert.assertEquals(recipe.getTotalTaxesAmount(), dato.getBigDecimal("expected.totalTaxesAmount"));

	}

	/**
	 * Return new Item instance, reading json object.
	 * 
	 * @param dato
	 * @param index
	 * @return
	 */
	private Item readItemFromJson(JSONObject dato, int index){
		final String isExemptTaskKey = "item_" + index + ".isExemptTax";
		final String isImportedKey = "item_" + index + ".isImported";
		final String nameKey = "item_" + index + ".name";
		final String quantityKey = "item_" + index + ".quantity";
		final String priceKey = "item_" + index + ".price";
		
		Item item = new ItemBuilder().with($ -> {
			$.isExemptTax = dato.getBoolean(isExemptTaskKey);
			$.isImported = dato.getBoolean(isImportedKey);
		}).with($ -> {
			$.name = dato.getString(nameKey);
			$.quantity = dato.getInt(quantityKey);
			$.price = dato.getDouble(priceKey);
		}).createItem();
		
		return item;
	}

}
