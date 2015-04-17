package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqBidSaveasByLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqBid */
			RfqBidPK comp_id = new RfqBidPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqBid	originalRfqBid = (RfqBid) incomingRequest.get("rfqBid");
			RfqBid	rfqBid = new RfqBid();

			comp_id.setIcRfqHeader(new BigDecimal((String) incomingRequest.get("newRfqBid_icRfqHeader")));
			comp_id.setIcRfqLine(new BigDecimal((String) incomingRequest.get("newRfqBid_icRfqLine")));
			comp_id.setVendorId(originalRfqBid.getComp_id().getVendorId());
			rfqBid.setBidCode(originalRfqBid.getBidCode());
			rfqBid.setBidCurrency(originalRfqBid.getBidCurrency());
			rfqBid.setCommentFlag(originalRfqBid.getCommentFlag());
			rfqBid.setDiscountAmount(originalRfqBid.getDiscountAmount());
			rfqBid.setDiscountPercent(originalRfqBid.getDiscountPercent());
			rfqBid.setDiscountSource(originalRfqBid.getDiscountSource());
			rfqBid.setLastChgDate(originalRfqBid.getLastChgDate());
			rfqBid.setOtherCharges(originalRfqBid.getOtherCharges());
			rfqBid.setOtherDescript(originalRfqBid.getOtherDescript());
			rfqBid.setOtherTaxAmount(originalRfqBid.getOtherTaxAmount());
			rfqBid.setQuantity(originalRfqBid.getQuantity());
			rfqBid.setShippingCharges(originalRfqBid.getShippingCharges());
			rfqBid.setShippingTaxAmt(originalRfqBid.getShippingTaxAmt());
			rfqBid.setTaxAmount(originalRfqBid.getTaxAmount());
			rfqBid.setTaxOther(originalRfqBid.getTaxOther());
			rfqBid.setTaxPercent(originalRfqBid.getTaxPercent());
			rfqBid.setTaxShipping(originalRfqBid.getTaxShipping());
			rfqBid.setUmCode(originalRfqBid.getUmCode());
			rfqBid.setUmFactor(originalRfqBid.getUmFactor());
			rfqBid.setUnitPrice(originalRfqBid.getUnitPrice());
			
			rfqBid.setComp_id(comp_id);

			incomingRequest.put("rfqBid", rfqBid);

			RfqBidAdd addTask = new RfqBidAdd();
			rfqBid = (RfqBid) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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