package com.tsa.puridiom.common.tax;

import java.math.BigDecimal;

public interface ICalculateTax
{
	public void calculateTax(Object entity, Object entityLine, BigDecimal bdTaxableSubtotal, boolean bAllZeroLines, BigDecimal bdExtCost, int iLine, String organizationId);

}
