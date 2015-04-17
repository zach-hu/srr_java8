package com.tsa.puridiom.rfqline.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;

public class RfqLineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RfqLine rfqLine = (RfqLine)incomingRequest.get("rfqLine");
		if(rfqLine == null)
		{
			rfqLine = new RfqLine();
		}
		RfqLineSetValuesPK setValues = new RfqLineSetValuesPK();
		setValues.setValues(incomingRequest, rfqLine);
		
		rfqLine.setStatus(DocumentStatus.DELETE_INPROGRESS);
		
		dbs.delete(rfqLine);
		this.setStatus(dbs.getStatus()) ;
		return rfqLine ;
	}

}