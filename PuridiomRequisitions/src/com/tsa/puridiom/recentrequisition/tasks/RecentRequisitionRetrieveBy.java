package com.tsa.puridiom.recentrequisition.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class RecentRequisitionRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from RecentRequisition as recentrequisition where 1=1 ");
		if(incomingRequest.containsKey("RecentRequisition_requisitionerCode"))
		{			
			String requisitionerCode = (String) incomingRequest.get("RecentRequisition_requisitionerCode");
			queryString.append(" AND recentrequisition.id.requisitionerCode = '" + requisitionerCode + "'");
		}
		if(incomingRequest.containsKey("RecentRequisition_icReqHeader"))
		{			
			String icReqHeader = (String) incomingRequest.get("RecentRequisition_icReqHeader");
			queryString.append(" AND recentrequisition.id.icReqHeader = '" + icReqHeader + "'");
		}
		if(incomingRequest.containsKey("RecentRequisition_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("RecentRequisition_dateEntered");
			queryString.append(" AND recentrequisition.dateEntered = '" + dateEntered + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}