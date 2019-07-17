package mde.supermarketpricing.pricingmethods;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class PackageOfferPricingMethodTest {

	@Test
	public void shouldPayThreeItemsAtUnitPrice() {
		final PricingMethod threeForADollarOffer = new PackagedOfferPricingMethod(3, BigDecimal.valueOf(1));

		BigDecimal totalPrice = threeForADollarOffer.computePrice(BigDecimal.valueOf(9999), 3);

		assertEquals(Double.valueOf(1), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayNineItemsAtSpecialPricePlusOneAtUnitPrice() {
		final PricingMethod threeForADollarOffer = new PackagedOfferPricingMethod(3, BigDecimal.valueOf(1));

		BigDecimal totalPrice = threeForADollarOffer.computePrice(BigDecimal.valueOf(0.6), 10);

		assertEquals(Double.valueOf(3.6), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayAllItemsAtSpecialOfferPrice() {
		final PricingMethod threeForADollarOffer = new PackagedOfferPricingMethod(1, BigDecimal.valueOf(1));
		
		BigDecimal totalPrice = threeForADollarOffer.computePrice(BigDecimal.valueOf(2), 9);
		
		assertEquals(Double.valueOf(9), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

}
