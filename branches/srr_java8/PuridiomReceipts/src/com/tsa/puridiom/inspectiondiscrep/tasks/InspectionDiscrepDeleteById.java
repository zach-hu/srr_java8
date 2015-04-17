package com.tsa.puridiom.inspectiondiscrep.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionDiscrepDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionDiscrep inspectionDiscrep = (InspectionDiscrep)incomingRequest.get("inspectionDiscrep");
		if(inspectionDiscrep == null)
		{
			inspectionDiscrep = new InspectionDiscrep();
		}
		InspectionDiscrepSetValuesPK setValues = new InspectionDiscrepSetValuesPK();
		setValues.setValues(incomingRequest, inspectionDiscrep);
		dbs.delete(inspectionDiscrep);
		this.setStatus(dbs.getStatus()) ;
		return inspectionDiscrep ;
	}

}