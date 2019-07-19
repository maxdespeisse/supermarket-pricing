package mde.supermarketpricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import mde.supermarketpricing.convertors.UnitConvertor;

public class Basket {

	private final Map<Product, Double> productsMap = new HashMap<>();

	public Map<Product, Double> getProductsMap() {
		return productsMap;
	}

	public void addProduct(final Product product) {
		addProductByAmount(product, 1);
	}

	public void addProductByAmount(final Product product, final double amountToAdd) {
		if (product == null)
			throw new IllegalArgumentException("product cannot be null");
		if (Double.compare(0, amountToAdd) >= 0)
			throw new IllegalArgumentException("amount must be greater than 0");
		final Double previousAmount = productsMap.get(product);
		final Double newAmount = previousAmount != null ? previousAmount + amountToAdd : amountToAdd;
		productsMap.put(product, newAmount);
	}

	public void addProductBySpecialUnitAmount(final Product product, final double amountToAddInSpecialUnit, final UnitConvertor convertor) {
		final Double amountToAdd = convertor.convert(amountToAddInSpecialUnit);
		addProductByAmount(product, amountToAdd);
	}

	public Double getTotalPrice() {
		final BigDecimal totalPrice = productsMap.keySet().stream()
				.reduce(BigDecimal.ZERO,
				(currentSubtotal, product) -> currentSubtotal.add(product.computePrice(productsMap.get(product))),
				(currentSubtotal, newSubtotal) -> currentSubtotal.add(newSubtotal));
		return totalPrice.setScale(2, RoundingMode.CEILING).doubleValue();
	}
}
