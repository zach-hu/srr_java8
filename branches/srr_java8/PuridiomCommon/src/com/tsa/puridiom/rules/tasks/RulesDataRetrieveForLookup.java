package com.tsa.puridiom.rules.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RulesDataRetrieveForLookup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String lookupTable = (String) incomingRequest.get("lookupTable");
			if (!HiltonUtility.isEmpty(lookupTable))
			{
				Class lookupClass = Class.forName("com.tsa.puridiom.entity." + lookupTable);
				Method[] lookupMethods = lookupClass.getDeclaredMethods();

				Map lookupMethodsMap = new HashMap();
				for (int i = 0; i < lookupMethods.length; i++)
				{
					Method method = lookupMethods[i];
					if (method.getName().startsWith("get"))
					{
						lookupMethodsMap.put(method.getName(), method);
					}
				}
				incomingRequest.put("lookupMethods" ,lookupMethodsMap);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
