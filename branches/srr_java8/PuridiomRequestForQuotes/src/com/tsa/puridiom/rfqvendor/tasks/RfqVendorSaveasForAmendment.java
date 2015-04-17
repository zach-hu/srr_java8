package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqVendorSaveasForAmendment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqVendor */
		    RfqVendorPK comp_id = new RfqVendorPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqVendor	originalRfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");
			RfqVendor	rfqVendor = new RfqVendor();

			comp_id.setIcRfqHeader(new BigDecimal((String) incomingRequest.get("newRfqVendor_icRfqHeader")));
			comp_id.setVendorId(originalRfqVendor.getComp_id().getVendorId());
			rfqVendor.setComp_id(comp_id);
			
			rfqVendor.setAddressCode(originalRfqVendor.getAddressCode());
			rfqVendor.setBidResponseCode(originalRfqVendor.getBidResponseCode());
			rfqVendor.setBidValidTo(originalRfqVendor.getBidValidTo());
			rfqVendor.setBidsEntered(originalRfqVendor.getBidsEntered());
			rfqVendor.setContactId(originalRfqVendor.getContactId());
			rfqVendor.setContactName(originalRfqVendor.getContactName());
			rfqVendor.setDateEdiXmit(originalRfqVendor.getDateEdiXmit());
			rfqVendor.setDatePromised(originalRfqVendor.getDatePromised());
			rfqVendor.setDateResponseRecv(originalRfqVendor.getDateResponseRecv());
			rfqVendor.setDiscountAmount(originalRfqVendor.getDiscountAmount());
			rfqVendor.setDiscountPercent(originalRfqVendor.getDiscountPercent());
			rfqVendor.setDiscountSource(originalRfqVendor.getDiscountSource());
			rfqVendor.setDiscountTerms(originalRfqVendor.getDiscountTerms());
			rfqVendor.setEdiDateResponse(originalRfqVendor.getEdiDateResponse());
			rfqVendor.setEdiRfq(originalRfqVendor.getEdiRfq());
			rfqVendor.setEdiStatus(originalRfqVendor.getEdiStatus());
			rfqVendor.setEvaluationStatus(originalRfqVendor.getEvaluationStatus());
			rfqVendor.setFob(originalRfqVendor.getFob());
			rfqVendor.setNotificationCode(originalRfqVendor.getNotificationCode());
			rfqVendor.setOtherCharges(originalRfqVendor.getOtherCharges());
			rfqVendor.setOtherDescription(originalRfqVendor.getOtherDescription());
			rfqVendor.setOtherTaxAmount(originalRfqVendor.getTaxAmount());
			rfqVendor.setPaymentTerms(originalRfqVendor.getPaymentTerms());
			rfqVendor.setShippingCharges(originalRfqVendor.getShippingCharges());
			rfqVendor.setShippingTaxAmt(originalRfqVendor.getShippingTaxAmt());
			rfqVendor.setTaxAmount(originalRfqVendor.getTaxAmount());
			rfqVendor.setTaxCode(originalRfqVendor.getTaxCode());
			rfqVendor.setTaxOther(originalRfqVendor.getTaxOther());
			rfqVendor.setTaxPercent(originalRfqVendor.getTaxPercent());
			rfqVendor.setTaxShipping(originalRfqVendor.getTaxShipping());
			rfqVendor.setTotalScore(originalRfqVendor.getTotalScore());
			rfqVendor.setVendCurrency(originalRfqVendor.getVendCurrency());
			rfqVendor.setBidsEntered(originalRfqVendor.getBidsEntered());		
			rfqVendor.setVendorClass(originalRfqVendor.getVendorClass());
			
			incomingRequest.put("rfqVendor", rfqVendor);

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
