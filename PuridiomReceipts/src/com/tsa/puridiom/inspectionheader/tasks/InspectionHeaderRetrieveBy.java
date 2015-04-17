package com.tsa.puridiom.inspectionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionHeaderRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionHeader as inspectionheader where 1=1 ");
		if(incomingRequest.containsKey("InspectionHeader_icInspNo"))
		{
			String icInspNo = (String) incomingRequest.get("InspectionHeader_icInspNo");
			queryString.append(" AND inspectionheader.id.icInspNo = '" + icInspNo + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_icMsrLine"))
		{
			String icMsrLine = (String) incomingRequest.get("InspectionHeader_icMsrLine");
			queryString.append(" AND inspectionheader.id.icMsrLine = '" + icMsrLine + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_inspectType"))
		{
			String inspectType = (String) incomingRequest.get("InspectionHeader_inspectType");
			queryString.append(" AND inspectionheader.inspectType = '" + inspectType + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_inspectorId"))
		{
			String inspectorId = (String) incomingRequest.get("InspectionHeader_inspectorId");
			queryString.append(" AND inspectionheader.inspectorId = '" + inspectorId + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_engineerId"))
		{
			String engineerId = (String) incomingRequest.get("InspectionHeader_engineerId");
			queryString.append(" AND inspectionheader.engineerId = '" + engineerId + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_remoteInspId"))
		{
			String remoteInspId = (String) incomingRequest.get("InspectionHeader_remoteInspId");
			queryString.append(" AND inspectionheader.remoteInspId = '" + remoteInspId + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_purchaseSpecs"))
		{
			String purchaseSpecs = (String) incomingRequest.get("InspectionHeader_purchaseSpecs");
			queryString.append(" AND inspectionheader.purchaseSpecs = '" + purchaseSpecs + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_area"))
		{
			String area = (String) incomingRequest.get("InspectionHeader_area");
			queryString.append(" AND inspectionheader.area = '" + area + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_storage"))
		{
			String storage = (String) incomingRequest.get("InspectionHeader_storage");
			queryString.append(" AND inspectionheader.storage = '" + storage + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_location"))
		{
			String location = (String) incomingRequest.get("InspectionHeader_location");
			queryString.append(" AND inspectionheader.location = '" + location + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_standardCode"))
		{
			String standardCode = (String) incomingRequest.get("InspectionHeader_standardCode");
			queryString.append(" AND inspectionheader.standardCode = '" + standardCode + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_udf01"))
		{
			String udf01 = (String) incomingRequest.get("InspectionHeader_udf01");
			queryString.append(" AND inspectionheader.udf01 = '" + udf01 + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_udf02"))
		{
			String udf02 = (String) incomingRequest.get("InspectionHeader_udf02");
			queryString.append(" AND inspectionheader.udf02 = '" + udf02 + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_udf03"))
		{
			String udf03 = (String) incomingRequest.get("InspectionHeader_udf03");
			queryString.append(" AND inspectionheader.udf03 = '" + udf03 + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_udf04"))
		{
			String udf04 = (String) incomingRequest.get("InspectionHeader_udf04");
			queryString.append(" AND inspectionheader.udf04 = '" + udf04 + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_udf05"))
		{
			String udf05 = (String) incomingRequest.get("InspectionHeader_udf05");
			queryString.append(" AND inspectionheader.udf05 = '" + udf05 + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_inspType"))
		{
			String inspType = (String) incomingRequest.get("InspectionHeader_inspType");
			queryString.append(" AND inspectionheader.inspType = '" + inspType + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_apprDt"))
		{
			String apprDt = (String) incomingRequest.get("InspectionHeader_apprDt");
			queryString.append(" AND inspectionheader.apprDt = '" + apprDt + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InspectionHeader_dateEntered");
			queryString.append(" AND inspectionheader.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_status"))
		{
			String status = (String) incomingRequest.get("InspectionHeader_status");
			queryString.append(" AND inspectionheader.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_owner"))
		{
			String owner = (String) incomingRequest.get("InspectionHeader_owner");
			queryString.append(" AND inspectionheader.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_lastChange"))
		{
			String lastChange = (String) incomingRequest.get("InspectionHeader_lastChange");
			queryString.append(" AND inspectionheader.lastChange = '" + lastChange + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_lastChangeBy"))
		{
			String lastChangeBy = (String) incomingRequest.get("InspectionHeader_lastChangeBy");
			queryString.append(" AND inspectionheader.lastChangeBy = '" + lastChangeBy + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_assignedDate"))
		{
			String assignedDate = (String) incomingRequest.get("InspectionHeader_assignedDate");
			queryString.append(" AND inspectionheader.assignedDate = '" + assignedDate + "'");
		}
		if(incomingRequest.containsKey("InspectionHeader_inspectionNumber"))
		{
			String inspectionNumber = (String) incomingRequest.get("InspectionHeader_inspectionNumber");
			queryString.append(" AND inspectionheader.inspectionNumber = '" + inspectionNumber + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}