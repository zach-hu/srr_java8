package com.tsa.puridiom.invhistory.tasks;
import java.util.Map;

public class InvHistorySetUp
{
	public void setUp(Map incomingRequest)
	{
		incomingRequest.put("InvHistory.seqNumber", "0");
		incomingRequest.put("InvHistory.itemNumber", "");
		incomingRequest.put("InvHistory.docPrtDate", "2003-10-29");
		incomingRequest.put("InvHistory.primUser", "");
		incomingRequest.put("InvHistory.puAppDate", "2003-10-29");
		incomingRequest.put("InvHistory.faId", "");
		incomingRequest.put("InvHistory.faAppDate", "2003-10-29");
	}
}