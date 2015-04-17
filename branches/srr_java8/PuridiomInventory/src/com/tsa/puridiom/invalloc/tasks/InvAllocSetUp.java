package com.tsa.puridiom.invalloc.tasks;
import java.util.Map;

public class InvAllocSetUp
{
	public void setUp(Map incomingRequest)
	{
		incomingRequest.put("InvAlloc.referenceType", "");
		incomingRequest.put("InvAlloc.itemNumber", "");
		incomingRequest.put("InvAlloc.icLoc", "0");
		incomingRequest.put("InvAlloc.icHeader", "0");
		incomingRequest.put("InvAlloc.icLine", "0");
		incomingRequest.put("InvAlloc.aisle", "");
		incomingRequest.put("InvAlloc.locrow", "");
		incomingRequest.put("InvAlloc.tier", "");
		incomingRequest.put("InvAlloc.bin", "");
		incomingRequest.put("InvAlloc.quantity", "0");
		incomingRequest.put("InvAlloc.icHeaderHistory", "0");
		incomingRequest.put("InvAlloc.lastQuantity", "0");
		incomingRequest.put("InvAlloc.itemLocation", "");
		incomingRequest.put("InvAlloc.lotNumber", "");
		incomingRequest.put("InvAlloc.itemDate", "2003-10-29");
		incomingRequest.put("InvAlloc.icText", "0");
	}
}