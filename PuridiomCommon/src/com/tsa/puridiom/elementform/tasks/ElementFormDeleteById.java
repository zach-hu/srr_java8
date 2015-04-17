package com.tsa.puridiom.elementform.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ElementFormDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ElementForm elementForm = (ElementForm)incomingRequest.get("elementForm");
		if(elementForm == null)
		{
			elementForm = new ElementForm();
		}
		ElementFormSetValuesPK setValues = new ElementFormSetValuesPK();
		setValues.setValues(incomingRequest, elementForm);
		dbs.delete(elementForm);
		this.setStatus(dbs.getStatus()) ;
		return elementForm ;
	}

}