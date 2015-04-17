/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ApprovalForwardInvList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String user = (String)incomingRequest.get("userId") ;
			//String queryString = "from ApprovalLog as ApprovalLog where ApprovalLog.approved='A' and ApprovalLog.callForward='" + user + "'";
			String queryString = "from InvoiceHeader as InvoiceHeader, ApprovalLog as ApprovalLog WHERE ApprovalLog.id.icHeader = InvoiceHeader.icIvcHeader AND InvoiceHeader.status = '" + DocumentStatus.IVC_APPROVING + "' AND " +
					"ApprovalLog.approved = 'A' AND ApprovalLog.callForward='" + user + "'";
			result = dbs.query(queryString) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Other Invoices Waiting my approval failed with: " + e.getMessage(), e);
		}
		return result ;
    }
}
