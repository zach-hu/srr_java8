package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineUpdateReceiptOptions extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		List reqLineList = (List) incomingRequest.get("requisitionLineList");
		String receiptRequired = (String) incomingRequest.get("RequisitionHeader_receiptRequired");
					
		if (Utility.isEmpty(receiptRequired))
		{
			throw new Exception("The receipt option was not found.");
		}
				
		if (reqLineList != null)
		{
			for (int i = 0; i < reqLineList.size(); i++) 
			{
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("requisitionline-update-receipt-options.xml");
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("requisitionLine", reqLine);
				updateParameters.put("RequisitionLine_receiptRequired", receiptRequired);

				process.executeProcess(updateParameters);
				
				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("RequisitionLineUpdateReceiptOptions failed.");
				}
			}
		}
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}