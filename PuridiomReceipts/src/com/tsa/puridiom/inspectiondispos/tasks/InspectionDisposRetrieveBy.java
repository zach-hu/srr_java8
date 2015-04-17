package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionDisposRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionDispos as inspectiondispos where 1=1 ");
		if(incomingRequest.containsKey("InspectionDispos_icRecHeader"))
		{			
			String icRecHeader = (String) incomingRequest.get("InspectionDispos_icRecHeader");
			queryString.append(" AND inspectiondispos.id.icRecHeader = '" + icRecHeader + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_icRecLine"))
		{			
			String icRecLine = (String) incomingRequest.get("InspectionDispos_icRecLine");
			queryString.append(" AND inspectiondispos.id.icRecLine = '" + icRecLine + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_keySequence"))
		{			
			String keySequence = (String) incomingRequest.get("InspectionDispos_keySequence");
			queryString.append(" AND inspectiondispos.id.keySequence = '" + keySequence + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_icMsrLine"))
		{			
			String icMsrLine = (String) incomingRequest.get("InspectionDispos_icMsrLine");
			queryString.append(" AND inspectiondispos.icMsrLine = '" + icMsrLine + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_disposition"))
		{			
			String disposition = (String) incomingRequest.get("InspectionDispos_disposition");
			queryString.append(" AND inspectiondispos.disposition = '" + disposition + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_respGroup"))
		{			
			String respGroup = (String) incomingRequest.get("InspectionDispos_respGroup");
			queryString.append(" AND inspectiondispos.respGroup = '" + respGroup + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_dispType"))
		{			
			String dispType = (String) incomingRequest.get("InspectionDispos_dispType");
			queryString.append(" AND inspectiondispos.dispType = '" + dispType + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_dispQuantity"))
		{			
			String dispQuantity = (String) incomingRequest.get("InspectionDispos_dispQuantity");
			queryString.append(" AND inspectiondispos.dispQuantity = '" + dispQuantity + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_dispClosed"))
		{			
			String dispClosed = (String) incomingRequest.get("InspectionDispos_dispClosed");
			queryString.append(" AND inspectiondispos.dispClosed = '" + dispClosed + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_lastChange"))
		{			
			String lastChange = (String) incomingRequest.get("InspectionDispos_lastChange");
			queryString.append(" AND inspectiondispos.lastChange = '" + lastChange + "'");
		}
		if(incomingRequest.containsKey("InspectionDispos_lastChangeBy"))
		{			
			String lastChangeBy = (String) incomingRequest.get("InspectionDispos_lastChangeBy");
			queryString.append(" AND inspectiondispos.lastChangeBy = '" + lastChangeBy + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}