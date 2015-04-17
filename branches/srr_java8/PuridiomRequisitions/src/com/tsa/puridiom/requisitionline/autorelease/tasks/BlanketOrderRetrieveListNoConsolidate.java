package com.tsa.puridiom.requisitionline.autorelease.tasks;

import java.util.Map;

public class BlanketOrderRetrieveListNoConsolidate extends BlanketOrderRetrieveList
{
	public Map getGroup(Map incomingRequest)
	{
		return (Map)incomingRequest.get("groupByReq");
	}
}
