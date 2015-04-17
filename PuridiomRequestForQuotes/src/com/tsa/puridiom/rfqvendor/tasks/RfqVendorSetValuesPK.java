package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class RfqVendorSetValuesPK
{
	public void setValues(Map incomingRequest, RfqVendor rfqvendor ) throws Exception
	{
		try
		{
			String icRfqHeaderString = (String) incomingRequest.get("RfqVendor_icRfqHeader");
			BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
			String vendorId = (String ) incomingRequest.get("RfqVendor_vendorId");
			RfqVendorPK comp_id = new RfqVendorPK();
			comp_id.setIcRfqHeader(icRfqHeader);
			comp_id.setVendorId(vendorId);
			rfqvendor.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}