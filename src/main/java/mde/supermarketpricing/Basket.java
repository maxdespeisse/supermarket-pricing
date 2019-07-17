package mde.supermarketpricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Basket {

	private Map<Product, Integer> productsMap = new HashMap<Product, Integer>();

	public Map<Product, Integer> getProductsMap() {
		return productsMap;
	}

	public void addProduct(final Product product) {
		if (product == null) throw new IllegalArgumentException("product cannot be null");
		final Integer productQuantity = productsMap.get(product);
		final Integer newProductQuantity = productQuantity != null ? productQuantity + 1 : 1;
		productsMap.put(product, newProductQuantity);
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
