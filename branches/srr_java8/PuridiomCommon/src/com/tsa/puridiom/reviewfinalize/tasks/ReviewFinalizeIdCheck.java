package com.tsa.puridiom.reviewfinalize.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class ReviewFinalizeIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		BigDecimal icHeader = new BigDecimal(incomingRequest.get("ReviewFinalize_icHeader").toString());

		if(Utility.isObjectEmpty(icHeader))
		{
				System.out.println("IdCheck: icHeader is Empty");
				this.setStatus(Status.FAILED);
				throw new TsaException("ReviewFinalize id is necessary to retrieve a RetrieveFinalize");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;
	}
}