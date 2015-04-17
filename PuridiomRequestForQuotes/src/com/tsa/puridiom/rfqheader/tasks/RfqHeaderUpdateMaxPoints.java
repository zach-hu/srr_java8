package com.tsa.puridiom.rfqheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.sql.Types;
import java.util.Map;

public class RfqHeaderUpdateMaxPoints extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
			
			if (Utility.isEmpty(icRfqHeaderString)) {
			    icRfqHeaderString = (String) incomingRequest.get("RfqQuestion_icRfqHeader");
			}
			if (!Utility.isEmpty(icRfqHeaderString)) {
				String	maxPoints = (String) incomingRequest.get("RfqHeader_maxPoints");
				String	sql = "update rfq_header set rfq_header.max_points = ? where rfq_header.ic_rfq_header = ?";
				Object [] args = {maxPoints, icRfqHeaderString};
				Integer [] types = {Types.VARCHAR, Types.VARCHAR};
			
				this.setStatus(dbs.sqlUpdate(sql, args, types)) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}

}