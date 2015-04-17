package com.tsa.puridiom.noncapitalentitydepartment;

import com.tsa.puridiom.entity.NonCapitalEntityDepartment;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class NonCapitalEntityDepartmentRetrieveById extends Task{
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