/*
 * Created on Nov 26, 2003
 * @author renzo
 * project: TSACommon
 * com.tsagate.common.utilityEntityUtility.java
 */
 
package com.tsagate.foundation.utility;

import java.lang.reflect.Method;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.properties.DictionaryManager;


public class EntityUtility
{
	public static int executeProcess(String processName, Map incomingRequest) throws Exception
	{
		PuridiomProcess process = null;
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			String	organizationId = (String) incomingRequest.get("organizationId");
			if(processName.indexOf(".xml") <= 0)
			{
				processName = processName + ".xml";
			}

			process = processLoader.loadProcess(DictionaryManager.getInstance("host", organizationId).getProperty(processName, processName));
			process.executeProcess(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(process, e.toString());
			throw e;
		}
		return process.getStatus();
	}
	/**
	 * getKey
	 * @param columnName
	 * @param element
	 */
	public static Object invokeGet(String columnName, Object element)
	{
		Object obj = null;
		try
		{
			Method methods[] = element.getClass().getMethods();
			//columnName = "get" + columnName;
			boolean methodFound = false;
			Method method = null;
			for (int i = 0; i < methods.length; i++)
			{
				String methodName = methods[i].getName();
				if(methodName.equalsIgnoreCase("get" + columnName))
				{
					methodFound = true;
					method = methods[i];
					i = methods.length;
				}
			}
			//if the method was not found then it is on the pk.
			if(!methodFound)
			{
				method = element.getClass().getMethod("getComp_id", null);
				Object pk = method.invoke(element, null);
				obj = EntityUtility.invokeGet(columnName, pk);
			}
			else
			{
				obj = method.invoke(element, null);
			}
		}
		catch (Exception e)
		{
			Log.error(new Sort(), e.toString());
		}
		return obj;
	}
}
