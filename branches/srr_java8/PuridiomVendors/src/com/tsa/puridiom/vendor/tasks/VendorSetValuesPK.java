package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class VendorSetValuesPK
{
	public void setValues(Map incomingRequest, Vendor vendor ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("Vendor_vendorId");
			vendor.setVendorId(vendorId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}