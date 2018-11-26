package com.stolfa.salestaxes.util;

import java.math.BigDecimal;

import com.stolfa.salestaxes.model.Item;

public class ServiceUtil {

	public static final BigDecimal BASIC_TAX_PERCENTAGE = new BigDecimal("10");

	public static final BigDecimal DUTY_TAX_PERCENTAGE = new BigDecimal("5");

	public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");
	
	/**
	 * 
	 * @param percentage
	 * @param price
	 * @param scale
	 * @return
	 */
	public static BigDecimal computesPercentage(BigDecimal percentage, BigDecimal price, Integer scale){
		BigDecimal result = price
				.divide(ONE_HUNDRED)
				.multiply(percentage)
				.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
		return result;
		
	}

	/**
	 *  Add taxes of 10% to all goods except item marked as "exempt tax".
	 *  Add duty tax of 5% to all goods marked as "imported".
	 * 
	 * @param item
	 * @return
	 */
	public static Item computesTax(Item item){
		
		BigDecimal taxAmount = new BigDecimal("0");
		
		if(!item.isExemptTax()){
			BigDecimal basicTax = computesPercentage(BASIC_TAX_PERCENTAGE, item.getPrice(), 2).multiply(new BigDecimal(item.getQuantity()));
			taxAmount = taxAmount.add(basicTax);
		}

		if (item.isImported()) {
			BigDecimal dutyTax = computesPercentage(DUTY_TAX_PERCENTAGE, item.getPrice(), 1).multiply(new BigDecimal(item.getQuantity()));
			taxAmount = taxAmount.add(dutyTax);
		}

		item.setPrice(item.getPrice().multiply(new BigDecimal(item.getQuantity())).add(taxAmount));
		item.setTaxAmount(taxAmount);
		
		return item;
	}

}
