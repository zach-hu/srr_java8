/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetAudit;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class BudgetCenterRetrieveIdFromBudgetAudit extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		List baList = (List) incomingRequest.get("budgetAudit") ;
		String budgetCenter_Id = "0";

		if (baList != null) {

		    for (int i = 0; i < baList.size(); i++) {
		    	BudgetAudit budgetAudit = (BudgetAudit) baList.get(i) ;
		    	budgetCenter_Id = (String) budgetAudit.getBudgetId().toString();
		    	break;
		    }
		}

		incomingRequest.put("BudgetCenter_budgetId", budgetCenter_Id);
		incomingRequest.put("BudgetCenter_status", "2");

		this.setStatus(Status.SUCCEEDED) ;

		return null  ;
	}
}
