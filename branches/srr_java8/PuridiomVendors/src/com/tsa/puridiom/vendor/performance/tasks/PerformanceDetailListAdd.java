/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;
import java.util.*;

import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class PerformanceDetailListAdd extends Task
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
				PerformanceDetail rating = (PerformanceDetail)it.next();
				if(!Utility.isEmpty(rating.getComp_id().getEvalSection()))
				{
					incomingRequest.put("performanceDetail", rating);
					PerformanceDetailAdd aTask= new PerformanceDetailAdd();
					aTask.executeTask(incomingRequest);
					if (aTask.getStatus() != Status.SUCCEEDED)
					{
						this.setStatus(Status.FAILED);
						break;
					}
				}
			}
			result = performanceDetailList;
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
