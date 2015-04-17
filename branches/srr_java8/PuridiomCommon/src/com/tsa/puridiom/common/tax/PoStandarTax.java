package com.tsa.puridiom.common.tax;

import java.math.BigDecimal;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.utility.Log;


public class PoStandarTax implements ICalculateTax
{
	public void calculateTax(Object entity, Object entityLine, BigDecimal bdTaxableSubtotal, boolean bAllZeroLines, BigDecimal bdExtCost, int iLines, String organizationId)
	{
		PoHeader poHeader = (PoHeader)entity;
		PoLine line = (PoLine)entityLine;
		BigDecimal bdHdrTax = poHeader.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal bdZero = new BigDecimal(0);
		BigDecimal bdPercent = new BigDecimal("0.01");

		BigDecimal bdLineTaxable = bdExtCost.subtract(line.getDiscountAmount()); //get taxable amount of line item

		if(line.getTaxable().equals("Y"))
         {
             if (line.getTaxPercent().compareTo(bdZero)== 0 && line.getTaxOvr().equals("N") && bdTaxableSubtotal.compareTo(bdZero) != 0)//if tax amount entered at header
             {
                 if(bAllZeroLines)
                 {
                     line.setTaxAmount(bdHdrTax.divide(new BigDecimal(iLines), 2, BigDecimal.ROUND_HALF_UP));
                 }
                 else
                 {
                     bdLineTaxable = bdLineTaxable.divide(bdTaxableSubtotal, 5, BigDecimal.ROUND_HALF_UP);
                     bdLineTaxable = bdLineTaxable.multiply(bdHdrTax).setScale(2, BigDecimal.ROUND_HALF_UP);
                     line.setTaxAmount(bdLineTaxable);
                 }

             }
             else if (line.getTaxPercent().compareTo(bdZero) > 0)//if tax calculated by percent
             {
                 line.setTaxAmount(bdLineTaxable.multiply(line.getTaxPercent()).multiply(bdPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
           }
           Log.debug(this,"tax amount for line( " + line.getLineNumber() + " ) = " + line.getTaxAmount());
         }
         else
         {
             line.setTaxAmount(bdZero);
         }
	}
}