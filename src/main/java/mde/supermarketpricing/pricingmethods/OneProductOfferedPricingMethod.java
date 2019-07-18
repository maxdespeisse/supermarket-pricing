package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public class OneProductOfferedPricingMethod implements PricingMethod {
	
	private final int quantityToBuy;
	
	public OneProductOfferedPricingMethod(final int quantityToBuy) {
		if (quantityToBuy < 1) throw new IllegalArgumentException("quantityToBuy must be equal or greater than 1");
		this.quantityToBuy = quantityToBuy;
	}

	@Override
	public BigDecimal computePrice(final BigDecimal unitPrice, final double quantity) {
		final int quantityRoundedUp = (int) Math.ceil(quantity);
		if (quantityRoundedUp > quantityToBuy) {
			final int offeredQuantity = quantityRoundedUp / (quantityToBuy + 1);
			return unitPrice.multiply(BigDecimal.valueOf((quantityRoundedUp - offeredQuantity)));
		}
		return unitPrice.multiply(BigDecimal.valueOf((quantityRoundedUp)));
	}

}
