package com.tsa.puridiom.common.tax;

import java.math.BigDecimal;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;


public class PoUseTax implements ICalculateTax
{
	public int iTaxLine = 0;

	public void calculateTax(Object entity, Object entityLine, BigDecimal bdTaxableSubtotal, boolean bAllZeroLines, BigDecimal bdExtCost, int iLines, String organizationId)
	{
		PoLine line = (PoLine)entityLine;
		BigDecimal bdPercent = new BigDecimal("0.01");
		//ArrayList reqList = (ArrayList)reqHeader.getRequisitionLineList();

		BigDecimal bdLineTaxable = bdExtCost.subtract(line.getDiscountAmount()); //get taxable amount of line item
		PoHeader header =(PoHeader)entity;
		if(HiltonUtility.isQriCanadian(organizationId, header.getUdf1Code()) && line.getTaxable().equals("Y"))
		{
			line.setUseTaxPercent(new BigDecimal(PropertiesManager.getInstance(organizationId).getProperty("PO DEFAULTS","USETAXPERCENTCANADIAN","0")));
			line.setUseTaxable("Y");
		}
		else
		{
			line.setUseTaxPercent(new BigDecimal(0));
			line.setUseTaxable("N");
		}

	    // tax calculated by percent
        line.setUseTaxAmount(bdLineTaxable.multiply(line.getUseTaxPercent()).multiply(bdPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
        Log.debug(this,"tax amount for line( " + line.getLineNumber() + " ) = " + line.getTaxAmount());
	}
}