package com.tsa.puridiom.invvendor.tasks;
import java.util.Map;

public class InvVendorSetUp
{
	public void setUp(Map incomingRequest)
	{
		incomingRequest.put("InvVendor.itemNumber", "");
		incomingRequest.put("InvVendor.vendorId", "");
		incomingRequest.put("InvVendor.lastDate", "2003-10-29");
		incomingRequest.put("InvVendor.lastPrice", "0");
		incomingRequest.put("InvVendor.mfgNumber", "");
		incomingRequest.put("InvVendor.leadTime", "0");
		incomingRequest.put("InvVendor.primaryVendor", "");
	}
}