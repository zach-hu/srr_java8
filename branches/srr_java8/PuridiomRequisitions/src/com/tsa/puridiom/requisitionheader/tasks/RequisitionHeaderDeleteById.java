package com.tsa.puridiom.requisitionheader.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class RequisitionHeaderDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
		if(requisitionHeader == null)
		{
			requisitionHeader = new RequisitionHeader();
		}
		RequisitionHeaderSetValuesPK setValues = new RequisitionHeaderSetValuesPK();
		setValues.setValues(incomingRequest, requisitionHeader);
		dbs.delete(requisitionHeader);
		this.setStatus(dbs.getStatus()) ;
		return requisitionHeader ;
	}

}