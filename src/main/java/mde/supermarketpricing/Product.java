package mde.supermarketpricing;

import java.math.BigDecimal;

import mde.supermarketpricing.pricingmethods.PricingMethod;

public class Product {
	
	private final String name;
	
	private final BigDecimal price;
	
	private final PricingMethod pricingMethod;
	
	Product(String name, BigDecimal price, PricingMethod pricingMethod) {
		if (price == null) throw new IllegalArgumentException("price cannot be null");
		if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("price cannot be negative");
		if (name == null) throw new IllegalArgumentException("name cannot be null");
		this.name = name;
		this.price = price;
		this.pricingMethod = pricingMethod;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public BigDecimal computePrice(final double amount) {
		return pricingMethod.computePrice(price, amount);
	}
}
