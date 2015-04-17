package com.tsa.puridiom.common.tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;


public class ReqGstTax implements ICalculateTax{

	Object object;
	String entityType;
	BigDecimal totalTax;


	public ReqGstTax()
	{

	}

	public void calculateTax(Object entity, Object entityLine, BigDecimal bdTaxableSubtotal, boolean bAllZeroLines, BigDecimal bdExtCost, int iLines, String organizationId) {

		RequisitionHeader reqHeader = (RequisitionHeader)entity;
		RequisitionLine line = (RequisitionLine)entityLine;
		BigDecimal bdHdrTax = reqHeader.getUseTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal bdZero = new BigDecimal(0);
		BigDecimal bdPercent = new BigDecimal("0.01");
		//ArrayList reqList = (ArrayList)reqHeader.getRequisitionLineList();
		BigDecimal gstPercent= new BigDecimal("0.06");

		int iTaxLine = 0;


		BigDecimal bdLineTaxable = bdExtCost.subtract(line.getDiscountAmount()); //get taxable amount of line item

		 if(true)
         {
             if (gstPercent.compareTo(bdZero)== 0 &&line.getTaxOvr().equals("N") && bdTaxableSubtotal.compareTo(bdZero) != 0)//if tax amount entered at header
             {
                 if(bAllZeroLines)
                 {
                     line.setUseTaxAmount(bdHdrTax.divide(new BigDecimal(iLines), 2, BigDecimal.ROUND_HALF_UP));
                 }
                 else
                 {
                     bdLineTaxable = bdLineTaxable.divide(bdTaxableSubtotal, 5, BigDecimal.ROUND_HALF_UP);
                     bdLineTaxable = bdLineTaxable.multiply(bdHdrTax).setScale(2, BigDecimal.ROUND_HALF_UP);
                     line.setUseTaxAmount(bdLineTaxable);
                 }
                // iTaxLine = li; //keep track of last line that was taxable in case you need to adjust later
             }
             else if (line.getTaxPercent().compareTo(bdZero) > 0)//if tax calculated by percent
             {
                 line.setUseTaxAmount(bdLineTaxable.multiply(line.getUseTaxPercent()).multiply(bdPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
             }
             //Log.debug(this,"tax amount for line( " + String.valueOf(li) + " ) = " + line.getTaxAmount());
         }
         else
         {
             line.setUseTaxAmount(bdZero);
         }

	}
}

