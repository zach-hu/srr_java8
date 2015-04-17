package com.tsa.puridiom.vendorinsurance.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class VendorInsuranceSetValuesPK
{
	public void setValues(Map incomingRequest, VendorInsurance vendorinsurance ) throws Exception
	{
		try
		{
			String contNumber = (String ) incomingRequest.get("VendorInsurance_contNumber");
			vendorinsurance.setContNumber(contNumber);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}