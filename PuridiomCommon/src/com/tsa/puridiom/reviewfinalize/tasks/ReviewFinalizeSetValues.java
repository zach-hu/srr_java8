package com.tsa.puridiom.reviewfinalize.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.AddressPK;
import com.tsa.puridiom.entity.ReviewFinalize;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class ReviewFinalizeSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		System.out.println("dentro del executeTask");
		Map incomingRequest = (Map)object;
		Object result = null;
		System.out.println("antes de try");

		try
		{

			ReviewFinalize reviewfinalize = (ReviewFinalize) incomingRequest.get("reviewfinalize");
			if (reviewfinalize == null)
			{
				reviewfinalize = new ReviewFinalize();
				System.out.println("nuevo reviewfinalize");
			}

			if (incomingRequest.containsKey("icHeader"))
			{
				System.out.println("incoming contiene icHeader");
				String icHeaderString = (String) incomingRequest.get("icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal (icHeaderString);
				System.out.println("icHeader "+icHeader);
				reviewfinalize.setIcHeader(icHeader);
			}

			if (incomingRequest.containsKey("step"))
			{
				String step = (String ) incomingRequest.get("step");
				reviewfinalize.setStep(step);
			}
			if (incomingRequest.containsKey("owner"))
			{
				String owner = (String ) incomingRequest.get("owner");
				reviewfinalize.setOwner(owner);
			}
			if (incomingRequest.containsKey("revisedBy"))
			{
				String revisedby = (String ) incomingRequest.get("revisedBy");
				reviewfinalize.setRevisedBy(revisedby);
			}
			if (incomingRequest.containsKey("completed"))
			{
				String completed = (String ) incomingRequest.get("completed");
				reviewfinalize.setCompleted(completed);
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
