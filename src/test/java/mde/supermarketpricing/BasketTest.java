package mde.supermarketpricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import mde.supermarketpricing.pricingmethods.BasicPricingMethod;
import mde.supermarketpricing.pricingmethods.PackagedOfferPricingMethod;
import mde.supermarketpricing.pricingmethods.PricingMethod;

public class BasketTest {
	
	final PricingMethod basicPricingMethod = new BasicPricingMethod();
	final PricingMethod threeForADollarOffer = new PackagedOfferPricingMethod(3, BigDecimal.valueOf(1));
	
	final Product chips = new Product("Chips Lays", BigDecimal.valueOf(4.99), basicPricingMethod);
	final Product pen = new Product("Bic pen", BigDecimal.valueOf(400), basicPricingMethod);
	final Product tv = new Product("TV-FHD LG", BigDecimal.valueOf(400), basicPricingMethod);

	@Test
	public void shouldHave3ProductsInBasket() {
		Basket basket = new Basket();
		basket.addProduct(chips);
		basket.addProduct(pen);
		basket.addProduct(tv);

		assertEquals(3, basket.getProductsMap().size());
	}

	@Test
	public void shouldHaveTotalPriceOf800() {
		Basket basket = new Basket();
		basket.addProduct(tv);
		basket.addProduct(pen);
		
		assertEquals(Double.valueOf(800), basket.getTotalPrice());
	}

	@Test
	public void shouldHaveTotalPriceOf0() {
		Basket basket = new Basket();

		assertEquals(Double.valueOf(0), basket.getTotalPrice());
	}

	@Test
	public void shouldPayThreePensForADollar() {
		Product penWith3For1Offer = new Product("Stylo en promo", BigDecimal.valueOf(0.5), threeForADollarOffer);
		Basket basket = new Basket();
		basket.addProduct(penWith3For1Offer);
		basket.addProduct(penWith3For1Offer);
		basket.addProduct(penWith3For1Offer);
		
		assertEquals(Double.valueOf(1), basket.getTotalPrice());
	}
	
	@Test
	public void shouldPayThreePensForADollarPlusOneAtUnitPrice() {
		Product penWith3For1Offer = new Product("Stylo en promo", BigDecimal.valueOf(0.5), threeForADollarOffer);
		Basket basket = new Basket();
		basket.addProduct(penWith3For1Offer);
		basket.addProduct(penWith3For1Offer);
		basket.addProduct(penWith3For1Offer);
		basket.addProduct(penWith3For1Offer);
		
		assertEquals(Double.valueOf(1.5), basket.getTotalPrice());
	}
	
}
