package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

public class RequisitionLineUpdateStatusByHeader extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			String	status = (String) incomingRequest.get("RequisitionHeader_status");
			BigDecimal icReqHeader = new BigDecimal(icReqHeaderString);
			String	sql = "update requisition_line set requisition_line.status = ? where " + 
						  "requisition_line.ic_req_header = ?";
			Object [] args = {status, icReqHeader.toString()};
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