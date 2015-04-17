package com.tsa.puridiom.noncapitalentitydepartment;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class NonCapitalEntityDepartmentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		NonCapitalEntityDepartment nonCapitalEntityDepartment = (NonCapitalEntityDepartment)incomingRequest.get("nonCapitalEntityDepartment");
		if(nonCapitalEntityDepartment == null)
		{
			nonCapitalEntityDepartment = new NonCapitalEntityDepartment();
		}
		NonCapitalEntityDepartmentSetValuesPK setValues = new NonCapitalEntityDepartmentSetValuesPK();
		setValues.setValues(incomingRequest, nonCapitalEntityDepartment);
		dbs.delete(nonCapitalEntityDepartment);
		this.setStatus(dbs.getStatus()) ;
		return nonCapitalEntityDepartment ;
	}

}