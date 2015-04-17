package com.tsa.puridiom.reviewfinalize.tasks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class ReviewFinalizeSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			ReviewFinalize reviewfinalize = (ReviewFinalize) incomingRequest.get("reviewFinalize");
			if (reviewfinalize == null)
			{
				reviewfinalize = new ReviewFinalize();
			}

			if (incomingRequest.containsKey("ReviewFinalize_icReview"))
			{
				String icHeaderString = (String) incomingRequest.get("ReviewFinalize_icReview");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal (icHeaderString);
				reviewfinalize.setIcReview(icHeader);
			}

			if (incomingRequest.containsKey("ReviewFinalize_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("ReviewFinalize_icHeader");
				if (Utility.isEmpty(icHeaderString)){		icHeaderString = "0";		}
				BigDecimal icHeader = new BigDecimal (icHeaderString);
				reviewfinalize.setIcHeader(icHeader);
			}

			if (incomingRequest.containsKey("ReviewFinalize_step"))
			{
				String step = (String ) incomingRequest.get("ReviewFinalize_step");
				reviewfinalize.setStep(step);
			}
			if (incomingRequest.containsKey("ReviewFinalize_owner"))
			{
				String owner = (String ) incomingRequest.get("ReviewFinalize_owner");
				reviewfinalize.setOwner(owner);
			}
			if (incomingRequest.containsKey("ReviewFinalize_revisedBy"))
			{
				String revisedby = (String ) incomingRequest.get("ReviewFinalize_revisedBy");
				reviewfinalize.setRevisedBy(revisedby);
			}
			if (incomingRequest.containsKey("ReviewFinalize_completed"))
			{
				String completed = (String ) incomingRequest.get("ReviewFinalize_completed");
				reviewfinalize.setCompleted(completed);
			}
			if (incomingRequest.containsKey("ReviewFinalize_dateCompleted"))
			{
				String dateCompletedString = (String) incomingRequest.get("ReviewFinalize_dateCompleted");
				Date dateCompleted = null;
				if (!Utility.isEmpty(dateCompletedString))
				{
					dateCompleted = Dates.getDate(dateCompletedString);
				}
				reviewfinalize.setDateCompleted(dateCompleted);
			}
			if (incomingRequest.containsKey("ReviewFinalize_notes"))
			{
				String notes = (String ) incomingRequest.get("ReviewFinalize_notes");
				reviewfinalize.setNotes(notes);
			}

			result = reviewfinalize;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
