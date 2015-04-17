package com.tsa.puridiom.elementdata.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ElementDataDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ElementData elementData = (ElementData)incomingRequest.get("elementData");
		if(elementData == null)
		{
			elementData = new ElementData();
		}
		ElementDataSetValuesPK setValues = new ElementDataSetValuesPK();
		setValues.setValues(incomingRequest, elementData);
		dbs.delete(elementData);
		this.setStatus(dbs.getStatus()) ;
		return elementData ;
	}

}