package com.tsa.puridiom.disbheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Map;

public class DisbHeaderUpdateStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	icDsbHeaderString = (String) incomingRequest.get("DisbHeader_icDsbHeader");
			String	status = (String) incomingRequest.get("DisbHeader_status");
			BigDecimal icDsbHeader = new BigDecimal(icDsbHeaderString);
			String	sql = "update disb_header set disb_header.status = ? where disb_header.ic_dsb_header = ?";
			Object [] args = new Object[2];
			Integer [] types = new Integer[2];
			args[0] = status;
			types[0] = Types.VARCHAR;
			args[1] = icDsbHeader;
			types[1] = Types.DECIMAL;
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