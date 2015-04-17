package com.tsa.puridiom.securitygroup.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SecurityGroupSetValuesPK
{
	public void setValues(Map incomingRequest, SecurityGroup securitygroup ) throws Exception
	{
		try
		{
			String groupId = (String ) incomingRequest.get("SecurityGroup_groupId");
			securitygroup.setGroupId(groupId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}