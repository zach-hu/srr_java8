/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.vendor.performance.tasks;
import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.documents.SupplierPerformanceRatings;
import com.tsa.puridiom.entity.PerformanceDetail;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class RatingByOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		Object result= null;
		this.setStatus(Status.SUCCEEDED);
		try
		{
			List performanceDetailList= (List) incomingRequest.get("performanceDetailList");
			BigDecimal ratingTotal = new BigDecimal(0);
			int catRated = 0;
			boolean rated = false;
			for(int i =0; i < performanceDetailList.size(); i++)
			{
				PerformanceDetail rating = (PerformanceDetail)performanceDetailList.get(i);
				if(rating.getEvalRating().compareTo(new BigDecimal(9)) != 0)
				{
					if(rating.getEvalWeight().compareTo(new BigDecimal(0)) > 0)
					{
						ratingTotal = ratingTotal.add(SupplierPerformanceRatings.calculateRating(rating.getEvalRating()).multiply(rating.getEvalWeight()));
					}
					else
					{
						ratingTotal = ratingTotal.add(rating.getEvalRating());
					}

					rated = true;
				}
			}
			if(rated)
			{
				incomingRequest.put("PoHeader_rated", "Y");
			}
		//	result = ratingTotal.divide(new BigDecimal(catRated), BigDecimal.ROUND_HALF_UP);
			result = ratingTotal;
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
