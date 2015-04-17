package com.tsa.puridiom.invformpart.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvFormPartUpdateMultipleSetup extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
				
		try
		{
			String itemNumber = (String) incomingRequest.get("InvFormPart_itemNumber");
			String formPart = (String) incomingRequest.get("formPart");
			incomingRequest.put("InvFormPart_formPart", formPart);
			
			//throw exception if item # or form part is empty!
			
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}