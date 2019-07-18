package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public class PackagedOfferPricingMethod implements PricingMethod {
	
	private final int quantityToBuy;
	
	private final BigDecimal packagePrice;
	
	public PackagedOfferPricingMethod(final int quantityToBuy, final BigDecimal packagePrice) {
		if (quantityToBuy < 1) throw new IllegalArgumentException("quantityToBuy must be equal or greater than 1");
		if (packagePrice == null) throw new IllegalArgumentException("packagePrice cannot be null");
		
		this.quantityToBuy = quantityToBuy;
		this.packagePrice = packagePrice;
	}

	@Override
	public BigDecimal computePrice(final BigDecimal unitPrice, final double quantity) {
		int quantityRoundedUp = (int) Math.ceil(quantity);
		int numberOfPackage = quantityRoundedUp / quantityToBuy;
		int quantityOutOfTheOffer = quantityRoundedUp % quantityToBuy;
		
		BigDecimal priceInTheOffer = packagePrice.multiply(BigDecimal.valueOf(numberOfPackage));
		BigDecimal priceOutOfTheOffer = unitPrice.multiply(BigDecimal.valueOf(quantityOutOfTheOffer));

		return priceInTheOffer.add(priceOutOfTheOffer);
	}
	
}
