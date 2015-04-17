package com.tsa.puridiom.invoiceline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvoiceLineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceLine invoiceLine = (InvoiceLine) incomingRequest.get("invoiceLine");
			if (invoiceLine == null)
			{
				invoiceLine = new InvoiceLine();
			}
			if (incomingRequest.containsKey("InvoiceLine_icIvcHeader"))
			{
				String icIvcHeaderString = (String) incomingRequest.get("InvoiceLine_icIvcHeader");
				if (Utility.isEmpty(icIvcHeaderString))
				{
					icIvcHeaderString = "0";
				}
				BigDecimal icIvcHeader = new BigDecimal ( icIvcHeaderString );
				invoiceLine.setIcIvcHeader(icIvcHeader);
			}
			if (incomingRequest.containsKey("InvoiceLine_icIvcLine"))
			{
				String icIvcLineString = (String) incomingRequest.get("InvoiceLine_icIvcLine");
				if (Utility.isEmpty(icIvcLineString))
				{
					icIvcLineString = "0";
				}
				BigDecimal icIvcLine = new BigDecimal ( icIvcLineString );
				invoiceLine.setIcIvcLine(icIvcLine);
			}
			if (incomingRequest.containsKey("InvoiceLine_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("InvoiceLine_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				invoiceLine.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("InvoiceLine_icPoLine"))
			{
				String icPoLineString = (String) incomingRequest.get("InvoiceLine_icPoLine");
				if (Utility.isEmpty(icPoLineString))
				{
					icPoLineString = "0";
				}
				BigDecimal icPoLine = new BigDecimal ( icPoLineString );
				invoiceLine.setIcPoLine(icPoLine);
			}
			if (incomingRequest.containsKey("InvoiceLine_invoiceNumber"))
			{
				String invoiceNumber = (String ) incomingRequest.get("InvoiceLine_invoiceNumber");
				invoiceLine.setInvoiceNumber(invoiceNumber);
			}
			if (incomingRequest.containsKey("InvoiceLine_lineNumber"))
			{
				String lineNumberString = (String) incomingRequest.get("InvoiceLine_lineNumber");
				if (Utility.isEmpty(lineNumberString))
				{
					lineNumberString = "0";
				}
				BigDecimal lineNumber = new BigDecimal ( lineNumberString );
				invoiceLine.setLineNumber(lineNumber);
			}
			if (incomingRequest.containsKey("InvoiceLine_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvoiceLine_itemNumber");
				invoiceLine.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvoiceLine_description"))
			{
				String description = (String ) incomingRequest.get("InvoiceLine_description");
				invoiceLine.setDescription(description);
			}
			if (incomingRequest.containsKey("InvoiceLine_quantity"))
			{
				String quantityString = (String) incomingRequest.get("InvoiceLine_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				invoiceLine.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("InvoiceLine_umCode"))
			{
				String umCode = (String ) incomingRequest.get("InvoiceLine_umCode");
				invoiceLine.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("InvoiceLine_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("InvoiceLine_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "0";
				}
				BigDecimal umFactor = new BigDecimal ( umFactorString );
				invoiceLine.setUmFactor(umFactor);
			}
			if (incomingRequest.containsKey("InvoiceLine_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("InvoiceLine_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				invoiceLine.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("InvoiceLine_taxable"))
			{
				String taxable = (String ) incomingRequest.get("InvoiceLine_taxable");
				invoiceLine.setTaxable(taxable);
			}
			if (incomingRequest.containsKey("InvoiceLine_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("InvoiceLine_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				invoiceLine.setTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("InvoiceLine_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("InvoiceLine_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				invoiceLine.setTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("InvoiceLine_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("InvoiceLine_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				invoiceLine.setDiscountPercent(discountPercent);
			}
			if (incomingRequest.containsKey("InvoiceLine_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("InvoiceLine_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				invoiceLine.setDiscountAmount(discountAmount);
			}
			if (incomingRequest.containsKey("InvoiceLine_lineTotal"))
			{
				String lineTotalString = (String) incomingRequest.get("InvoiceLine_lineTotal");
				if (Utility.isEmpty(lineTotalString))
				{
					lineTotalString = "0";
				}
				BigDecimal lineTotal = new BigDecimal ( lineTotalString );
				invoiceLine.setLineTotal(lineTotal);
			}
			if (incomingRequest.containsKey("InvoiceLine_status"))
			{
				String status = (String ) incomingRequest.get("InvoiceLine_status");
				invoiceLine.setStatus(status);
			}
			if (incomingRequest.containsKey("InvoiceLine_icLineHistory"))
            {
                String icLineHistoryString = (String) incomingRequest.get("InvoiceLine_icLineHistory");
                if (Utility.isEmpty(icLineHistoryString))
                {
                    icLineHistoryString = "0";
                }
                BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
                invoiceLine.setIcLineHistory(icLineHistory);
            }
			if (incomingRequest.containsKey("InvoiceLine_shippingCharges"))
            {
                String shippingChargesString = (String) incomingRequest.get("InvoiceLine_shippingCharges");
                if (Utility.isEmpty(shippingChargesString))
                {
                    shippingChargesString = "0";
                }
                BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
                invoiceLine.setShippingCharges(shippingCharges);
            }
            if (incomingRequest.containsKey("InvoiceLine_shippingTaxable"))
            {
                String shippingTaxable = (String ) incomingRequest.get("InvoiceLine_shippingTaxable");
                invoiceLine.setShippingTaxable(shippingTaxable);
            }
            if (incomingRequest.containsKey("InvoiceLine_shippingTax"))
            {
                String shippingTaxString = (String) incomingRequest.get("InvoiceLine_shippingTax");
                if (Utility.isEmpty(shippingTaxString))
                {
                    shippingTaxString = "0";
                }
                BigDecimal shippingTax = new BigDecimal ( shippingTaxString );
                invoiceLine.setShippingTax(shippingTax);
            }
            if (incomingRequest.containsKey("InvoiceLine_otherCharges"))
            {
                String otherChargesString = (String) incomingRequest.get("InvoiceLine_otherCharges");
                if (Utility.isEmpty(otherChargesString))
                {
                    otherChargesString = "0";
                }
                BigDecimal otherCharges = new BigDecimal ( otherChargesString );
                invoiceLine.setOtherCharges(otherCharges);
            }
            if (incomingRequest.containsKey("InvoiceLine_otherDescription"))
            {
                String otherDescription = (String ) incomingRequest.get("InvoiceLine_otherDescription");
                invoiceLine.setOtherDescription(otherDescription);
            }
            if (incomingRequest.containsKey("InvoiceLine_otherTaxable"))
            {
                String otherTaxable = (String ) incomingRequest.get("InvoiceLine_otherTaxable");
                invoiceLine.setOtherTaxable(otherTaxable);
            }
            if (incomingRequest.containsKey("InvoiceLine_otherTax"))
            {
                String otherTaxString = (String) incomingRequest.get("InvoiceLine_otherTax");
                if (Utility.isEmpty(otherTaxString))
                {
                    otherTaxString = "0";
                }
                BigDecimal otherTax = new BigDecimal ( otherTaxString );
                invoiceLine.setOtherTax(otherTax);
            }
			if (incomingRequest.containsKey("InvoiceLine_commodity"))
			{
				String commodity = (String ) incomingRequest.get("InvoiceLine_commodity");
				invoiceLine.setCommodity(commodity) ;
			}
            if (incomingRequest.containsKey("InvoiceLine_asset"))
            {
                String asset = (String ) incomingRequest.get("InvoiceLine_asset");
                invoiceLine.setAsset(asset);
            }

            if (incomingRequest.containsKey("InvoiceLine_udf1Code"))
			{
				String udf1Code = (String) incomingRequest.get("InvoiceLine_udf1Code");
				invoiceLine.setUdf1Code(udf1Code);
			}

            if (incomingRequest.containsKey("InvoiceLine_icRelPoLine"))
			{
				String icRelPoLineString = (String) incomingRequest.get("InvoiceLine_icRelPoLine");

				if (HiltonUtility.isEmpty(icRelPoLineString))
				{
					icRelPoLineString = "0";
				}

				BigDecimal icRelPoLine = new BigDecimal(icRelPoLineString);
				invoiceLine.setIcRelPoLine(icRelPoLine);
			}

			result = invoiceLine;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}