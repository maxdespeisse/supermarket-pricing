package mde.supermarketpricing.pricingmethods;

import java.math.BigDecimal;

public class PackagedOfferPricingMethod implements PricingMethod {
	
	private final int quantityToBuy;
	
	private final BigDecimal packagePrice;
	
	public PackagedOfferPricingMethod(int quantityToBuy, BigDecimal packagePrice) {
		if (quantityToBuy < 1) throw new IllegalArgumentException("quantityToBuy must be greater than 1");
		if (packagePrice == null) throw new IllegalArgumentException("packagePrice cannot be null");
		
		this.quantityToBuy = quantityToBuy;
		this.packagePrice = packagePrice;
	}

	@Override
	public BigDecimal computePrice(BigDecimal unitPrice, int quantity) {
		int numberOfPackage = quantity / quantityToBuy;
		int quantityOutOfTheOffer = quantity % quantityToBuy;
		
		BigDecimal priceInTheOffer = packagePrice.multiply(BigDecimal.valueOf(numberOfPackage));
		BigDecimal priceOutOfTheOffer = unitPrice.multiply(BigDecimal.valueOf(quantityOutOfTheOffer));

		return priceInTheOffer.add(priceOutOfTheOffer);
	}
	
}
