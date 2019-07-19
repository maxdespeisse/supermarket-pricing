package mde.supermarketpricing;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import mde.supermarketpricing.convertors.OunceToPoundConvertor;
import org.junit.Test;

import mde.supermarketpricing.pricingmethods.BasicPricingMethod;
import mde.supermarketpricing.pricingmethods.OneProductOfferedPricingMethod;
import mde.supermarketpricing.pricingmethods.PackagedOfferPricingMethod;
import mde.supermarketpricing.pricingmethods.PricingMethod;

public class BasketTest {
	
	final PricingMethod basicPricingMethod = new BasicPricingMethod();
	final PricingMethod threeForADollarOffer = new PackagedOfferPricingMethod(3, BigDecimal.valueOf(1));
	final PricingMethod oneFreeForThreeBoughtOffer = new OneProductOfferedPricingMethod(3);
	
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
	
	@Test
	public void shouldPurchase4PensAndPayOnly3() {
		Product penWithOneOfferedFor3Bought = new Product("Stylo en promo", BigDecimal.valueOf(0.5), oneFreeForThreeBoughtOffer);
		Basket basket = new Basket();
		basket.addProduct(penWithOneOfferedFor3Bought);
		basket.addProduct(penWithOneOfferedFor3Bought);
		basket.addProduct(penWithOneOfferedFor3Bought);
		basket.addProduct(penWithOneOfferedFor3Bought);
		
		assertEquals(Double.valueOf(1.5), basket.getTotalPrice());
	}

	@Test
	public void shouldPurchase3PoundsAndAHalfAndPayUnitPrice() {
		Product tomatoes = new Product("Tomatoes", BigDecimal.valueOf(4), basicPricingMethod);
		Basket basket = new Basket();
		basket.addProductByAmount(tomatoes, 3.5);

		assertEquals(Double.valueOf(14), basket.getTotalPrice());
	}

	@Test
	public void shouldPurchase4OuncesAndPayUnitPrice() {
		Product tomatoes = new Product("Tomatoes", BigDecimal.valueOf(2), basicPricingMethod);
		Basket basket = new Basket();
		basket.addProductBySpecialUnitAmount(tomatoes, 4, new OunceToPoundConvertor());

		assertEquals(Double.valueOf(0.5), basket.getTotalPrice());
	}

	@Test
	public void shouldPurchase4OuncesAndRoundPriceUp() {
		Product tomatoes = new Product("Tomatoes", BigDecimal.valueOf(1.99), basicPricingMethod);
		Basket basket = new Basket();
		basket.addProductBySpecialUnitAmount(tomatoes, 4, new OunceToPoundConvertor());

		assertEquals(Double.valueOf(0.5), basket.getTotalPrice());
	}

	@Test
	public void shouldPurchaseTwoBagsOfTomatoes() {
		Product tomatoes = new Product("Tomatoes", BigDecimal.valueOf(1.99), basicPricingMethod);
		Basket basket = new Basket();
		basket.addProductBySpecialUnitAmount(tomatoes, 4, new OunceToPoundConvertor());

		assertEquals(Double.valueOf(0.5), basket.getTotalPrice());
	}

	@Test
	public void shouldPurchaseMultipleTypeOfProducts() {
		Product canOfBeans = new Product("Beans", BigDecimal.valueOf(0.65), new BasicPricingMethod());
		Product tomato = new Product("Tomato", BigDecimal.valueOf(1.99), new BasicPricingMethod());
		Product kiwiWith4For5DollarOffer = new Product("Kiwi", BigDecimal.valueOf(1.5), new PackagedOfferPricingMethod(4, BigDecimal.valueOf(5)));
		Product shampooWithOneFreeFor2Bought = new Product("Shampoo", BigDecimal.valueOf(4), new OneProductOfferedPricingMethod(2));

		Basket basket = new Basket();
		basket.addProduct(canOfBeans);
		basket.addProductByAmount(tomato, 3);
		basket.addProductByAmount(kiwiWith4For5DollarOffer, 5);
		basket.addProductByAmount(shampooWithOneFreeFor2Bought, 3);

		assertEquals(Double.valueOf(21.12), basket.getTotalPrice());
	}

	@Test
	public void shouldComputePriceOfTwoSmallBagsAsOneBagLarge() {
		Product tomato = new Product("Tomato", BigDecimal.valueOf(1.99), new BasicPricingMethod());

		Basket basketWithTwoBags = new Basket();
		basketWithTwoBags.addProductByAmount(tomato, 1.6);
		basketWithTwoBags.addProductByAmount(tomato, 2.4);

		Basket basketWithOneLargeBag = new Basket();
		basketWithOneLargeBag.addProductByAmount(tomato, 4);

		assertEquals(basketWithTwoBags.getTotalPrice(), basketWithOneLargeBag.getTotalPrice());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAmountIsNegative() {
		Product tomato = new Product("Tomato", BigDecimal.valueOf(1.99), new BasicPricingMethod());
		
		Basket basketWithTwoBags = new Basket();
		basketWithTwoBags.addProductByAmount(tomato, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAmountIsZero() {
		Product tomato = new Product("Tomato", BigDecimal.valueOf(1.99), new BasicPricingMethod());
		
		Basket basketWithTwoBags = new Basket();
		basketWithTwoBags.addProductByAmount(tomato, 0);
	}
	
}
