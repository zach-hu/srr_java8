package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.requisitionheader.history.tasks.RequisitionHeaderHistory;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.Map;

public class RequisitionHeaderAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			if (requisitionHeader == null)
			{
				throw new Exception ("RequisitionHeader was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			//RequisitionHeaderHistory history = new RequisitionHeaderHistory();
			//incomingRequest.put("newHistoryRequisitionHeader", requisitionHeader);
			
			//history.executeTask(incomingRequest);
			//if(history.getStatus() == Status.SUCCEEDED)
			//{
			    dbs.add(requisitionHeader);
			/*}
			else
			{
			    this.setStatus(history.getStatus());
			    throw new TsaException(this.getName() + ", An error occurred updating history! ");
			}
			*/
			result = requisitionHeader;
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