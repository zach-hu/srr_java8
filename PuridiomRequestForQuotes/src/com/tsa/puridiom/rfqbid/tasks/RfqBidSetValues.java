package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RfqBidSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqBidPK comp_id = new RfqBidPK();
			RfqBid rfqBid = (RfqBid) incomingRequest.get("rfqBid");
			if (rfqBid == null)
			{
				rfqBid = new RfqBid();
			}

			if (incomingRequest.containsKey("RfqBid_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RfqBid_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("RfqBid_icRfqLine"))
			{
				String icRfqLineString = (String) incomingRequest.get("RfqBid_icRfqLine");
				if (Utility.isEmpty(icRfqLineString))
				{
					icRfqLineString = "0";
				}
				BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
				comp_id.setIcRfqLine(icRfqLine);
			}
			if (incomingRequest.containsKey("RfqBid_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("RfqBid_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("RfqBid_quantity"))
			{
				String quantityString = (String) incomingRequest.get("RfqBid_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal ( quantityString );
				rfqBid.setQuantity(quantity);
			}
			if (incomingRequest.containsKey("RfqBid_umCode"))
			{
				String umCode = (String ) incomingRequest.get("RfqBid_umCode");
				rfqBid.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("RfqBid_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("RfqBid_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "0";
				}
				try
				{
					BigDecimal umFactor = new BigDecimal ( umFactorString );
					rfqBid.setUmFactor(umFactor);
				}
				catch (Exception e)
				{
					rfqBid.setUmFactor(new BigDecimal(0));
				}
			}
			if (incomingRequest.containsKey("RfqBid_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("RfqBid_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				if (unitPrice.compareTo(new BigDecimal(0)) > 0) {
				    incomingRequest.put("RfqBid_bidCode", "00");
				}
				rfqBid.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("RfqBid_discountSource"))
			{
				String discountSource = (String ) incomingRequest.get("RfqBid_discountSource");
				rfqBid.setDiscountSource(discountSource);
			}
			if (incomingRequest.containsKey("RfqBid_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("RfqBid_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				rfqBid.setDiscountAmount(discountAmount);
			}
			if (incomingRequest.containsKey("RfqBid_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("RfqBid_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				rfqBid.setDiscountPercent(discountPercent);
			}
			if (incomingRequest.containsKey("RfqBid_shippingCharges"))
			{
				String shippingChargesString = (String) incomingRequest.get("RfqBid_shippingCharges");
				if (Utility.isEmpty(shippingChargesString))
				{
					shippingChargesString = "0";
				}
				BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
				rfqBid.setShippingCharges(shippingCharges);
			}
			if (incomingRequest.containsKey("RfqBid_otherCharges"))
			{
				String otherChargesString = (String) incomingRequest.get("RfqBid_otherCharges");
				if (Utility.isEmpty(otherChargesString))
				{
					otherChargesString = "0";
				}
				BigDecimal otherCharges = new BigDecimal ( otherChargesString );
				rfqBid.setOtherCharges(otherCharges);
			}
			if (incomingRequest.containsKey("RfqBid_otherDescript"))
			{
				String otherDescript = (String ) incomingRequest.get("RfqBid_otherDescript");
				rfqBid.setOtherDescript(otherDescript);
			}
			if (incomingRequest.containsKey("RfqBid_taxShipping"))
			{
				String taxShipping = (String ) incomingRequest.get("RfqBid_taxShipping");
				rfqBid.setTaxShipping(taxShipping);
			}
			if (incomingRequest.containsKey("RfqBid_taxOther"))
			{
				String taxOther = (String ) incomingRequest.get("RfqBid_taxOther");
				rfqBid.setTaxOther(taxOther);
			}
			if (incomingRequest.containsKey("RfqBid_commentFlag"))
			{
				String commentFlag = (String ) incomingRequest.get("RfqBid_commentFlag");
				rfqBid.setCommentFlag(commentFlag);
			}
			if (incomingRequest.containsKey("RfqBid_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RfqBid_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				rfqBid.setTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("RfqBid_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RfqBid_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				rfqBid.setTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("RfqBid_shippingTaxAmt"))
			{
				String shippingTaxAmtString = (String) incomingRequest.get("RfqBid_shippingTaxAmt");
				if (Utility.isEmpty(shippingTaxAmtString))
				{
					shippingTaxAmtString = "0";
				}
				BigDecimal shippingTaxAmt = new BigDecimal ( shippingTaxAmtString );
				rfqBid.setShippingTaxAmt(shippingTaxAmt);
			}
			if (incomingRequest.containsKey("RfqBid_otherTaxAmount"))
			{
				String otherTaxAmountString = (String) incomingRequest.get("RfqBid_otherTaxAmount");
				if (Utility.isEmpty(otherTaxAmountString))
				{
					otherTaxAmountString = "0";
				}
				BigDecimal otherTaxAmount = new BigDecimal ( otherTaxAmountString );
				rfqBid.setOtherTaxAmount(otherTaxAmount);
			}
			if (incomingRequest.containsKey("RfqBid_bidCurrency"))
			{
				String bidCurrency = (String ) incomingRequest.get("RfqBid_bidCurrency");
				rfqBid.setBidCurrency(bidCurrency);
			}
			if (incomingRequest.containsKey("RfqBid_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("RfqBid_lastChgDate");
				Date lastChgDate = Dates.getDate(lastChgDateString);
				rfqBid.setLastChgDate(lastChgDate);
			}
			if (incomingRequest.containsKey("RfqBid_bidCode"))
			{
				String bidCode = (String ) incomingRequest.get("RfqBid_bidCode");
				rfqBid.setBidCode(bidCode);
			}
			if (incomingRequest.containsKey("RfqBid_currencyFactor"))
			{
				String currencyFactorString = (String) incomingRequest.get("RfqBid_currencyFactor");
				if (Utility.isEmpty(currencyFactorString))
				{
					currencyFactorString = "1";
				}
				BigDecimal currencyFactor = new BigDecimal ( currencyFactorString );
				rfqBid.setCurrencyFactor(currencyFactor);
			}
			rfqBid.setComp_id(comp_id);

			result = rfqBid;
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
