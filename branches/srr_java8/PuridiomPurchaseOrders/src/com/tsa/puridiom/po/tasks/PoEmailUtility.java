package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.tasks.SupplierEmailUtility;

public class PoEmailUtility extends SupplierEmailUtility
{

	public String getVendorId(Map incomingRequest)
	{
		return (String)incomingRequest.get("Pdf_PoHeader_vendorId");
	}

	public String getContactCode(Map incomingRequest)
	{
		return (String)incomingRequest.get("PoHeader_vendContactCode");
	}
}
