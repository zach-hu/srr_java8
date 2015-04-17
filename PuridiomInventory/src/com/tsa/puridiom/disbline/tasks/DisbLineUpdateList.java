package com.tsa.puridiom.disbline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class DisbLineUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	disbLineList = (List) incomingRequest.get("disbLineList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("disbline-update-norecalc.xml");
				
			for (int i=0; i < disbLineList.size(); i++) {
				DisbLine disbLine = (DisbLine) disbLineList.get(i);

				incomingRequest.put("disbLine", disbLine);
				
				process.executeProcess(incomingRequest);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Disbursement Line update process failed.");
				}
			}

			result = disbLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}