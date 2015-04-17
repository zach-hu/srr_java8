package com.tsa.puridiom.bomreference.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class BomReferenceDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BomReference bomReference = (BomReference)incomingRequest.get("bomReference");
		if(bomReference == null)
		{
			bomReference = new BomReference();
		}
		BomReferenceSetValuesPK setValues = new BomReferenceSetValuesPK();
		setValues.setValues(incomingRequest, bomReference);
		dbs.delete(bomReference);
		this.setStatus(dbs.getStatus()) ;
		return bomReference ;
	}

}