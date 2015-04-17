package com.tsa.puridiom.inspectiondispos.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionDisposDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionDispos inspectionDispos = (InspectionDispos)incomingRequest.get("inspectionDispos");
		if(inspectionDispos == null)
		{
			inspectionDispos = new InspectionDispos();
		}
		InspectionDisposSetValuesPK setValues = new InspectionDisposSetValuesPK();
		setValues.setValues(incomingRequest, inspectionDispos);
		dbs.delete(inspectionDispos);
		this.setStatus(dbs.getStatus()) ;
		return inspectionDispos ;
	}

}