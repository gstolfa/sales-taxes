package com.stolfa.salestaxes.service;

import com.stolfa.salestaxes.model.Basket;
import com.stolfa.salestaxes.model.Recipe;

public interface ShoppingService {

	Basket getBasket();
	
	Recipe getRecipe(Basket basket);
	
}
