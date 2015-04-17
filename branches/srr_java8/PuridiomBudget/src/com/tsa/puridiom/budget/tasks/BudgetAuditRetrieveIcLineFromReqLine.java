package com.tsa.puridiom.budget.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Richard
 */
public class BudgetAuditRetrieveIcLineFromReqLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{

			String icReqLine = (String) incomingRequest.get("RequisitionLine_icReqLine");
			//BigDecimal b_icReqLine = new BigDecimal(icReqLine);

			incomingRequest.put("RequisitionLine_icReqLine",icReqLine) ;
			incomingRequest.put("BudgetAudit_icLine",icReqLine) ;
			incomingRequest.put("BudgetAudit_lineType","REQ") ;

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
