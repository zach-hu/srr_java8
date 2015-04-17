package com.tsa.puridiom.reviewfinalize.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReviewFinalizeService extends Task
{
	public static boolean isStepChecked(List reviewFinalizeList, String step)
	{
		if(reviewFinalizeList == null){		return false;		}
		for(int i = 0; i < reviewFinalizeList.size(); i++)
		{
			ReviewFinalize reviewFinalize = (ReviewFinalize)reviewFinalizeList.get(i);
			if(step.equalsIgnoreCase(reviewFinalize.getStep()))
			{
				if(reviewFinalize.getCompleted().equalsIgnoreCase("Y")){		return true;		}
			}
		}
		return false;
	}

	public static ReviewFinalize getReviewFinalize(List reviewFinalizeList, String step)
	{
		if(reviewFinalizeList == null){		return null;		}
		for(int i = 0; i < reviewFinalizeList.size(); i++)
		{
			ReviewFinalize reviewFinalize = (ReviewFinalize)reviewFinalizeList.get(i);
			if(step.equalsIgnoreCase(reviewFinalize.getStep())){		return reviewFinalize;		}
		}

		return new ReviewFinalize();

	}

	public static boolean isCompleted(List reviewFinalizeList)
	{
		boolean completed = true;
		if(reviewFinalizeList == null || reviewFinalizeList.size() < 1){		return false;		}
		for(int i = 0; i < reviewFinalizeList.size(); i++)
		{
			ReviewFinalize reviewFinalize = (ReviewFinalize)reviewFinalizeList.get(i);
			if(!reviewFinalize.isReviewCompleted())
			{
				completed = false;
				i = reviewFinalizeList.size();
			}
		}
		return completed;
	}
	public static String getNotes(List reviewFinalizeList, String step)
	{
		if(reviewFinalizeList == null){		return "";		}
		for(int i = 0; i < reviewFinalizeList.size(); i++)
		{
			ReviewFinalize reviewFinalize = (ReviewFinalize)reviewFinalizeList.get(i);

			if(step.equalsIgnoreCase(reviewFinalize.getStep()))
			{
				return reviewFinalize.getNotes();
			}
		}

		return "";
	}

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{


			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return result;
	}
}
