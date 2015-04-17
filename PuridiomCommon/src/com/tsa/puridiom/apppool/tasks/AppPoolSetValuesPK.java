package com.tsa.puridiom.apppool.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;


public class AppPoolSetValuesPK
{
	public void setValues(Map incomingRequest, AppPool apppool ) throws Exception
	{
		try
		{
			String poolid = (String ) incomingRequest.get("AppPool_poolid");
			apppool.setPoolid(poolid);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}