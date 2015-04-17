/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;
import java.util.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Administrator
 */
public class PerformanceDetailListUpdate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		Object result= null;
		this.setStatus(Status.SUCCEEDED);
		try
		{
			List performanceDetailList= (List) incomingRequest.get("performanceDetailList");
			Iterator it= performanceDetailList.iterator();
			while (it.hasNext())
			{
				incomingRequest.put("performanceDetail", it.next());
				PerformanceDetailUpdate aTask= new PerformanceDetailUpdate();
				aTask.executeTask(incomingRequest);
				if (aTask.getStatus() != Status.SUCCEEDED)
				{
					this.setStatus(Status.FAILED);
					break;
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
