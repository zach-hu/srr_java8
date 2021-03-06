package com.tsa.puridiom.rfqheader.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class RfqHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
		if(rfqHeader == null)
		{
			rfqHeader = new RfqHeader();
		}
		RfqHeaderSetValuesPK setValues = new RfqHeaderSetValuesPK();
		setValues.setValues(incomingRequest, rfqHeader);
		dbs.delete(rfqHeader);
		this.setStatus(dbs.getStatus()) ;
		return rfqHeader ;
	}

}