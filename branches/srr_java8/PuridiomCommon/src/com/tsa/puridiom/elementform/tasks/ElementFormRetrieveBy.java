package com.tsa.puridiom.elementform.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ElementFormRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ElementForm as elementform where 1=1 ");
		if(incomingRequest.containsKey("ElementForm_formId"))
		{			
			String formId = (String) incomingRequest.get("ElementForm_formId");
			queryString.append(" AND elementform.id.formId = '" + formId + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementIndex"))
		{			
			String elementIndex = (String) incomingRequest.get("ElementForm_elementIndex");
			queryString.append(" AND elementform.id.elementIndex = '" + elementIndex + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementModule"))
		{			
			String elementModule = (String) incomingRequest.get("ElementForm_elementModule");
			queryString.append(" AND elementform.elementModule = '" + elementModule + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementId"))
		{			
			String elementId = (String) incomingRequest.get("ElementForm_elementId");
			queryString.append(" AND elementform.elementId = '" + elementId + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementType"))
		{			
			String elementType = (String) incomingRequest.get("ElementForm_elementType");
			queryString.append(" AND elementform.elementType = '" + elementType + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementLength"))
		{			
			String elementLength = (String) incomingRequest.get("ElementForm_elementLength");
			queryString.append(" AND elementform.elementLength = '" + elementLength + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementCase"))
		{			
			String elementCase = (String) incomingRequest.get("ElementForm_elementCase");
			queryString.append(" AND elementform.elementCase = '" + elementCase + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementFormat"))
		{			
			String elementFormat = (String) incomingRequest.get("ElementForm_elementFormat");
			queryString.append(" AND elementform.elementFormat = '" + elementFormat + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementLabel"))
		{			
			String elementLabel = (String) incomingRequest.get("ElementForm_elementLabel");
			queryString.append(" AND elementform.elementLabel = '" + elementLabel + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementDefault"))
		{			
			String elementDefault = (String) incomingRequest.get("ElementForm_elementDefault");
			queryString.append(" AND elementform.elementDefault = '" + elementDefault + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementTb"))
		{			
			String elementTb = (String) incomingRequest.get("ElementForm_elementTb");
			queryString.append(" AND elementform.elementTb = '" + elementTb + "'");
		}
		if(incomingRequest.containsKey("ElementForm_elementTc"))
		{			
			String elementTc = (String) incomingRequest.get("ElementForm_elementTc");
			queryString.append(" AND elementform.elementTc = '" + elementTc + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}