package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionHeaderGetTaxCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			
			if (requisitionHeader != null) {
			    result = requisitionHeader.getTaxCode();
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}