/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.*;

/**
 * @author Administrator
 */
public class PerformanceDetailLoadFromRequest extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List performanceDetailList = new ArrayList();
			if (incomingRequest.containsKey("PerformanceDetail_icPoHeader"))
			{
				Object perfDetailObj = incomingRequest.get("PerformanceDetail_evaluator");
				Set keySet = incomingRequest.keySet();
				String PerformanceDetail_icPoHeader = (String)incomingRequest.get("PerformanceDetail_icPoHeader");
				if (perfDetailObj instanceof String[])
				{
					int	arraySize = ((String[]) perfDetailObj).length;

					for (int i=0; i < arraySize; i++)
					{
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("vendor-performance-set-values-from-request.xml");
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("PerformanceDetail_icPoHeader", PerformanceDetail_icPoHeader);
						Iterator iterator = keySet.iterator();
						while (iterator.hasNext())
						{
							String key = (String) iterator.next();
							if (key.indexOf("PerformanceDetail_") == 0)
							{
								Object obj =  incomingRequest.get(key);
								if (obj instanceof String[])
								{
									String arrayObj[] = (String[]) obj;
									updateParameters.put(key, arrayObj[i]);
								} else {
                                    updateParameters.put(key, obj);
                                }
							}
						}
						process.executeProcess(updateParameters);
						if(process.getStatus() == Status.SUCCEEDED)
						{
							performanceDetailList.add(updateParameters.get("performanceDetail"));
						}
					}
				}
				else
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("vendor-performance-set-values-from-request.xml");
					Map updateParameters = new HashMap();
					updateParameters.put("userId", incomingRequest.get("userId"));
					updateParameters.put("organizationId", incomingRequest.get("organizationId"));
					updateParameters.put("dbsession", incomingRequest.get("dbsession"));
					updateParameters.put("PerformanceDetail_icPoHeader", PerformanceDetail_icPoHeader);
					Iterator iterator = keySet.iterator();
					while (iterator.hasNext())
					{
						String key = (String) iterator.next();
						if (key.indexOf("PerformanceDetail_") == 0)
						{
							Object obj =  incomingRequest.get(key);
							updateParameters.put(key, obj);
						}
					}
					process.executeProcess(updateParameters);
					if(process.getStatus() == Status.SUCCEEDED)
					{
						performanceDetailList.add(updateParameters.get("performanceDetail"));
					}
				}
			}
			else
			{
				this.setStatus(Status.FAILED);
				throw new Exception("The value for PerformanceDetail_icHeader was not found.");
			}
			result = performanceDetailList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
