package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.common.tasks.SupplierFaxUtility;

public class PoFaxUtility extends SupplierFaxUtility
{

	public String getVendorId(Map incomingRequest)
	{
		return (String)incomingRequest.get("Pdf_PoHeader_contactId");
	}

	public String getContactCode(Map incomingRequest)
	{
		return (String)incomingRequest.get("PoHeader_vendContactCode");
	}
}
