package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public class BasicPricingMethod implements PricingMethod {

	@Override
	public BigDecimal computePrice(BigDecimal price, int quantity) {
		BigDecimal total = price.multiply(new BigDecimal(quantity));
		return total;
	}

}
