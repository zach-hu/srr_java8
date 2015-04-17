package com.tsa.puridiom.apppooluser.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;

public class AppPooluserSetValuesPK
{
	public void setValues(Map incomingRequest, AppPooluser apppooluser ) throws Exception
	{
		try
		{
			String poolid = (String ) incomingRequest.get("AppPooluser_poolid");
			String userId = (String ) incomingRequest.get("AppPooluser_userId");
			AppPooluserPK comp_id = new AppPooluserPK();
			comp_id.setPoolid(poolid);
			comp_id.setUserId(userId);
			apppooluser.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}