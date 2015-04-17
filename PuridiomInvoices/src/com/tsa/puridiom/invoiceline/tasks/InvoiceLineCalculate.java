package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.Map;

public class InvoiceLineCalculate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			PoLine aPoLine = (PoLine)incomingRequest.get("poLine");
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");

			BigDecimal ivLineQuantity = new BigDecimal((String) incomingRequest.get("InvoiceLine_quantity"));
			BigDecimal ivLineUnitPrice = new BigDecimal((String) incomingRequest.get("InvoiceLine_unitPrice"));
			BigDecimal ivLineUmFactor = new BigDecimal((String) incomingRequest.get("InvoiceLine_umFactor"));

			String s_i = (String)incomingRequest.get("i");
			String s_arraySize = (String)incomingRequest.get("arraySize");
			String s_lastIndex = (String)incomingRequest.get("lastIndex");

			BigDecimal bdZero = new BigDecimal(0);
			String sTaxable = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceLine_taxable"));
			String sExtCost = (String) incomingRequest.get("InvoiceLine_lineTotal");
			String sSubtotal = (String) incomingRequest.get("InvoiceHeader_invoiceSubtotal");
			//String sInvoiceTotal = (String) incomingRequest.get("invoiceTotal");
			String sDiscount = (String) incomingRequest.get("InvoiceHeader_invoiceDiscount");
			String sTax = (String) incomingRequest.get("InvoiceHeader_invoiceTax");
			String sUsetax = (String) incomingRequest.get("InvoiceHeader_useTax");
			String sShipping = (String) incomingRequest.get("InvoiceHeader_invoiceShipping");
			String sOther = (String) incomingRequest.get("InvoiceHeader_invoiceOther");
			String sTotalTaxable = (String) incomingRequest.get("totalTaxableAmount");

			if ( HiltonUtility.isEmpty(sExtCost) )
			{
				sExtCost = "0";
			}
			if ( HiltonUtility.isEmpty(sSubtotal) )
			{
				sSubtotal = "0";
			}
			if ( HiltonUtility.isEmpty(sDiscount) )
			{
				sDiscount = "0";
			}
			if ( HiltonUtility.isEmpty(sTax) )
			{
				sTax = "0";
			}
			if ( HiltonUtility.isEmpty(sUsetax) )
			{
				sUsetax = "0";
			}
			if ( HiltonUtility.isEmpty(sShipping) )
			{
				sShipping = "0";
			}
			if ( HiltonUtility.isEmpty(sOther) )
			{
				sOther = "0";
			}
			if ( HiltonUtility.isEmpty(sTotalTaxable) )
			{
				sTotalTaxable = "0";
			}

			BigDecimal bdExtCost = new BigDecimal ( sExtCost );
			BigDecimal bdLineTotal = new BigDecimal (sExtCost);
			BigDecimal bdSubtotal = new BigDecimal ( sSubtotal );
			BigDecimal bdDiscount = new BigDecimal ( sDiscount );
			BigDecimal bdTax = new BigDecimal ( sTax );
			BigDecimal bdUseTax = new BigDecimal ( sUsetax );
			BigDecimal bdShipping = new BigDecimal ( sShipping );
			BigDecimal bdOther = new BigDecimal ( sOther );
			BigDecimal bdTotalTaxable = new BigDecimal ( sTotalTaxable );

			if (bdSubtotal.compareTo(new BigDecimal(0)) == 0)
			{
				bdSubtotal = new BigDecimal("1");
			}

			if (bdTotalTaxable.compareTo(new BigDecimal(0)) == 0)
			{
				bdTotalTaxable = new BigDecimal("1");
			}

			BigDecimal invoice_all =
				bdDiscount
				.add(bdTax)
				.add(bdUseTax)
				.add(bdShipping)
				.add(bdOther);

			//if the invoice pay PO Total it's true -> this fails when a partial invoice
			//boolean bPayAllPo = false;
			/*if (poHeader != null)
			{
				if(invoice_all.compareTo(getAllFromPoHeader(poHeader))==0
						&& (aPoLine.getQuantity().compareTo(ivLineQuantity) == 0)
						&& (aPoLine.getUnitPrice().compareTo(ivLineUnitPrice) == 0)
						&& (aPoLine.getUmFactor().compareTo(ivLineUmFactor) == 0)){
					bPayAllPo = true;
				}
			}*/

			// only distribute amounts if extended cost is greater than 0
