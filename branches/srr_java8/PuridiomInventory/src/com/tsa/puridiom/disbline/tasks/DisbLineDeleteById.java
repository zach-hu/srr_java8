package com.tsa.puridiom.disbline.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class DisbLineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
		if(disbLine == null)
		{
			disbLine = new DisbLine();
		}
		DisbLineSetValuesPK setValues = new DisbLineSetValuesPK();
		setValues.setValues(incomingRequest, disbLine);
		dbs.delete(disbLine);
		this.setStatus(dbs.getStatus()) ;
		return disbLine ;
	}

}