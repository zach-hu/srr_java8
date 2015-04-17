package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.sql.Types;
import java.util.Map;

public class RequisitionLineUpdateStatusById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icReqLine = (String) incomingRequest.get("RequisitionLine_icReqLine");
			String	status = (String) incomingRequest.get("RequisitionLine_status");
			String	sql = "update requisition_line set requisition_line.status = ? where " +
						  "requisition_line.ic_req_line = ?";
			Object [] args = {status, icReqLine};
			Integer [] types = {Types.VARCHAR, Types.VARCHAR};
		
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