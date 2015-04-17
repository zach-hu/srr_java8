package com.tsa.puridiom.poline.tasks;
import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoLineRecalculate extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		this.setStatus(Status.SUCCEEDED) ;
		
		try 
		{	
			PoLine pol = (PoLine) incomingRequest.get("poLine");
			
			/* Calculate Line Totals */
			
			String quantity = (String) incomingRequest.get("PoLine_quantity");
			if (quantity == null){	quantity = Utility.tsaToString(pol.getQuantity());	}
			BigDecimal bdQuantity = new BigDecimal(quantity);
			
			String unitPrice = (String) incomingRequest.get("PoLine_unitPrice");
			if (unitPrice == null) {		unitPrice = Utility.tsaToString(pol.getUnitPrice());	}
			BigDecimal bdUnitPrice = new BigDecimal(unitPrice);
			
			String umFactor = (String) incomingRequest.get("PoLine_umFactor");
			if (umFactor == null){		umFactor = Utility.tsaToString(pol.getUmFactor());	}
			BigDecimal bdUmFactor = new BigDecimal(umFactor);
			
			BigDecimal bdLineTotal = bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor);
			bdLineTotal = bdLineTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			/* Calculate Line Discount */
			String discountPercent = (String) incomingRequest.get("PoLine_discountPercent");
			if (discountPercent == null){		discountPercent = Utility.tsaToString(pol.getDiscountPercent());		}
			BigDecimal bdDiscountPercent = new BigDecimal(discountPercent);
			
			String discountAmount = (String) incomingRequest.get("PoLine_discountAmount");
			if (discountAmount == null){		discountAmount = Utility.tsaToString(pol.getDiscountAmount());		}
			BigDecimal bdDiscountAmount = new BigDecimal(discountAmount);
			
			
			bdDiscountAmount = bdDiscountPercent.multiply(new BigDecimal(0.01));
			bdDiscountAmount = bdDiscountAmount.multiply(bdLineTotal);
			bdDiscountAmount = bdDiscountAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			String taxable = (String) incomingRequest.get("PoLine_taxable");
			if (taxable == null)
			{		
				taxable = pol.getTaxable();					
				if(taxable == null) 
				{
					taxable = "Y";
				}
			}
			
			/* Calculate Tax */
			String taxPercent = (String) incomingRequest.get("PoLine_taxPercent");
			if (taxPercent == null){	taxPercent = Utility.tsaToString(pol.getTaxPercent());	}
			String taxAmount = (String) incomingRequest.get("PoLine_discountPercent");
			if (taxAmount == null){		taxAmount = Utility.tsaToString(pol.getTaxAmount());	}
			
			BigDecimal bdTaxPercent = new BigDecimal(taxPercent);
			BigDecimal bdTaxAmount = new BigDecimal(taxAmount);
			
			if (taxable.equals("Y")) 
			{
				bdTaxAmount = bdTaxPercent.multiply(new BigDecimal(0.01));
				bdTaxAmount = bdTaxAmount.multiply(bdLineTotal.subtract(bdDiscountAmount));
				bdTaxAmount = bdTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			else 
			{
				bdTaxAmount = new BigDecimal(0);
			}
			
			/* Calculate shipping charges */
			String shippingCharges = (String) incomingRequest.get("PoLine_shippingCharges");
			if (shippingCharges == null) 
			{
				shippingCharges = pol.getShippingCharges().toString();
			}
			BigDecimal bdShippingCharges = new BigDecimal(shippingCharges);
			
			String taxShipping = (String) incomingRequest.get("PoLine_taxShipping");
			if (taxShipping == null) 
			{
				taxShipping = pol.getShippingTaxable();
				if (taxShipping == null){	taxShipping = "N";	}
			}
			
			BigDecimal bdShippingTaxAmt = new BigDecimal(0);
			if (taxShipping.equals("Y")) 
			{
				bdShippingTaxAmt = bdTaxPercent.multiply(new BigDecimal(0.01));
				bdShippingTaxAmt = bdShippingTaxAmt.multiply(bdShippingCharges);
				bdShippingTaxAmt = bdShippingTaxAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			
			String otherCharges = (String) incomingRequest.get("PoLine_otherCharges");
			if (otherCharges == null){		otherCharges = Utility.tsaToString(pol.getOtherCharges());		}
			BigDecimal bdOtherCharges = new BigDecimal(otherCharges);
			
			String taxOther = (String) incomingRequest.get("PoLine_taxOther");
			if (taxOther == null) 
			{
				taxOther = pol.getOtherTaxable();
				if (taxOther == null){	taxOther = "N";	}
			}
			
			BigDecimal bdOtherTaxAmt = new BigDecimal(0);
			if (taxOther.equals("Y")) 
			{
				bdOtherTaxAmt = bdTaxPercent.multiply(new BigDecimal(0.01));
				bdOtherTaxAmt = bdOtherTaxAmt.multiply(bdOtherCharges);
				bdOtherTaxAmt =bdOtherTaxAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			
			BigDecimal bdAllocateTotal = bdLineTotal.subtract(bdDiscountAmount);
			bdAllocateTotal = bdAllocateTotal.add(bdTaxAmount);
			bdAllocateTotal = bdAllocateTotal.add(bdShippingCharges);
			bdAllocateTotal = bdAllocateTotal.add(bdShippingTaxAmt);
			bdAllocateTotal = bdAllocateTotal.add(bdOtherCharges);
			bdAllocateTotal = bdAllocateTotal.add(bdOtherTaxAmt);
			
			incomingRequest.put("PoLine_lineTotal",	bdLineTotal.toString());
			incomingRequest.put("PoLine_discountAmount",	bdDiscountAmount.toString());
			incomingRequest.put("PoLine_taxAmount", bdTaxAmount.toString());
			incomingRequest.put("PoLine_shippingCharges", bdShippingCharges.toString());
			incomingRequest.put("PoLine_shippingTax", bdShippingTaxAmt.toString());
			incomingRequest.put("PoLine_otherCharges", bdOtherCharges.toString());
			incomingRequest.put("PoLine_otherTax", bdOtherTaxAmt.toString());
			
			incomingRequest.put("allocateTotal", bdAllocateTotal.toString());
			incomingRequest.put("recalculate", "N");
			
			if (bdLineTotal.compareTo(pol.getLineTotal()) != 0
				|| bdTaxAmount.compareTo(pol.getTaxAmount()) != 0
				|| bdDiscountAmount.compareTo(pol.getDiscountAmount()) != 0
				|| bdShippingCharges.compareTo(pol.getShippingCharges()) != 0
				|| bdShippingTaxAmt.compareTo(pol.getShippingTax()) != 0
				|| bdOtherCharges.compareTo(pol.getOtherCharges()) != 0
				|| bdOtherTaxAmt.compareTo(pol.getOtherTax()) != 0) 
			{
				incomingRequest.put("recalculate", "Y");
			}
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}