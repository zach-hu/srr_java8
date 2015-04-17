package com.tsa.puridiom.budgetcenter.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class BudgetCenterUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	bcList = (List) incomingRequest.get("budgetCenterList");


			for (int i=0; i < bcList.size(); i++) {
				// Get Audit Record
				BudgetCenter bc = (BudgetCenter) bcList.get(i);
				incomingRequest.put("budgetCenter",bc);
				// Update
				BudgetCenterUpdate task = new BudgetCenterUpdate();
				task.executeTask(incomingRequest);
				this.setStatus(task.getStatus()) ;
			}

			result = bcList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}