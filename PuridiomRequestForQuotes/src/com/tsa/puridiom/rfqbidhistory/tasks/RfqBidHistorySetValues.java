package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RfqBidHistorySetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			RfqBidHistory rfqBidHistory = (RfqBidHistory) incomingRequest.get("rfqBidHistory");
			RfqBidHistoryPK comp_id = null;

			if (rfqBidHistory == null) {
				comp_id = new RfqBidHistoryPK();
				rfqBidHistory = new RfqBidHistory();
				rfqBidHistory.setComp_id(comp_id);
			}

			rfqBidHistory.getComp_id();

			if (incomingRequest.containsKey("RfqBidHistory_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RfqBidHistory_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}

			if (incomingRequest.containsKey("RfqBidHistory_icRfqLine"))
			{
				String icRfqLineString = (String) incomingRequest.get("RfqBidHistory_icRfqLine");
				if (Utility.isEmpty(icRfqLineString))
				{
					icRfqLineString = "0";
				}
				BigDecimal icRfqLine = new BigDecimal ( icRfqLineString );
				comp_id.setIcRfqLine(icRfqLine);
			}

			if (incomingRequest.containsKey("RfqBidHistory_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("RfqBidHistory_vendorId");
				comp_id.setVendorId(vendorId);
			}

			if (incomingRequest.containsKey("RfqBidHistory_bidSequence"))
			{
				String sequenceNo = (String) incomingRequest.get("RfqBidHistory_bidSequence");
				if (Utility.isEmpty(sequenceNo))
				{
					sequenceNo = "0";
				}
				BigDecimal bidHistory_sequenceNo = new BigDecimal (sequenceNo );
				comp_id.setBidSequence(bidHistory_sequenceNo);
			}

			if (incomingRequest.containsKey("RfqBidHistory_quantity"))
			{
				String quantityString = (String) incomingRequest.get("RfqBidHistory_quantity");
				if (Utility.isEmpty(quantityString))
				{
					quantityString = "0";
				}
				BigDecimal quantity = new BigDecimal(quantityString);
				rfqBidHistory.setQuantity(quantity);
			}

			if (incomingRequest.containsKey("RfqBidHistory_umCode"))
			{
				String umCode = (String ) incomingRequest.get("RfqBidHistory_umCode");
				rfqBidHistory.setUmCode(umCode);
			}

			if (incomingRequest.containsKey("RfqBidHistory_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("RfqBidHistory_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				rfqBidHistory.setUnitPrice(unitPrice);
			}

			if (incomingRequest.containsKey("RfqBidHistory_discountSource"))
			{
				String discountSource = (String ) incomingRequest.get("RfqBidHistory_discountSource");
				rfqBidHistory.setDiscountSource(discountSource);
			}

			if (incomingRequest.containsKey("RfqBidHistory_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("RfqBidHistory_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				rfqBidHistory.setDiscountAmount(discountAmount);
			}

			if (incomingRequest.containsKey("RfqBidHistory_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("RfqBidHistory_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				rfqBidHistory.setDiscountPercent(discountPercent);
			}

			if (incomingRequest.containsKey("RfqBidHistory_shippingChargest"))
			{
				String shippingChargesString = (String) incomingRequest.get("RfqBidHistory_shippingCharges");
				if (Utility.isEmpty(shippingChargesString))
				{
					shippingChargesString = "0";
				}
				BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
				rfqBidHistory.setShippingCharges(shippingCharges);
			}

			if (incomingRequest.containsKey("RfqBidHistory_otherChargest"))
			{
				String otherChargesString = (String) incomingRequest.get("RfqBidHistory_otherCharges");
				if (Utility.isEmpty(otherChargesString))
				{
					otherChargesString = "0";
				}
				BigDecimal otherCharges = new BigDecimal(otherChargesString);
				rfqBidHistory.setOtherCharges(otherCharges);
			}

			if (incomingRequest.containsKey("RfqBidHistory_otherDescript"))
			{
				String otherDescript = (String) incomingRequest.get("RfqBidHistory_otherDescript");
				rfqBidHistory.setOtherDescript(otherDescript);
			}

			if (incomingRequest.containsKey("RfqBidHistory_taxShipping"))
			{
				String taxShipping = (String) incomingRequest.get("RfqBidHistory_taxShipping");
				rfqBidHistory.setTaxShipping(taxShipping);
			}

			if (incomingRequest.containsKey("RfqBidHistory_taxOther"))
			{
				String taxOther = (String) incomingRequest.get("RfqBidHistory_taxOther");
				rfqBidHistory.setTaxOther(taxOther);
			}

			if (incomingRequest.containsKey("RfqBidHistory_commentFlag"))
			{
				String commentFlag = (String) incomingRequest.get("RfqBidHistory_commentFlag");
				rfqBidHistory.setCommentFlag(commentFlag);
			}

			if (incomingRequest.containsKey("RfqBidHistory_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RfqBidHistory_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal(taxPercentString);
				rfqBidHistory.setTaxPercent(taxPercent);
			}

			if (incomingRequest.containsKey("RfqBidHistory_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RfqBidHistory_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal(taxAmountString);
				rfqBidHistory.setTaxAmount(taxAmount);
			}

			if (incomingRequest.containsKey("RfqBidHistory_shippingTaxAmt"))
			{
				String shippingTaxAmtString = (String) incomingRequest.get("RfqBidHistory_shippingTaxAmt");
				if (Utility.isEmpty(shippingTaxAmtString))
				{
					shippingTaxAmtString = "0";
				}
				BigDecimal shippingTaxAmt = new BigDecimal(shippingTaxAmtString);
				rfqBidHistory.setShippingTaxAmt(shippingTaxAmt);
			}

			if (incomingRequest.containsKey("RfqBidHistory_otherTaxAmount"))
			{
				String otherTaxAmountString = (String) incomingRequest.get("RfqBidHistory_otherTaxAmount");
				if (Utility.isEmpty(otherTaxAmountString))
				{
					otherTaxAmountString = "0";
				}
				BigDecimal otherTaxAmount = new BigDecimal(otherTaxAmountString);
				rfqBidHistory.setOtherTaxAmount(otherTaxAmount);
			}

			if (incomingRequest.containsKey("RfqBidHistory_bidCurrency"))
			{
				String bidCurrency = (String) incomingRequest.get("RfqBidHistory_bidCurrency");
				rfqBidHistory.setBidCurrency(bidCurrency);
			}

			if (incomingRequest.containsKey("RfqBidHistory_lastChgDate"))
			{
				String lastChgDateString = (String) incomingRequest.get("RfqBidHistory_lastChgDate");
				Date lastChgDate = Dates.getDate(lastChgDateString);
				rfqBidHistory.setLastChgDate(lastChgDate);
			}

			if (incomingRequest.containsKey("RfqBidHistory_lastMdfDate"))
			{
				String lastMdfDateString = (String) incomingRequest.get("RfqBidHistory_lastMdfDate");
				Date lastMdfDate = Dates.getDateTimeHours(lastMdfDateString);
				rfqBidHistory.setLastMdfDate(lastMdfDate);
			}

			if (incomingRequest.containsKey("RfqBidHistory_bidCode"))
			{
				String bidCode = (String ) incomingRequest.get("RfqBidHistory_bidCode");
				if (bidCode.compareTo(new BigDecimal(0).toString()) > 0) {
				    incomingRequest.put("RfqBidHistory_bidCode", "00");
				}
				rfqBidHistory.setBidCode(bidCode);
			}

			if (incomingRequest.containsKey("RfqBidHistory_umFactor"))
			{
				String umFactorString = (String) incomingRequest.get("RfqBidHistory_umFactor");
				if (Utility.isEmpty(umFactorString))
				{
					umFactorString = "0";
				}
				BigDecimal umFactor = new BigDecimal ( umFactorString );
				rfqBidHistory.setUnitPrice(umFactor);
			}

			if (incomingRequest.containsKey("RfqBidHistory_userId"))
			{
				String userId = (String) incomingRequest.get("RfqBidHistory_userId");
				rfqBidHistory.setUserId(userId);
			}
			if (incomingRequest.containsKey("RfqBidHistory_timeZone"))
			{
				String timeZone = (String) incomingRequest.get("RfqBidHistory_timeZone");
				rfqBidHistory.setTimeZone(timeZone);
			}
			rfqBidHistory.setComp_id(comp_id);
			result = rfqBidHistory;
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
