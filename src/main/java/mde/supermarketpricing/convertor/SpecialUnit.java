package mde.supermarketpricing.convertor;

import java.math.BigDecimal;

public enum SpecialUnit implements UnitConvertor {
	OUNCE(amountInOunce -> BigDecimal.valueOf(amountInOunce).divide(BigDecimal.valueOf(16)).doubleValue());

	private UnitConvertor unitConvertor;

	SpecialUnit(final UnitConvertor unitConvertor) {
		this.unitConvertor = unitConvertor;
	}
	
	@Override
	public double convert(double amount) {
		return unitConvertor.convert(amount);
	}

}
