package com.tsa.puridiom.budgettran.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class BudgetTranUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	baList = (List) incomingRequest.get("budgetTranList");


			for (int i=0; i < baList.size(); i++) {
				// Get Tran Record
				BudgetTran ba = (BudgetTran) baList.get(i);
				incomingRequest.put("budgetTran",ba);
				// Update
				if (! ba.isNewRecord()) {
					BudgetTranUpdate task = new BudgetTranUpdate();
					task.executeTask(incomingRequest);
					this.setStatus(task.getStatus()) ;
				} else {
					BudgetTranAdd task = new BudgetTranAdd();
					task.executeTask(incomingRequest);
					this.setStatus(task.getStatus()) ;
				}
			}

			result = baList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}