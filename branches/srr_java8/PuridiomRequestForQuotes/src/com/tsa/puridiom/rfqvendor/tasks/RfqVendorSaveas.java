package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class RfqVendorSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	organizationId = (String) incomingRequest.get("organizationId");
			/* Expects incoming request to contain rfqVendor */
			RfqVendorPK comp_id = new RfqVendorPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqVendor	originalRfqVendor = (RfqVendor) incomingRequest.get("rfqVendor");
			RfqVendor	rfqVendor = new RfqVendor();

			comp_id.setIcRfqHeader(new BigDecimal((String) incomingRequest.get("newRfqVendor_icRfqHeader")));
			comp_id.setVendorId(originalRfqVendor.getComp_id().getVendorId());
			rfqVendor.setComp_id(comp_id);
			
			rfqVendor.setAddressCode(originalRfqVendor.getAddressCode());
			rfqVendor.setBidResponseCode(PropertiesManager.getInstance(organizationId).getProperty("RFQ DEFAULTS", "ResponseCode", ""));
			rfqVendor.setContactId(originalRfqVendor.getContactId());
			rfqVendor.setContactName(originalRfqVendor.getContactName());
			rfqVendor.setEdiRfq(originalRfqVendor.getEdiRfq());
			rfqVendor.setEdiStatus(originalRfqVendor.getEdiStatus());
			rfqVendor.setFob(originalRfqVendor.getFob());
			rfqVendor.setNotificationCode(originalRfqVendor.getNotificationCode());
			rfqVendor.setPaymentTerms(originalRfqVendor.getPaymentTerms());
			rfqVendor.setVendCurrency(originalRfqVendor.getVendCurrency());
			rfqVendor.setBidsEntered("N");		
			rfqVendor.setVendorClass(originalRfqVendor.getVendorClass());
			rfqVendor.setComp_id(comp_id);

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
