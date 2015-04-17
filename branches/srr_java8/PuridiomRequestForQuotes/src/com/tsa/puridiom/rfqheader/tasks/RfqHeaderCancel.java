package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.sql.Types;
import java.util.Map;

public class RfqHeaderCancel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
		if (rfqHeader == null)
		{
			rfqHeader = new RfqHeader();
		}
		
		String	icRfqHeader = String.valueOf(rfqHeader.getIcRfqHeader());
		String	updateString = "update rfq_header set status = ? where ic_rfq_header = ?";
		Object [] args = {DocumentStatus.CANCELLED, icRfqHeader};
		Integer [] types = {Types.VARCHAR, Types.VARCHAR};
		
		dbs.sqlUpdate(updateString, args, types);
		
		this.setStatus(dbs.getStatus()) ;
		return rfqHeader ;
	}

}