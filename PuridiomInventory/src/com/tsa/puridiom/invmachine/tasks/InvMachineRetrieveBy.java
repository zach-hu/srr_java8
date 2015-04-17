package com.tsa.puridiom.invmachine.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvMachineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvMachine as invmachine where 1=1 ");
		if(incomingRequest.containsKey("InvMachine_machineId"))
		{
			String machineId = (String) incomingRequest.get("InvMachine_machineId");
			queryString.append(" AND invmachine.machineId = '" + machineId + "'");
		}
		if(incomingRequest.containsKey("InvMachine_description"))
		{
			String description = (String) incomingRequest.get("InvMachine_description");
			queryString.append(" AND invmachine.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InvMachine_workCenterId"))
		{
			String workCenterId = (String) incomingRequest.get("InvMachine_workCenterId");
			queryString.append(" AND invmachine.workCenterId = '" + workCenterId + "'");
		}
		if(incomingRequest.containsKey("InvMachine_assetId"))
		{
			String assetId = (String) incomingRequest.get("InvMachine_assetId");
			queryString.append(" AND invmachine.assetId = '" + assetId + "'");
		}
		if(incomingRequest.containsKey("InvMachine_notes"))
		{
			String notes = (String) incomingRequest.get("InvMachine_notes");
			queryString.append(" AND invmachine.notes = '" + notes + "'");
		}
		if(incomingRequest.containsKey("InvMachine_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InvMachine_dateEntered");
			queryString.append(" AND invmachine.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvMachine_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("InvMachine_dateExpires");
			queryString.append(" AND invmachine.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("InvMachine_owner"))
		{
			String owner = (String) incomingRequest.get("InvMachine_owner");
			queryString.append(" AND invmachine.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvMachine_status"))
		{
			String status = (String) incomingRequest.get("InvMachine_status");
			queryString.append(" AND invmachine.status = '" + status + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}