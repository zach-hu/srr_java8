/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqWaitingApproval extends Task
{
    public Object executeTask(Object object) throws Exception
    {

    	 Map incomingRequest = (Map) object;
    	 List result = null;
         try
         {
        	String user = (String)incomingRequest.get("userId") ;
        	DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
     		String queryString = "from RfqHeader as RfqHeader, ApprovalLog as ApprovalLog WHERE ApprovalLog.id.icHeader = RfqHeader.icRfqHeader AND RfqHeader.status = '" + DocumentStatus.RFQ_APPROVING + "' AND " +
     		"ApprovalLog.approved = 'A' AND ApprovalLog.callForward='" + user + "'";
     		List resultList= dbs.query(queryString) ;
     		result=resultList;
     		this.setStatus(Status.SUCCEEDED);
 		}
         catch (Exception e)
         {
 			this.setStatus(Status.FAILED);
 			throw new TsaException("Rfq Approvals could not be completed!", e);
 		}

         return result ;
    }
}
