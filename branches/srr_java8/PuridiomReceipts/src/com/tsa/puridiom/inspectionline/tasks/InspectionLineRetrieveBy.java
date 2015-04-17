package com.tsa.puridiom.inspectionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionLineRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionLine as inspectionline where 1=1 ");
		if(incomingRequest.containsKey("InspectionLine_icInspNo"))
		{
			String icInspNo = (String) incomingRequest.get("InspectionLine_icInspNo");
			queryString.append(" AND inspectionline.icInspNo = '" + icInspNo + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_icInspLine"))
		{
			String icInspLine = (String) incomingRequest.get("InspectionLine_icInspLine");
			queryString.append(" AND inspectionline.id.icInspLine = '" + icInspLine + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_inspectCode"))
		{
			String inspectCode = (String) incomingRequest.get("InspectionLine_inspectCode");
			queryString.append(" AND inspectionline.inspectCode = '" + inspectCode + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_seqNo"))
		{
			String seqNo = (String) incomingRequest.get("InspectionLine_seqNo");
			queryString.append(" AND inspectionline.seqNo = '" + seqNo + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_critNo"))
		{
			String critNo = (String) incomingRequest.get("InspectionLine_critNo");
			queryString.append(" AND inspectionline.critNo = '" + critNo + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_standardCode"))
		{
			String standardCode = (String) incomingRequest.get("InspectionLine_standardCode");
			queryString.append(" AND inspectionline.standardCode = '" + standardCode + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_lockFlag"))
		{
			String lockFlag = (String) incomingRequest.get("InspectionLine_lockFlag");
			queryString.append(" AND inspectionline.lockFlag = '" + lockFlag + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_udf01"))
		{
			String udf01 = (String) incomingRequest.get("InspectionLine_udf01");
			queryString.append(" AND inspectionline.udf01 = '" + udf01 + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_udf02"))
		{
			String udf02 = (String) incomingRequest.get("InspectionLine_udf02");
			queryString.append(" AND inspectionline.udf02 = '" + udf02 + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_udf03"))
		{
			String udf03 = (String) incomingRequest.get("InspectionLine_udf03");
			queryString.append(" AND inspectionline.udf03 = '" + udf03 + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_udf04"))
		{
			String udf04 = (String) incomingRequest.get("InspectionLine_udf04");
			queryString.append(" AND inspectionline.udf04 = '" + udf04 + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_udf05"))
		{
			String udf05 = (String) incomingRequest.get("InspectionLine_udf05");
			queryString.append(" AND inspectionline.udf05 = '" + udf05 + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InspectionLine_dateEntered");
			queryString.append(" AND inspectionline.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_status"))
		{
			String status = (String) incomingRequest.get("InspectionLine_status");
			queryString.append(" AND inspectionline.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_owner"))
		{
			String owner = (String) incomingRequest.get("InspectionLine_owner");
			queryString.append(" AND inspectionline.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_lastChange"))
		{
			String lastChange = (String) incomingRequest.get("InspectionLine_lastChange");
			queryString.append(" AND inspectionline.lastChange = '" + lastChange + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_lastChangeBy"))
		{
			String lastChangeBy = (String) incomingRequest.get("InspectionLine_lastChangeBy");
			queryString.append(" AND inspectionline.lastChangeBy = '" + lastChangeBy + "'");
		}
		if(incomingRequest.containsKey("InspectionLine_critDescription"))
		{
			String critDescription = (String) incomingRequest.get("InspectionLine_critDescription");
			queryString.append(" AND inspectionline.critDescription = '" + critDescription + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}