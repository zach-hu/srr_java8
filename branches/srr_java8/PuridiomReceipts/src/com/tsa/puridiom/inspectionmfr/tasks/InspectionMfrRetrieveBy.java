package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionMfrRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionMfr as inspectionmfr where 1=1 ");
		if(incomingRequest.containsKey("InspectionMfr_icMfrNo"))
		{
			String icMfrNo = (String) incomingRequest.get("InspectionMfr_icMfrNo");
			queryString.append(" AND inspectionmfr.id.icMfrNo = '" + icMfrNo + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_icInspNo"))
		{
			String icInspNo = (String) incomingRequest.get("InspectionMfr_icInspNo");
			queryString.append(" AND inspectionmfr.icInspNo = '" + icInspNo + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_icMsrLine"))
		{
			String icMsrLine = (String) incomingRequest.get("InspectionMfr_icMsrLine");
			queryString.append(" AND inspectionmfr.icMsrLine = '" + icMsrLine + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_documentType"))
		{
			String documentType = (String) incomingRequest.get("InspectionMfr_documentType");
			queryString.append(" AND inspectionmfr.documentType = '" + documentType + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_mfrName"))
		{
			String mfrName = (String) incomingRequest.get("InspectionMfr_mfrName");
			queryString.append(" AND inspectionmfr.mfrName = '" + mfrName + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_mfrNo"))
		{
			String mfrNo = (String) incomingRequest.get("InspectionMfr_mfrNo");
			queryString.append(" AND inspectionmfr.mfrNo = '" + mfrNo + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_mfrHeatLot"))
		{
			String mfrHeatLot = (String) incomingRequest.get("InspectionMfr_mfrHeatLot");
			queryString.append(" AND inspectionmfr.mfrHeatLot = '" + mfrHeatLot + "'");
		}
		if(incomingRequest.containsKey("InspectionMfr_shelfLifeDate"))
		{
			String shelfLifeDate = (String) incomingRequest.get("InspectionMfr_shelfLifeDate");
			queryString.append(" AND inspectionmfr.shelfLifeDate = '" + shelfLifeDate + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}