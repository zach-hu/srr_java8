package com.tsa.puridiom.rules.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RulesDataRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Method[] reqMethods = new RequisitionHeader().getClass().getDeclaredMethods();
			Method[] rfqMethods = new RfqHeader().getClass().getDeclaredMethods();
			Method[] poMethods = new PoHeader().getClass().getDeclaredMethods();

			Map reqMethodsMap = new HashMap();
			for (int i = 0; i < reqMethods.length; i++)
			{
				Method method = reqMethods[i];
				if (method.getName().startsWith("get"))
				{
					reqMethodsMap.put(method.getName(), method);
				}
			}
			incomingRequest.put("reqMethods" ,reqMethodsMap);

			Map rfqMethodsMap = new HashMap();
			for (int i = 0; i < rfqMethods.length; i++)
			{
				Method method = rfqMethods[i];
				if (method.getName().startsWith("get"))
				{
					rfqMethodsMap.put(method.getName(), method);
				}
			}
			incomingRequest.put("rfqMethods" ,rfqMethodsMap);

			Map poMethodsMap = new HashMap();
			for (int i = 0; i < poMethods.length; i++)
			{
				Method method = poMethods[i];
				if (method.getName().startsWith("get"))
				{
					poMethodsMap.put(method.getName(), method);
				}
			}
			incomingRequest.put("poMethods" ,poMethodsMap);

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
