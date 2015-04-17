package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.sql.Types;
import java.util.Map;

public class RfqLineUpdateStatusByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icRfqHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			String	status = (String) incomingRequest.get("RfqHeader_status");
			String	sql = "update rfq_line set rfq_line.status = ? where rfq_line.ic_rfq_header = ? AND " +
						  "rfq_line.status <> ?";
			Object [] args = {status, icRfqHeader, DocumentStatus.HISTORY};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		
			this.setStatus(dbs.sqlUpdate(sql, args, types)) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}