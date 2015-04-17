package com.tsa.puridiom.invformpart.tasks;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvFormPartAddMultiple extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
				
		try {
			if (incomingRequest.containsKey("InvFormPart_itemNumber")) {
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("invformpart-add.xml");
				List	invFormPartList = new ArrayList();
		
				for (int i = 1; i <= 12; i++)
				{
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("InvFormPart_itemNumber", incomingRequest.get("InvFormPart_itemNumber"));
					updateParameters.put("InvFormPart_formPart", String.valueOf(i));
					process.executeProcess(updateParameters);
					
					InvFormPart ifp = (InvFormPart) updateParameters.get("invFormPart");
					if (ifp != null)
					{
						invFormPartList.add(ifp);
					}
				}
				
				result = invFormPartList;
			}
			else {
				throw new Exception("The value for InvFormPart_itemNumber was not found.");
			}
	
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}