package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public interface PricingMethod {
	public BigDecimal computePrice(final BigDecimal unitPrice, final int quantity);
}
