/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ApprovalForwardReqList extends Task
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
			String queryString = "from RequisitionHeader as RequisitionHeader, ApprovalLog as ApprovalLog WHERE ApprovalLog.id.icHeader = RequisitionHeader.icReqHeader AND (RequisitionHeader.status = ? OR RequisitionHeader.status = ?) AND " +
					"ApprovalLog.approved = 'A' AND ApprovalLog.callForward=?";
			Object [] args = {DocumentStatus.REQ_APPROVING, DocumentStatus.REQ_PLANNING_APPROVING, user};
			Type [] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};
			result = dbs.query(queryString, args, types) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Other Requisitions Waiting my approval failed with: " + e.getMessage(), e);
		}
		return result ;
    }
}
