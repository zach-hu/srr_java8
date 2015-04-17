package com.tsa.puridiom.securityprofile.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class SecurityProfileDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		SecurityProfile securityProfile = (SecurityProfile)incomingRequest.get("securityProfile");
		if(securityProfile == null)
		{
			securityProfile = new SecurityProfile();
		}
		SecurityProfileSetValuesPK setValues = new SecurityProfileSetValuesPK();
		setValues.setValues(incomingRequest, securityProfile);
		dbs.delete(securityProfile);
		this.setStatus(dbs.getStatus()) ;
		return securityProfile ;
	}

}