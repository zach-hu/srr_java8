/*
 * Created on July 13, 2007
 */
package com.tsa.puridiom.vendor.performance.tasks;

import java.util.*;

import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Kelli
 */
public class PerformanceDetailListEmailEvaluators extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		Object result= null;
		this.setStatus(Status.SUCCEEDED);
		try
		{
			List ratingsList= (List) incomingRequest.get("ratingsList");
            Map evaluators = new HashMap();
			Iterator it= ratingsList.iterator();
			while (it.hasNext())
			{
				PerformanceDetail rating = (PerformanceDetail)it.next();
                String evaluator = rating.getEvaluator();
				if(!Utility.isEmpty(rating.getComp_id().getEvalSection()) && !Utility.isEmpty(evaluator) && ! evaluators.containsKey(evaluator))
				{
					incomingRequest.put("performanceDetail", rating);
					QueueAddVendorEvaluationEmail aTask= new QueueAddVendorEvaluationEmail();
					aTask.executeTask(incomingRequest);
					if (aTask.getStatus() != Status.SUCCEEDED)
					{
						this.setStatus(Status.FAILED);
						break;
					}
                    evaluators.put(evaluator, "true");
				}
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
