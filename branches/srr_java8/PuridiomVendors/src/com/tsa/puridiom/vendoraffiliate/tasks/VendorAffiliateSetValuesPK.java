package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsa.puridiom.entity.VendorAffiliate;
import com.tsa.puridiom.entity.VendorAffiliatePK;
import java.util.Map;

public class VendorAffiliateSetValuesPK
{
	public void setValues(Map incomingRequest, VendorAffiliate vendoraffiliate ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("VendorAffiliate_vendorId");
			String affiliateId = (String ) incomingRequest.get("VendorAffiliate_affiliateId");
			VendorAffiliatePK comp_id = new VendorAffiliatePK();
			comp_id.setAffiliateId(affiliateId);
			comp_id.setVendorId(vendorId);
			vendoraffiliate.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}