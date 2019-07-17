package mde.supermarketpricing.pricingmethods;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class BasicPricingMethodTest {

	final PricingMethod basicMethod = new BasicPricingMethod();

	@Test
	public void shouldPayThreeItemsAtUnitPrice() {
		BigDecimal totalPrice = basicMethod.computePrice(BigDecimal.valueOf(5), 3);

		assertEquals(Double.valueOf(15), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayZeroForZeroItems() {
		BigDecimal totalPrice = basicMethod.computePrice(BigDecimal.valueOf(5), 0);

		assertEquals(Double.valueOf(0), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

	@Test
	public void shouldPayOneItemAtUnitPrice() {
		BigDecimal totalPrice = basicMethod.computePrice(BigDecimal.valueOf(5), 1);

		assertEquals(Double.valueOf(5), (Double) totalPrice.setScale(2, RoundingMode.CEILING).doubleValue());
	}

}
