package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionHistoryRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionHistory as inspectionhistory where 1=1 ");
		if(incomingRequest.containsKey("InspectionHistory_icHistory"))
		{
			String icHistory = (String) incomingRequest.get("InspectionHistory_icHistory");
			queryString.append(" AND inspectionhistory.id.icHistory = '" + icHistory + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_icInspNo"))
		{
			String icInspNo = (String) incomingRequest.get("InspectionHistory_icInspNo");
			queryString.append(" AND inspectionhistory.icInspNo = '" + icInspNo + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_icMsrLine"))
		{
			String icMsrLine = (String) incomingRequest.get("InspectionHistory_icMsrLine");
			queryString.append(" AND inspectionhistory.icMsrLine = '" + icMsrLine + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_icRecLine"))
		{
			String icRecLine = (String) incomingRequest.get("InspectionHistory_icRecLine");
			queryString.append(" AND inspectionhistory.icRecLine = '" + icRecLine + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_recType"))
		{
			String recType = (String) incomingRequest.get("InspectionHistory_recType");
			queryString.append(" AND inspectionhistory.recType = '" + recType + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_statusDate"))
		{
			String statusDate = (String) incomingRequest.get("InspectionHistory_statusDate");
			queryString.append(" AND inspectionhistory.statusDate = '" + statusDate + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_quantity"))
		{
			String quantity = (String) incomingRequest.get("InspectionHistory_quantity");
			queryString.append(" AND inspectionhistory.quantity = '" + quantity + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_area"))
		{
			String area = (String) incomingRequest.get("InspectionHistory_area");
			queryString.append(" AND inspectionhistory.area = '" + area + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_storage"))
		{
			String storage = (String) incomingRequest.get("InspectionHistory_storage");
			queryString.append(" AND inspectionhistory.storage = '" + storage + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_location"))
		{
			String location = (String) incomingRequest.get("InspectionHistory_location");
			queryString.append(" AND inspectionhistory.location = '" + location + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_status"))
		{
			String status = (String) incomingRequest.get("InspectionHistory_status");
			queryString.append(" AND inspectionhistory.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_owner"))
		{
			String owner = (String) incomingRequest.get("InspectionHistory_owner");
			queryString.append(" AND inspectionhistory.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InspectionHistory_historyText"))
		{
			String historyText = (String) incomingRequest.get("InspectionHistory_historyText");
			queryString.append(" AND inspectionhistory.historyText = '" + historyText + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}