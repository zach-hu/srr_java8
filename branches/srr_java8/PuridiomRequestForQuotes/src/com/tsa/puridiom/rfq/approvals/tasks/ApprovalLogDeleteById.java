package com.tsa.puridiom.rfq.approvals.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.entity.*;

public class ApprovalLogDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ApprovalLog approvalLog = (ApprovalLog)incomingRequest.get("approvalLog");
		if(approvalLog == null)
		{
			approvalLog = new ApprovalLog();
			ApprovalLogSetValuesPK setValues = new ApprovalLogSetValuesPK();
			setValues.setValues(incomingRequest, approvalLog);
		}
		dbs.delete(approvalLog);
		this.setStatus(dbs.getStatus()) ;
		return approvalLog ;
	}

}