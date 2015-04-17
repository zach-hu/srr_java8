package com.tsa.puridiom.rfqvendor.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.entity.RfqVendorPK;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class RfqVendorSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String today = Dates.today("", userTimeZone);
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			int dateResponseOffset = Integer.parseInt(propertiesManager.getProperty("RFQ DEFAULTS", "VENDORDATERESPONSEOFFSET", "90"));
			int bidValidToOffset = Integer.parseInt(propertiesManager.getProperty("RFQ DEFAULTS", "VENDORBIDVALIDTOOFFSET", "90"));

			RfqVendorPK comp_id = new RfqVendorPK();
			RfqVendor rfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");

			if (rfqVendor == null)
			{
				rfqVendor = new RfqVendor();
			}

			if (incomingRequest.containsKey("RfqVendor_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RfqVendor_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("RfqVendor_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("RfqVendor_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("RfqVendor_dateResponseRecv"))
			{
				String dateResponseRecvString = (String) incomingRequest.get("RfqVendor_dateResponseRecv");
				Date dateResponseRecv = Dates.getDate(dateResponseRecvString);
				rfqVendor.setDateResponseRecv(dateResponseRecv);
			}
			else {
				rfqVendor.setDateResponseRecv(Dates.getDate(today));
			}
			if (incomingRequest.containsKey("RfqVendor_bidResponseCode"))
			{
				String bidResponseCode = (String) incomingRequest.get("RfqVendor_bidResponseCode");
				rfqVendor.setBidResponseCode(bidResponseCode);
			}
			if (incomingRequest.containsKey("RfqVendor_contactId"))
			{
				String contactId = (String) incomingRequest.get("RfqVendor_contactId");
				rfqVendor.setContactId(contactId);
			}
			if (incomingRequest.containsKey("RfqVendor_discountSource"))
			{
				String discountSource = (String) incomingRequest.get("RfqVendor_discountSource");
				rfqVendor.setDiscountSource(discountSource);
			}
			if (incomingRequest.containsKey("RfqVendor_discountPercent"))
			{
				String discountPercentString = (String) incomingRequest.get("RfqVendor_discountPercent");
				if (Utility.isEmpty(discountPercentString))
				{
					discountPercentString = "0";
				}
				BigDecimal discountPercent = new BigDecimal ( discountPercentString );
				rfqVendor.setDiscountPercent(discountPercent);
			}
			if (incomingRequest.containsKey("RfqVendor_discountAmount"))
			{
				String discountAmountString = (String) incomingRequest.get("RfqVendor_discountAmount");
				if (Utility.isEmpty(discountAmountString))
				{
					discountAmountString = "0";
				}
				BigDecimal discountAmount = new BigDecimal ( discountAmountString );
				rfqVendor.setDiscountAmount(discountAmount);
			}
			if (incomingRequest.containsKey("RfqVendor_shippingCharges"))
			{
				String shippingChargesString = (String) incomingRequest.get("RfqVendor_shippingCharges");
				if (Utility.isEmpty(shippingChargesString))
				{
					shippingChargesString = "0";
				}
				BigDecimal shippingCharges = new BigDecimal ( shippingChargesString );
				rfqVendor.setShippingCharges(shippingCharges);
			}
			if (incomingRequest.containsKey("RfqVendor_otherCharges"))
			{
				String otherChargesString = (String) incomingRequest.get("RfqVendor_otherCharges");
				if (Utility.isEmpty(otherChargesString))
				{
					otherChargesString = "0";
				}
				BigDecimal otherCharges = new BigDecimal ( otherChargesString );
				rfqVendor.setOtherCharges(otherCharges);
			}
			if (incomingRequest.containsKey("RfqVendor_otherDescription"))
			{
				String otherDescription = (String) incomingRequest.get("RfqVendor_otherDescription");
				rfqVendor.setOtherDescription(otherDescription);
			}
			if (incomingRequest.containsKey("RfqVendor_taxShipping"))
			{
				String taxShipping = (String) incomingRequest.get("RfqVendor_taxShipping");
				rfqVendor.setTaxShipping(taxShipping);
			}
			if (incomingRequest.containsKey("RfqVendor_taxOther"))
			{
				String taxOther = (String) incomingRequest.get("RfqVendor_taxOther");
				rfqVendor.setTaxOther(taxOther);
			}
			if (incomingRequest.containsKey("RfqVendor_taxCode"))
			{
				String taxCode = (String) incomingRequest.get("RfqVendor_taxCode");
				rfqVendor.setTaxCode(taxCode);
			}
			if (incomingRequest.containsKey("RfqVendor_datePromised"))
			{
				String datePromisedString = (String) incomingRequest.get("RfqVendor_datePromised");
				Date datePromised = Dates.getDate(datePromisedString);
				rfqVendor.setDatePromised(datePromised);
			}
			else if (rfqVendor.getDatePromised() == null) {
				rfqVendor.setDatePromised(Dates.getDate(today));
			}
			if (incomingRequest.containsKey("RfqVendor_taxPercent"))
			{
				String taxPercentString = (String) incomingRequest.get("RfqVendor_taxPercent");
				if (Utility.isEmpty(taxPercentString))
				{
					taxPercentString = "0";
				}
				BigDecimal taxPercent = new BigDecimal ( taxPercentString );
				rfqVendor.setTaxPercent(taxPercent);
			}
			if (incomingRequest.containsKey("RfqVendor_taxAmount"))
			{
				String taxAmountString = (String) incomingRequest.get("RfqVendor_taxAmount");
				if (Utility.isEmpty(taxAmountString))
				{
					taxAmountString = "0";
				}
				BigDecimal taxAmount = new BigDecimal ( taxAmountString );
				rfqVendor.setTaxAmount(taxAmount);
			}
			if (incomingRequest.containsKey("RfqVendor_shippingTaxAmt"))
			{
				String shippingTaxAmtString = (String) incomingRequest.get("RfqVendor_shippingTaxAmt");
				if (Utility.isEmpty(shippingTaxAmtString))
				{
					shippingTaxAmtString = "0";
				}
				BigDecimal shippingTaxAmt = new BigDecimal ( shippingTaxAmtString );
				rfqVendor.setShippingTaxAmt(shippingTaxAmt);
			}
			if (incomingRequest.containsKey("RfqVendor_otherTaxAmount"))
			{
				String otherTaxAmountString = (String) incomingRequest.get("RfqVendor_otherTaxAmount");
				if (Utility.isEmpty(otherTaxAmountString))
				{
					otherTaxAmountString = "0";
				}
				BigDecimal otherTaxAmount = new BigDecimal ( otherTaxAmountString );
				rfqVendor.setOtherTaxAmount(otherTaxAmount);
			}
			if (incomingRequest.containsKey("RfqVendor_vendCurrency"))
			{
				String vendCurrency = (String) incomingRequest.get("RfqVendor_vendCurrency");
				rfqVendor.setVendCurrency(vendCurrency);
			}
			if (incomingRequest.containsKey("RfqVendor_fob"))
			{
				String fob = (String) incomingRequest.get("RfqVendor_fob");
				rfqVendor.setFob(fob);
			}
			if (incomingRequest.containsKey("RfqVendor_paymentTerms"))
			{
				String paymentTerms = (String) incomingRequest.get("RfqVendor_paymentTerms");
				rfqVendor.setPaymentTerms(paymentTerms);
			}
			if (incomingRequest.containsKey("RfqVendor_bidValidTo"))
			{
				String bidValidToString = (String) incomingRequest.get("RfqVendor_bidValidTo");
				Date bidValidTo = Dates.getDate(bidValidToString);
				rfqVendor.setBidValidTo(bidValidTo);
			}
			else if (rfqVendor.getBidValidTo() == null) {
				rfqVendor.setBidValidTo(Dates.getDate(today));
			}
			if (incomingRequest.containsKey("RfqVendor_addressCode"))
			{
				String addressCode = (String) incomingRequest.get("RfqVendor_addressCode");
				rfqVendor.setAddressCode(addressCode);
			}
			if (incomingRequest.containsKey("RfqVendor_ediRfq"))
			{
				String ediRfq = (String) incomingRequest.get("RfqVendor_ediRfq");
				rfqVendor.setEdiRfq(ediRfq);
			}
			if (incomingRequest.containsKey("RfqVendor_dateEdiXmit"))
			{
				String dateEdiXmitString = (String) incomingRequest.get("RfqVendor_dateEdiXmit");
				Date dateEdiXmit = Dates.getDate(dateEdiXmitString);
				rfqVendor.setDateEdiXmit(dateEdiXmit);
			}
			if (incomingRequest.containsKey("RfqVendor_ediDateResponse"))
			{
				String ediDateResponseString = (String) incomingRequest.get("RfqVendor_ediDateResponse");
				Date ediDateResponse = Dates.getDate(ediDateResponseString);
				rfqVendor.setEdiDateResponse(ediDateResponse);
			}
			if (incomingRequest.containsKey("RfqVendor_ediStatus"))
			{
				String ediStatus = (String) incomingRequest.get("RfqVendor_ediStatus");
				rfqVendor.setEdiStatus(ediStatus);
			}
			if (incomingRequest.containsKey("RfqVendor_discountTerms"))
			{
				String discountTerms = (String) incomingRequest.get("RfqVendor_discountTerms");
				rfqVendor.setDiscountTerms(discountTerms);
			}
			if (incomingRequest.containsKey("RfqVendor_notificationCode"))
			{
				String notificationCode = (String) incomingRequest.get("RfqVendor_notificationCode");
				rfqVendor.setNotificationCode(notificationCode);
			}
			if (incomingRequest.containsKey("RfqVendor_contactName"))
			{
				String contactName = (String) incomingRequest.get("RfqVendor_contactName");
				rfqVendor.setContactName(contactName);
			}
			if (incomingRequest.containsKey("RfqVendor_bidsEntered"))
			{
				String bidsEntered = (String) incomingRequest.get("RfqVendor_bidsEntered");
				rfqVendor.setBidsEntered(bidsEntered);
			}
			if (incomingRequest.containsKey("RfqVendor_vendorClass"))
			{
				String vendorClass = (String) incomingRequest.get("RfqVendor_vendorClass");
				rfqVendor.setVendorClass(vendorClass);
			}
			if (incomingRequest.containsKey("RfqVendor_currencyFactor"))
			{
				String currencyFactorString = (String) incomingRequest.get("RfqVendor_currencyFactor");
				if (Utility.isEmpty(currencyFactorString))
				{
					currencyFactorString = "1";
				}
				BigDecimal currencyFactor = new BigDecimal ( currencyFactorString );
				rfqVendor.setCurrencyFactor(currencyFactor);
			}
			if (incomingRequest.containsKey("RfqVendor_evaluationStatus"))
			{
				String evaluationStatus = (String) incomingRequest.get("RfqVendor_evaluationStatus");
				rfqVendor.setEvaluationStatus(evaluationStatus);
			}
			if (incomingRequest.containsKey("RfqVendor_totalScore"))
			{
				String totalScoreString = (String) incomingRequest.get("RfqVendor_totalScore");
				if (Utility.isEmpty(totalScoreString))
				{
					totalScoreString = "0";
				}
				BigDecimal totalScore = new BigDecimal ( totalScoreString );
				rfqVendor.setTotalScore(totalScore);
			}
			if (incomingRequest.containsKey("RfqVendor_bidCode"))
			{
				String bidCode = (String) incomingRequest.get("RfqVendor_bidCode");
				rfqVendor.setBidCode(bidCode);
			}
			rfqVendor.setComp_id(comp_id);

			result = rfqVendor;
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
