package com.tsa.puridiom.sungard.vendor.tasks;

import com.tsa.puridiom.entity.sungard.Vendor;
import com.tsa.puridiom.entity.sungard.VendorPK;

import java.math.BigDecimal;
import java.util.Map;

public class SungardVendorSetValuesPK
{
	public void setValues(Map incomingRequest, Vendor vendor ) throws Exception
	{
		try
		{
			String vendorId = (String) incomingRequest.get("SungardVendor_vendorId");
			String internalVendorIdString = (String) incomingRequest.get("SungardVendor_internalVendorId");
			BigDecimal internalVendorId = new BigDecimal ( internalVendorIdString );
			
			VendorPK comp_id = new VendorPK();
			comp_id.setVendorId(vendorId);
			comp_id.setInternalVendorId(internalVendorId);
			vendor.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}