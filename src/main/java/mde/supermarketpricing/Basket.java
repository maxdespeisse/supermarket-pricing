package mde.supermarketpricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Basket {

	private final Map<Product, Double> productsMap = new HashMap<Product, Double>();

	public Map<Product, Double> getProductsMap() {
		return productsMap;
	}

	public void addProduct(final Product product) {
		if (product == null) throw new IllegalArgumentException("product cannot be null");
		final Double amount = productsMap.get(product);
		final Double newAmount = amount != null ? amount + 1 : 1;
		productsMap.put(product, newAmount);
	}

	public Double getTotalPrice() {
		final BigDecimal totalPrice = productsMap.keySet()
				.stream()
				.reduce(BigDecimal.ZERO,
					(currentSubtotal, product) -> currentSubtotal
					.add(product.computePrice(productsMap.get(product))),
					(currentSubtotal, newSubtotal) -> currentSubtotal.add(newSubtotal));
		return totalPrice.setScale(2, RoundingMode.CEILING).doubleValue();
	}
}
