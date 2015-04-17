package com.tsa.puridiom.requisitionline.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class RequisitionLineDelete extends Task{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
		if(requisitionLine == null)
		{
			requisitionLine = new RequisitionLine();
		}
		RequisitionLineSetValuesPK setValues = new RequisitionLineSetValuesPK();
		setValues.setValues(incomingRequest, requisitionLine);
		dbs.delete(requisitionLine);
		this.setStatus(dbs.getStatus()) ;
		return requisitionLine ;
	}

}