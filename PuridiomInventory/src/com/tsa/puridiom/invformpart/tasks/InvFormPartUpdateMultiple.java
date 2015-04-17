package com.tsa.puridiom.invformpart.tasks;

import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class InvFormPartUpdateMultiple extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
				
		try {
			if (incomingRequest.containsKey("InvFormPart_itemNumber")) {
				String	formPartArray[] = (String[]) incomingRequest.get("InvFormPart_formPart");
				
				for (int i = 0; i < formPartArray.length; i++)
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invformpart-update-from-array.xml");
					incomingRequest.put("formPart", formPartArray[i]);
					incomingRequest.put("index", new BigDecimal(i));
					process.executeProcess(incomingRequest);
				}
				
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