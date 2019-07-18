package mde.supermarketpricing.pricingmethods;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class OneProductOfferedPricingMethodTest {
	final PricingMethod oneOfferedForTwoBought = new OneProductOfferedPricingMethod(2);

	@Test
	public void shouldPayTwoAndGetOneForFree() {
		BigDecimal totalPrice = oneOfferedForTwoBought.computePrice(BigDecimal.valueOf(5), 3);

		assertEquals(Double.valueOf(10), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayZeroForZeroItems() {
		BigDecimal totalPrice = oneOfferedForTwoBought.computePrice(BigDecimal.valueOf(5), 0);

		assertEquals(Double.valueOf(0), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayOneItemAtUnitPrice() {
		BigDecimal totalPrice = oneOfferedForTwoBought.computePrice(BigDecimal.valueOf(5), 1);
		
		assertEquals(Double.valueOf(5), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayTwoItemsAtUnitPrice() {
		BigDecimal totalPrice = oneOfferedForTwoBought.computePrice(BigDecimal.valueOf(5), 2);

		assertEquals(Double.valueOf(10), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}
	
	@Test
	public void shouldPayThreeItemsAndGetOneFree() {
		BigDecimal totalPrice = oneOfferedForTwoBought.computePrice(BigDecimal.valueOf(5), 4);
		
		assertEquals(Double.valueOf(15), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayFourItemsAndGetOneFree() {
		BigDecimal totalPrice = oneOfferedForTwoBought.computePrice(BigDecimal.valueOf(5), 5);
		
		assertEquals(Double.valueOf(20), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}
}
