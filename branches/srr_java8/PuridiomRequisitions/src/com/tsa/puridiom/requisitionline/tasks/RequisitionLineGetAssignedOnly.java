package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public class RequisitionLineGetAssignedOnly extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List reqLineList = (List) incomingRequest.get("requisitionLineList");
			String	userId = (String) incomingRequest.get("userId");
			
			for (int i = reqLineList.size() - 1; i >= 0 ; i--) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);
				String	assignedBuyer = reqLine.getAssignedBuyer();
				
				if (Utility.isEmpty(assignedBuyer) || !assignedBuyer.equalsIgnoreCase(userId)) {
						reqLineList.remove(i);
				}
			}
			
			result = reqLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return result ;
	}

}