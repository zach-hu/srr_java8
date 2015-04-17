package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.common.documents.DocumentStatus;
import java.util.Map;

public class PoLineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		PoLine poLine = (PoLine)incomingRequest.get("poLine");
		if(poLine == null)
		{
			poLine = new PoLine();
		}
		PoLineSetValuesPK setValues = new PoLineSetValuesPK();
		setValues.setValues(incomingRequest, poLine);

		poLine.setStatus(DocumentStatus.DELETE_INPROGRESS);

		dbs.delete(poLine);
		this.setStatus(dbs.getStatus()) ;
		dbs.getSession().flush() ;

		return poLine ;
	}

}