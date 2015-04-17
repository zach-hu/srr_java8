package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsa.puridiom.entity.VendorAffiliate;
import com.tsa.puridiom.entity.VendorAffiliatePK;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorAffiliateSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			VendorAffiliatePK comp_id = new VendorAffiliatePK();
			VendorAffiliate vendorAffiliate = (VendorAffiliate) incomingRequest.get("vendorAffiliate");
			if (vendorAffiliate == null)
			{
				vendorAffiliate = new VendorAffiliate();
			}

			if (incomingRequest.containsKey("VendorAffiliate_vendorId"))
			{
				String vendorId = (String) incomingRequest.get("VendorAffiliate_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("VendorAffiliate_affiliateId"))
			{
				String affiliateId = (String) incomingRequest.get("VendorAffiliate_affiliateId");
				comp_id.setAffiliateId(affiliateId);
			}
			vendorAffiliate.setComp_id(comp_id);

			result = vendorAffiliate;
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