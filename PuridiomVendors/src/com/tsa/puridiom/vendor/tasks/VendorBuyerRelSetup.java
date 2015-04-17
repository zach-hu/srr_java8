package com.tsa.puridiom.vendor.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.entity.VendorBuyerRel;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * Class configured Vendor with the Buyer, the Buyer obtained 
 * from the associated VendorBuyerRel to Vendor.
 * 
 * @author Alexander
 *
 */
public class VendorBuyerRelSetup extends Task {
	
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Vendor vendor = null;
		Map incomingRequest = (Map) object;
		
		try
		{
			vendor = (Vendor) incomingRequest.get("vendor");
			
			VendorBuyerRel vendorBuyerRel = (VendorBuyerRel) incomingRequest.get("vendorBuyerRel");
			if (vendorBuyerRel != null) {
				vendor.setBuyer(vendorBuyerRel.getComp_id().getUserId());
			}
			
			this.setStatus(Status.COMPLETED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return vendor;
	}
}