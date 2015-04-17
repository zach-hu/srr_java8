package com.tsa.puridiom.inspectiondispos.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InspectionDisposUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InspectionDispos inspectionDispos = (InspectionDispos)incomingRequest.get("inspectionDispos");
			if (inspectionDispos == null)
			{
				throw new Exception ("InspectionDispos was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(inspectionDispos);
		
			result = inspectionDispos;
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