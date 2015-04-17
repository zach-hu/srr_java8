package com.tsa.puridiom.securitygroup.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;
import java.util.Map;

public class SecurityGroupDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		SecurityGroup securityGroup = (SecurityGroup)incomingRequest.get("securityGroup");
		if(securityGroup == null)
		{
			securityGroup = new SecurityGroup();
		}
		SecurityGroupSetValuesPK setValues = new SecurityGroupSetValuesPK();
		setValues.setValues(incomingRequest, securityGroup);
		dbs.delete(securityGroup);
		this.setStatus(dbs.getStatus()) ;
		return securityGroup ;
	}

}