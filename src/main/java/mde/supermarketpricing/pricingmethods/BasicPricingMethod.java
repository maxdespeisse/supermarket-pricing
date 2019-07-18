package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public class BasicPricingMethod implements PricingMethod {

	@Override
	public BigDecimal computePrice(final BigDecimal price, final double amount) {
		return price.multiply(BigDecimal.valueOf(amount));
	}

}
