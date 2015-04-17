package com.tsa.puridiom.usergrouprel.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class UserGroupRelDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		UserGroupRel userGroupRel = (UserGroupRel)incomingRequest.get("userGroupRel");
		if(userGroupRel == null)
		{
			userGroupRel = new UserGroupRel();
		}
		UserGroupRelSetValuesPK setValues = new UserGroupRelSetValuesPK();
		setValues.setValues(incomingRequest, userGroupRel);
		dbs.delete(userGroupRel);
		this.setStatus(dbs.getStatus()) ;
		return userGroupRel ;
	}

}