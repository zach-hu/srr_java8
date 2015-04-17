package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

public class RequisitionLineStatusUpdate
{
	public void setUp(Map incomingRequest)
	{

		String status = (String)incomingRequest.get("newStatus") ;
		incomingRequest.put("RequisitionLine_status", status);
	}
}