package com.tsa.puridiom.inspectiondiscrep.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InspectionDiscrepUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InspectionDiscrep inspectionDiscrep = (InspectionDiscrep)incomingRequest.get("inspectionDiscrep");
			if (inspectionDiscrep == null)
			{
				throw new Exception ("InspectionDiscrep was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(inspectionDiscrep);

			result = inspectionDiscrep;
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