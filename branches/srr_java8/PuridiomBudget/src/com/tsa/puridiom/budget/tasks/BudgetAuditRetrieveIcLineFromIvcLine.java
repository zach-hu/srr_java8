package com.tsa.puridiom.budget.tasks;

import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Richard
 */
public class BudgetAuditRetrieveIcLineFromIvcLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			String icIvcLine = (String) incomingRequest.get("InvoiceLine_icIvcLine");
			String icPoLine = (String) incomingRequest.get("InvoiceLine_icPoLine");
			incomingRequest.put("InvoiceLine_icPoLine",icPoLine);

			incomingRequest.put("InvoiceLine_icPoLine",icIvcLine) ;
			incomingRequest.put("InvoiceLine_icPoLine",icPoLine) ;
			incomingRequest.put("BudgetAudit_icLine",icPoLine) ;
			incomingRequest.put("BudgetAudit_lineType","INV") ;

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
