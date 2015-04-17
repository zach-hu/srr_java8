package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;
import com.tsa.puridiom.common.documents.DocumentStatus;

public class RequisitionLineCancel
{
	public void setUp(Map incomingRequest)
	{

		incomingRequest.put("RequisitionLine_status", DocumentStatus.CANCELLED);
	}
}