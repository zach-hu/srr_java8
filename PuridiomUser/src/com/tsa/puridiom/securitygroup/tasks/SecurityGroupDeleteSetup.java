package com.tsa.puridiom.securitygroup.tasks;

import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class SecurityGroupDeleteSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String groupId = (String) incomingRequest.get("SecurityGroup_groupId");
		
		incomingRequest.put("SecurityProfile_groupId", groupId);
		incomingRequest.put("UserGroupRel_groupId", groupId);
		incomingRequest.put("UserRole_roleId", groupId);
		
		return null;
	}

}