//			if ( bdExtCost.compareTo(bdZero) > 0)
//			{
				if ( bdDiscount.compareTo(bdZero) > 0 )
				{
					BigDecimal bdDiscountAmount = bdDiscount.multiply(  bdExtCost.divide(bdSubtotal, 8, BigDecimal.ROUND_HALF_UP) ).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal bdDiscountAlreadyApplied = (BigDecimal) incomingRequest.get("discountAlreadyApplied");
					if (bdDiscountAmount.add(bdDiscountAlreadyApplied).compareTo(bdDiscount) > 0)
					{
						//subtract a penny
						bdDiscountAmount = bdDiscountAmount.subtract(new BigDecimal(.01).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					bdDiscountAlreadyApplied = bdDiscountAlreadyApplied.add(bdDiscountAmount);
					incomingRequest.put("discountAlreadyApplied", bdDiscountAlreadyApplied);
					bdLineTotal = bdLineTotal.subtract(bdDiscountAmount);
				}

				if ( ivLineQuantity.compareTo(new BigDecimal(0)) != 0 && bdTax.compareTo(bdZero) != 0 && sTaxable.equalsIgnoreCase("Y") )
				{
					BigDecimal bdTaxAmount = bdTax.multiply( bdExtCost.divide(bdTotalTaxable, 8, BigDecimal.ROUND_HALF_UP) ).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal bdTaxAlreadyApplied = (BigDecimal) incomingRequest.get("taxAlreadyApplied");
					if (bdTaxAmount.abs().add(bdTaxAlreadyApplied.abs()).compareTo(bdTax.abs()) > 0)
					{
						bdTaxAmount = bdTaxAmount.subtract(new BigDecimal(.01).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if (!HiltonUtility.isEmpty(s_i) && !HiltonUtility.isEmpty(s_arraySize) && !HiltonUtility.isEmpty(s_lastIndex))
					{
						int iI = Integer.parseInt(s_i);
						int iArraySize = Integer.parseInt(s_arraySize);
						int iLastIndex = Integer.parseInt(s_lastIndex);
						if (iI == (iArraySize - 1) || iI == iLastIndex)
							bdTaxAmount = bdTax.subtract(bdTaxAlreadyApplied);
					}
					if (invoiceHeader != null && invoiceHeader.getCurrencyCode().equalsIgnoreCase("JPY"))
						bdTaxAmount = bdTaxAmount.setScale(0, BigDecimal.ROUND_HALF_UP);
					bdTaxAlreadyApplied = bdTaxAlreadyApplied.add(bdTaxAmount);
					incomingRequest.put("taxAlreadyApplied", bdTaxAlreadyApplied);
					incomingRequest.put("InvoiceLine_taxAmount", bdTaxAmount.toString());
					bdLineTotal = bdLineTotal.add(bdTaxAmount);
				}

				if ( ivLineQuantity.compareTo(new BigDecimal(0)) != 0 && bdUseTax.compareTo(bdZero) != 0 && sTaxable.equalsIgnoreCase("Y") )
				{
					BigDecimal bdUseTaxAmount = bdUseTax.multiply( bdExtCost.divide(bdTotalTaxable, 8, BigDecimal.ROUND_HALF_UP) ).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal bdUseTaxAlreadyApplied = (BigDecimal) incomingRequest.get("useTaxAlreadyApplied");
					if (bdUseTaxAmount.abs().add(bdUseTaxAlreadyApplied.abs()).compareTo(bdUseTax.abs()) > 0)
					{
						bdUseTaxAmount = bdUseTaxAmount.subtract(new BigDecimal(.01).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if (!HiltonUtility.isEmpty(s_i) && !HiltonUtility.isEmpty(s_arraySize) && !HiltonUtility.isEmpty(s_lastIndex))
					{
						int iI = Integer.parseInt(s_i);
						int iArraySize = Integer.parseInt(s_arraySize);
						int iLastIndex = Integer.parseInt(s_lastIndex);
						if (iI == (iArraySize - 1) || iI == iLastIndex)
							bdUseTaxAmount = bdUseTax.subtract(bdUseTaxAlreadyApplied);
					}
					if (invoiceHeader != null && invoiceHeader.getCurrencyCode().equalsIgnoreCase("JPY"))
						bdUseTaxAmount = bdUseTaxAmount.setScale(0, BigDecimal.ROUND_HALF_UP);
					bdUseTaxAlreadyApplied = bdUseTaxAlreadyApplied.add(bdUseTaxAmount);
					incomingRequest.put("useTaxAlreadyApplied", bdUseTaxAlreadyApplied);
					incomingRequest.put("InvoiceLine_useTaxAmount", bdUseTaxAmount.toString());
					bdLineTotal = bdLineTotal.add(bdUseTaxAmount);
				}

				if ( ivLineQuantity.compareTo(new BigDecimal(0)) != 0 && bdShipping.compareTo(bdZero) != 0 )
				{
					BigDecimal bdShippingCharges = bdShipping.multiply( bdExtCost.divide(bdSubtotal, 8, BigDecimal.ROUND_HALF_UP) ).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal bdShippingAlreadyApplied = (BigDecimal) incomingRequest.get("shippingAlreadyApplied");
					if (bdShippingCharges.abs().add(bdShippingAlreadyApplied.abs()).compareTo(bdShipping.abs()) > 0)
					{
						bdShippingCharges = bdShippingCharges.subtract(new BigDecimal(.01).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if (!HiltonUtility.isEmpty(s_i) && !HiltonUtility.isEmpty(s_arraySize) && !HiltonUtility.isEmpty(s_lastIndex))
					{
						int iI = Integer.parseInt(s_i);
						int iArraySize = Integer.parseInt(s_arraySize);
						int iLastIndex = Integer.parseInt(s_lastIndex);
						if (iI == (iArraySize - 1) || iI == iLastIndex)
							bdShippingCharges = bdShipping.subtract(bdShippingAlreadyApplied);
					}
					if (invoiceHeader != null && invoiceHeader.getCurrencyCode().equalsIgnoreCase("JPY"))
						bdShippingCharges = bdShippingCharges.setScale(0, BigDecimal.ROUND_HALF_UP);
					bdShippingAlreadyApplied = bdShippingAlreadyApplied.add(bdShippingCharges);
					incomingRequest.put("shippingAlreadyApplied", bdShippingAlreadyApplied);
					incomingRequest.put("InvoiceLine_shippingCharges", bdShippingCharges.toString());
					bdLineTotal = bdLineTotal.add(bdShippingCharges);
				}

				if ( ivLineQuantity.compareTo(new BigDecimal(0)) != 0 && bdOther.compareTo(bdZero) != 0 )
				{
					BigDecimal bdOtherCharges = bdOther.multiply( bdExtCost.divide(bdSubtotal, 8, BigDecimal.ROUND_HALF_UP) ).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal bdOtherAlreadyApplied = (BigDecimal) incomingRequest.get("otherAlreadyApplied");
					if (bdOtherCharges.abs().add(bdOtherAlreadyApplied.abs()).compareTo(bdOther.abs()) > 0)
					{
						bdOtherCharges = bdOtherCharges.subtract(new BigDecimal(.01).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if (!HiltonUtility.isEmpty(s_i) && !HiltonUtility.isEmpty(s_arraySize) && !HiltonUtility.isEmpty(s_lastIndex))
					{
						int iI = Integer.parseInt(s_i);
						int iArraySize = Integer.parseInt(s_arraySize);
						int iLastIndex = Integer.parseInt(s_lastIndex);
						if (iI == (iArraySize - 1) || iI == iLastIndex)
							bdOtherCharges = bdOther.subtract(bdOtherAlreadyApplied);
					}
					if (invoiceHeader != null && invoiceHeader.getCurrencyCode().equalsIgnoreCase("JPY"))
						bdOtherCharges = bdOtherCharges.setScale(0, BigDecimal.ROUND_HALF_UP);
					bdOtherAlreadyApplied = bdOtherAlreadyApplied.add(bdOtherCharges);
					incomingRequest.put("otherAlreadyApplied", bdOtherAlreadyApplied);
					incomingRequest.put("InvoiceLine_otherCharges", bdOtherCharges.toString());
					bdLineTotal = bdLineTotal.add(bdOtherCharges);
				}

				
				incomingRequest.put("InvoiceLine_lineTotal", bdLineTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				
				/*BigDecimal lineTotal = bdLineTotal.setScale(3, BigDecimal.ROUND_HALF_UP);

				if(bPayAllPo){
					BigDecimal poLineTotal = aPoLine.getLineTotal().setScale(3, BigDecimal.ROUND_HALF_UP);
					incomingRequest.put("InvoiceLine_lineTotal", poLineTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}else {
					incomingRequest.put("InvoiceLine_lineTotal", lineTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}*/

				/*if(poLineTotal.compareTo(lineTotal)==0 ){
					incomingRequest.put("InvoiceLine_lineTotal", lineTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}else{
					BigDecimal tmpBD = poLineTotal.subtract(lineTotal);
					if(tmpBD.abs().doubleValue()<= 0.01d){
						incomingRequest.put("InvoiceLine_lineTotal", poLineTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}else{
						incomingRequest.put("InvoiceLine_lineTotal", lineTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
				}*/

//			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	public BigDecimal getAllFromPoHeader(PoHeader pPoHeader){
		return pPoHeader.getDiscountAmount()
		.add(pPoHeader.getTaxAmount())
		.add(pPoHeader.getUseTaxAmount())
		.add(pPoHeader.getShippingTax())
		.add(pPoHeader.getShippingCharges())
		.add(pPoHeader.getOtherCharges())
		.add(pPoHeader.getOtherTax());
	}

}
