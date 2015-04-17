package com.tsa.puridiom.securitygroup.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SecurityGroupSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			SecurityGroup securityGroup = (SecurityGroup) incomingRequest.get("securityGroup");
			if (securityGroup == null)
			{
				securityGroup = new SecurityGroup();
			}

			if (incomingRequest.containsKey("SecurityGroup_groupId"))
			{
				String groupId = (String ) incomingRequest.get("SecurityGroup_groupId");
				securityGroup.setGroupId(groupId);
			}
			if (incomingRequest.containsKey("SecurityGroup_groupDescription"))
			{
				String groupDescription = (String ) incomingRequest.get("SecurityGroup_groupDescription");
				securityGroup.setGroupDescription(groupDescription);
			}
			if (incomingRequest.containsKey("SecurityGroup_owner"))
			{
				String owner = (String ) incomingRequest.get("SecurityGroup_owner");
				securityGroup.setOwner(owner);
			}
			if (incomingRequest.containsKey("SecurityGroup_status"))
			{
				String status = (String ) incomingRequest.get("SecurityGroup_status");
				securityGroup.setStatus(status);
			}
			if (incomingRequest.containsKey("SecurityGroup_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("SecurityGroup_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				securityGroup.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("SecurityGroup_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("SecurityGroup_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				securityGroup.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("SecurityGroup_idlemin"))
			{
				String idlemin = (String ) incomingRequest.get("SecurityGroup_idlemin");
				securityGroup.setIdlemin(idlemin);
			}

			result = securityGroup;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}