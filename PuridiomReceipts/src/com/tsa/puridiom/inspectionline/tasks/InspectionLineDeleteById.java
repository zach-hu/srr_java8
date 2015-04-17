package com.tsa.puridiom.inspectionline.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InspectionLineDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		InspectionLine inspectionLine = (InspectionLine)incomingRequest.get("inspectionLine");
//		if(inspectionLine == null)
//		{
//			inspectionLine = new InspectionLine();
//		}
//		InspectionLineSetValuesPK setValues = new InspectionLineSetValuesPK();
//		setValues.setValues(incomingRequest, inspectionLine);
		dbs.delete(inspectionLine);
		this.setStatus(dbs.getStatus()) ;
		return inspectionLine ;
	}

}