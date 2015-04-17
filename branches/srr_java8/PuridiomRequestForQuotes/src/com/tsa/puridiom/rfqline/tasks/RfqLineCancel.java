package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.sql.Types;
import java.util.Map;

public class RfqLineCancel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RfqLine rfqLine = (RfqLine)incomingRequest.get("rfqLine");
		if (rfqLine == null)
		{
			throw new Exception("RfqLine entity was not found");
		}
		
		String	icRfqLine = String.valueOf(rfqLine.getIcRfqLine());
		String	updateString = "update rfq_line set status = ? where ic_rfq_line = ?";
		Object [] args = {DocumentStatus.CANCELLED, icRfqLine};
		Integer [] types = {Types.VARCHAR, Types.VARCHAR};
		
		dbs.sqlUpdate(updateString, args, types);
		
		this.setStatus(dbs.getStatus()) ;
		return rfqLine ;
	}

}