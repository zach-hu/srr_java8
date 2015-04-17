package com.tsa.puridiom.budgetaudit.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class BudgetAuditUpdateList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	baList = (List) incomingRequest.get("budgetAuditList");

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			for (int i=0; i < baList.size(); i++) {
				// Get Audit Record
				BudgetAudit ba = (BudgetAudit) baList.get(i);
				incomingRequest.put("budgetAudit",ba);

				dbs.getSession().merge(ba) ;

				// Update
//				if (! ba.isNewRecord()) {
//					BudgetAuditUpdate task = new BudgetAuditUpdate();
//					task.executeTask(incomingRequest);
//					this.setStatus(task.getStatus()) ;
//				} else {
//					BudgetAuditAdd task = new BudgetAuditAdd();
//					task.executeTask(incomingRequest);
//					this.setStatus(task.getStatus()) ;
//				}
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