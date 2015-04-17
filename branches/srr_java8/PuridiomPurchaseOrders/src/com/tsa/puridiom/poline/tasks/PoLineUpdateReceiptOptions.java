package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoLineUpdateReceiptOptions extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
				
		List poLineList = (List) incomingRequest.get("poLineList");
		String receiptRequired = (String) incomingRequest.get("PoHeader_receiptRequired");
					
		if (Utility.isEmpty(receiptRequired)) {
			throw new Exception("The receipt option was not found.");
		}
				
		if (poLineList != null)
		{
			for (int i = 0; i < poLineList.size(); i++) 
			{
				PoLine poLine = (PoLine) poLineList.get(i);
				
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("poline-update-receipt-options.xml");
				
				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("poLine", poLine);
				updateParameters.put("PoLine_receiptRequired", receiptRequired);

				process.executeProcess(updateParameters);
				
				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("PoLineUpdateReceiptOptions failed.");
				}
			}
		}
		/*
		else 
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invitem-lookup-by-id.xml");
			incomingRequest.put("createAction", "SAVE");
			process.executeProcess(incomingRequest);
				
			if (process.getStatus() < Status.SUCCEEDED) {
				throw new Exception("InvItemLookup failed.");
			}
		}
		*/
					
		this.status = Status.SUCCEEDED;
		return result;
	}
}