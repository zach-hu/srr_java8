package com.tsa.puridiom.invstage.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvStageRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvStage as invstage where 1=1 ");
		if(incomingRequest.containsKey("InvStage_stageId"))
		{
			String stageId = (String) incomingRequest.get("InvStage_stageId");
			queryString.append(" AND invstage.stageId = '" + stageId + "'");
		}
		if(incomingRequest.containsKey("InvStage_description"))
		{
			String description = (String) incomingRequest.get("InvStage_description");
			queryString.append(" AND invstage.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InvStage_respons"))
		{
			String respons = (String) incomingRequest.get("InvStage_respons");
			queryString.append(" AND invstage.respons = '" + respons + "'");
		}
		if(incomingRequest.containsKey("InvStage_notes"))
		{
			String notes = (String) incomingRequest.get("InvStage_notes");
			queryString.append(" AND invstage.notes = '" + notes + "'");
		}
		if(incomingRequest.containsKey("InvStage_workCenterId"))
		{
			String workCenterId = (String) incomingRequest.get("InvStage_workCenterId");
			queryString.append(" AND invstage.workCenterId = '" + workCenterId + "'");
		}
		if(incomingRequest.containsKey("InvStage_utilization"))
		{
			String utilization = (String) incomingRequest.get("InvStage_utilization");
			queryString.append(" AND invstage.utilization = '" + utilization + "'");
		}
		if(incomingRequest.containsKey("InvStage_qtyDays"))
		{
			String qtyDays = (String) incomingRequest.get("InvStage_qtyDays");
			queryString.append(" AND invstage.qtyDays = '" + qtyDays + "'");
		}
		if(incomingRequest.containsKey("InvStage_setupHours"))
		{
			String setupHours = (String) incomingRequest.get("InvStage_setupHours");
			queryString.append(" AND invstage.setupHours = '" + setupHours + "'");
		}
		if(incomingRequest.containsKey("InvStage_partsHour"))
		{
			String partsHour = (String) incomingRequest.get("InvStage_partsHour");
			queryString.append(" AND invstage.partsHour = '" + partsHour + "'");
		}
		if(incomingRequest.containsKey("InvStage_timePart"))
		{
			String timePart = (String) incomingRequest.get("InvStage_timePart");
			queryString.append(" AND invstage.timePart = '" + timePart + "'");
		}
		if(incomingRequest.containsKey("InvStage_vendorName"))
		{
			String vendorName = (String) incomingRequest.get("InvStage_vendorName");
			queryString.append(" AND invstage.vendorName = '" + vendorName + "'");
		}
		if(incomingRequest.containsKey("InvStage_leadTime"))
		{
			String leadTime = (String) incomingRequest.get("InvStage_leadTime");
			queryString.append(" AND invstage.leadTime = '" + leadTime + "'");
		}
		if(incomingRequest.containsKey("InvStage_outside"))
		{
			String outside = (String) incomingRequest.get("InvStage_outside");
			queryString.append(" AND invstage.outside = '" + outside + "'");
		}
		if(incomingRequest.containsKey("InvStage_descriptor"))
		{
			String descriptor = (String) incomingRequest.get("InvStage_descriptor");
			queryString.append(" AND invstage.descriptor = '" + descriptor + "'");
		}
		if(incomingRequest.containsKey("InvStage_machineId"))
		{
			String machineId = (String) incomingRequest.get("InvStage_machineId");
			queryString.append(" AND invstage.machineId = '" + machineId + "'");
		}
		if(incomingRequest.containsKey("InvStage_backflush"))
		{
			String backflush = (String) incomingRequest.get("InvStage_backflush");
			queryString.append(" AND invstage.backflush = '" + backflush + "'");
		}
		if(incomingRequest.containsKey("InvStage_persons"))
		{
			String persons = (String) incomingRequest.get("InvStage_persons");
			queryString.append(" AND invstage.persons = '" + persons + "'");
		}
		if(incomingRequest.containsKey("InvStage_ccost"))
		{
			String ccost = (String) incomingRequest.get("InvStage_ccost");
			queryString.append(" AND invstage.ccost = '" + ccost + "'");
		}
		if(incomingRequest.containsKey("InvStage_unitOfMeasure"))
		{
			String unitOfMeasure = (String) incomingRequest.get("InvStage_unitOfMeasure");
			queryString.append(" AND invstage.unitOfMeasure = '" + unitOfMeasure + "'");
		}

		if(incomingRequest.containsKey("InvStage_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InvStage_dateEntered");
			queryString.append(" AND invstage.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvStage_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("InvStage_dateExpires");
			queryString.append(" AND invstage.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("InvStage_owner"))
		{
			String owner = (String) incomingRequest.get("InvStage_owner");
			queryString.append(" AND invstage.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvStage_status"))
		{
			String status = (String) incomingRequest.get("InvStage_status");
			queryString.append(" AND invstage.status = '" + status + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}