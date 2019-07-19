package mde.supermarketpricing.convertors;

import java.math.BigDecimal;

public class OunceToPoundConvertor implements UnitConvertor {

    @Override
    public double convert(double amountInOunce) {
        return BigDecimal.valueOf(amountInOunce).divide(BigDecimal.valueOf(16)).doubleValue();
    }
}
