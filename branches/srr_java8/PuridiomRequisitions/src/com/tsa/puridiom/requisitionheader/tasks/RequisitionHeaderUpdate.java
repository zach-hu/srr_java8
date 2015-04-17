package com.tsa.puridiom.requisitionheader.tasks;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionHeaderUpdate extends Task
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
                this.setStatus(Status.FAILED);
				throw new Exception ("RequisitionHeader was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			incomingRequest.put("newHistoryRequisitionHeader", requisitionHeader);
            Log.debug(this, "Updating Requisition: " + requisitionHeader.getRequisitionNumber() +
                    " with status: " + requisitionHeader.getStatus());

			dbs.update(requisitionHeader);
			//requisitionHeader.serializeMe();
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