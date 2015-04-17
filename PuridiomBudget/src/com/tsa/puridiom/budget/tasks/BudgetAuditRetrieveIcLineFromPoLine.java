package com.tsa.puridiom.budget.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Richard
 */
public class BudgetAuditRetrieveIcLineFromPoLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{

			String icPoLine = (String) incomingRequest.get("PoLine_icPoLine");

			incomingRequest.put("PoLine_icPoLine",icPoLine) ;
			incomingRequest.put("BudgetAudit_icLine",icPoLine) ;
			incomingRequest.put("BudgetAudit_lineType","PO") ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
