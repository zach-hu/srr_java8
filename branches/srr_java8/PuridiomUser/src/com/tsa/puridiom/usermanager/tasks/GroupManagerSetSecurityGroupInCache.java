/*
 * Created on June 10, 2004
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.entity.SecurityGroup;
import com.tsa.puridiom.usermanager.GroupManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class GroupManagerSetSecurityGroupInCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			SecurityGroup securityGroup = (SecurityGroup) incomingRequest.get("securityGroup");
			
			if (securityGroup != null)
			{
				GroupManager.getInstance().setSecurityGroupInCache(organizationId, securityGroup);
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return null;
		}
	}

}
