package com.tsa.puridiom.inspectionstd.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionStdRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionStd as inspectionstd where 1=1 ");
		if(incomingRequest.containsKey("InspectionStd_standardCode"))
		{
			String standardCode = (String) incomingRequest.get("InspectionStd_standardCode");
			queryString.append(" AND inspectionstd.standardCode = '" + standardCode + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_inspectType"))
		{
			String inspectType = (String) incomingRequest.get("InspectionStd_inspectType");
			queryString.append(" AND inspectionstd.inspectType = '" + inspectType + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_inspectCode"))
		{
			String inspectCode = (String) incomingRequest.get("InspectionStd_inspectCode");
			queryString.append(" AND inspectionstd.inspectCode = '" + inspectCode + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_critNo"))
		{
			String critNo = (String) incomingRequest.get("InspectionStd_critNo");
			queryString.append(" AND inspectionstd.critNo = '" + critNo + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_description"))
		{
			String description = (String) incomingRequest.get("InspectionStd_description");
			queryString.append(" AND inspectionstd.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_defaultFlag"))
		{
			String defaultFlag = (String) incomingRequest.get("InspectionStd_defaultFlag");
			queryString.append(" AND inspectionstd.defaultFlag = '" + defaultFlag + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("InspectionStd_dateEntered");
			queryString.append(" AND inspectionstd.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_status"))
		{
			String status = (String) incomingRequest.get("InspectionStd_status");
			queryString.append(" AND inspectionstd.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_owner"))
		{
			String owner = (String) incomingRequest.get("InspectionStd_owner");
			queryString.append(" AND inspectionstd.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_lastChange"))
		{
			String lastChange = (String) incomingRequest.get("InspectionStd_lastChange");
			queryString.append(" AND inspectionstd.lastChange = '" + lastChange + "'");
		}
		if(incomingRequest.containsKey("InspectionStd_lastChangeBy"))
		{
			String lastChangeBy = (String) incomingRequest.get("InspectionStd_lastChangeBy");
			queryString.append(" AND inspectionstd.lastChangeBy = '" + lastChangeBy + "'");
		}

		queryString.append(" order by inspectionstd.standardCode, inspectionstd.inspectCode, inspectionstd.icInspStd") ;
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}