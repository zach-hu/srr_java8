package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionDiscrepRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionDiscrep as inspectiondiscrep where 1=1 ");
		if(incomingRequest.containsKey("InspectionDiscrep_icRecHeader"))
		{
			String icRecHeader = (String) incomingRequest.get("InspectionDiscrep_icRecHeader");
			queryString.append(" AND inspectiondiscrep.id.icRecHeader = '" + icRecHeader + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_icRecLine"))
		{
			String icRecLine = (String) incomingRequest.get("InspectionDiscrep_icRecLine");
			queryString.append(" AND inspectiondiscrep.id.icRecLine = '" + icRecLine + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_keySequence"))
		{
			String keySequence = (String) incomingRequest.get("InspectionDiscrep_keySequence");
			queryString.append(" AND inspectiondiscrep.id.keySequence = '" + keySequence + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_icMsrLine"))
		{
			String icMsrLine = (String) incomingRequest.get("InspectionDiscrep_icMsrLine");
			queryString.append(" AND inspectiondiscrep.icMsrLine = '" + icMsrLine + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_inspectCode"))
		{
			String inspectCode = (String) incomingRequest.get("InspectionDiscrep_inspectCode");
			queryString.append(" AND inspectiondiscrep.inspectCode = '" + inspectCode + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_inspRequirements"))
		{
			String inspRequirements = (String) incomingRequest.get("InspectionDiscrep_inspRequirements");
			queryString.append(" AND inspectiondiscrep.inspRequirements = '" + inspRequirements + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_inspDescription"))
		{
			String inspDescription = (String) incomingRequest.get("InspectionDiscrep_inspDescription");
			queryString.append(" AND inspectiondiscrep.inspDescription = '" + inspDescription + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_inspQuantity"))
		{
			String inspQuantity = (String) incomingRequest.get("InspectionDiscrep_inspQuantity");
			queryString.append(" AND inspectiondiscrep.inspQuantity = '" + inspQuantity + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_status"))
		{
			String status = (String) incomingRequest.get("InspectionDiscrep_status");
			queryString.append(" AND inspectiondiscrep.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_inspStartDate"))
		{
			String inspStartDate = (String) incomingRequest.get("InspectionDiscrep_inspStartDate");
			queryString.append(" AND inspectiondiscrep.inspStartDate = '" + inspStartDate + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_lastChange"))
		{
			String lastChange = (String) incomingRequest.get("InspectionDiscrep_lastChange");
			queryString.append(" AND inspectiondiscrep.lastChange = '" + lastChange + "'");
		}
		if(incomingRequest.containsKey("InspectionDiscrep_lastChangeBy"))
		{
			String lastChangeBy = (String) incomingRequest.get("InspectionDiscrep_lastChangeBy");
			queryString.append(" AND inspectiondiscrep.lastChangeBy = '" + lastChangeBy + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}