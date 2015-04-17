package com.tsa.puridiom.usergrouprel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class UserGroupRelSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserGroupRelPK comp_id = new UserGroupRelPK();
			UserGroupRel userGroupRel = (UserGroupRel) incomingRequest.get("userGroupRel");
			if (userGroupRel == null)
			{
				userGroupRel = new UserGroupRel();
			}

			if (incomingRequest.containsKey("UserGroupRel_groupId"))
			{
				String groupId = (String ) incomingRequest.get("UserGroupRel_groupId");
				comp_id.setGroupId(groupId);
			}
			if (incomingRequest.containsKey("UserGroupRel_userId"))
			{
				String userId = (String ) incomingRequest.get("UserGroupRel_userId");
				comp_id.setUserId(userId);
			}
			userGroupRel.setComp_id(comp_id);

			result = userGroupRel;
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