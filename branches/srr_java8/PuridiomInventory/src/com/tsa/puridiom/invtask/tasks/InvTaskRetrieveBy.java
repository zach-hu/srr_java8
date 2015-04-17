package com.tsa.puridiom.invtask.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvTaskRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvTask as invtask where 1=1 ");
		if(incomingRequest.containsKey("InvTask_taskId"))
		{
			String taskId = (String) incomingRequest.get("InvTask_taskId");
			queryString.append(" AND invtask.taskId = '" + taskId + "'");
		}
		if(incomingRequest.containsKey("InvTask_refNo"))
		{
			String refNo = (String) incomingRequest.get("InvTask_refNo");
			queryString.append(" AND invtask.refNo = '" + refNo + "'");
		}
		if(incomingRequest.containsKey("InvTask_stageId"))
		{
			String stageId = (String) incomingRequest.get("InvTask_stageId");
			queryString.append(" AND invtask.stageId = '" + stageId + "'");
		}
		if(incomingRequest.containsKey("InvTask_taskType"))
		{
			String taskType = (String) incomingRequest.get("InvTask_taskType");
			queryString.append(" AND invtask.taskType = '" + taskType + "'");
		}
		if(incomingRequest.containsKey("InvTask_description"))
		{
			String description = (String) incomingRequest.get("InvTask_description");
			queryString.append(" AND invtask.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InvTask_notes"))
		{
			String notes = (String) incomingRequest.get("InvTask_notes");
			queryString.append(" AND invtask.notes = '" + notes + "'");
		}
		if(incomingRequest.containsKey("InvTask_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InvTask_dateEntered");
			queryString.append(" AND invtask.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvTask_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("InvTask_dateExpires");
			queryString.append(" AND invtask.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("InvTask_owner"))
		{
			String owner = (String) incomingRequest.get("InvTask_owner");
			queryString.append(" AND invtask.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvTask_status"))
		{
			String status = (String) incomingRequest.get("InvTask_status");
			queryString.append(" AND invtask.status = '" + status + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}