package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public class OneProductOfferedPricingMethod implements PricingMethod {
	
	private final int quantityToBuy;
	
	public OneProductOfferedPricingMethod(int quantityToBuy) {
		if (quantityToBuy < 1) throw new IllegalArgumentException("quantityToBuy must be equal or greater than 1");
		this.quantityToBuy = quantityToBuy;
	}

	@Override
	public BigDecimal computePrice(BigDecimal unitPrice, int quantity) {
		if (quantity > quantityToBuy) {
			final int offeredQuantity = quantity / (quantityToBuy + 1);
			return unitPrice.multiply(BigDecimal.valueOf((quantity - offeredQuantity)));
		}
		return unitPrice.multiply(BigDecimal.valueOf((quantity)));
	}

}
