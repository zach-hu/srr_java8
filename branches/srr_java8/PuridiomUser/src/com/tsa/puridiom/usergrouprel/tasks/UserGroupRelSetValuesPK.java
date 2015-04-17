package com.tsa.puridiom.usergrouprel.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class UserGroupRelSetValuesPK
{
	public void setValues(Map incomingRequest, UserGroupRel usergrouprel ) throws Exception
	{
		try
		{
			String groupId = (String ) incomingRequest.get("UserGroupRel_groupId");
			String userId = (String ) incomingRequest.get("UserGroupRel_userId");
			UserGroupRelPK comp_id = new UserGroupRelPK();
			comp_id.setGroupId(groupId);
			comp_id.setUserId(userId);
			usergrouprel.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}