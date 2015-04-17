package com.tsa.puridiom.elementdata.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ElementDataRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ElementData as elementdata where 1=1 ");
		if(incomingRequest.containsKey("ElementData_formId"))
		{
			String formId = (String) incomingRequest.get("ElementData_formId");
			queryString.append(" AND elementdata.id.formId = '" + formId + "'");
		}
		if(incomingRequest.containsKey("ElementData_icHeader"))
		{
			String icHeader = (String) incomingRequest.get("ElementData_icHeader");
			queryString.append(" AND elementdata.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("ElementData_icLine"))
		{
			String icLine = (String) incomingRequest.get("ElementData_icLine");
			queryString.append(" AND elementdata.id.icLine = '" + icLine + "'");
		}
		if(incomingRequest.containsKey("ElementData_icSequence"))
		{
			String icSequence = (String) incomingRequest.get("ElementData_icSequece");
			queryString.append(" AND elementdata.id.icSequence = '" + icSequence + "'");
		}
		if(incomingRequest.containsKey("ElementData_elementId"))
		{
			String elementId = (String) incomingRequest.get("ElementData_elementId");
			queryString.append(" AND elementdata.id.elementId = '" + elementId + "'");
		}
		if(incomingRequest.containsKey("ElementData_elementValue"))
		{
			String elementValue = (String) incomingRequest.get("ElementData_elementValue");
			queryString.append(" AND elementdata.elementValue = '" + elementValue + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}