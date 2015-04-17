package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
			if (requisitionLine == null)
			{
				throw new Exception ("RequisitionLine was not found.");
			}
			
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			/*RequisitionLineHistory history = new RequisitionLineHistory();
			incomingRequest.put("newHistoryRequisitionLine", requisitionLine);
			history.executeTask(incomingRequest);
			if(history.getStatus() == Status.SUCCEEDED)
			{*/
			    dbs.add(requisitionLine);
			/*}
			else
			{
			    this.setStatus(history.getStatus());
			    throw new TsaException(this.getName() + ", An error occurred updating history! ");
			}
				*/
			result = requisitionLine;
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