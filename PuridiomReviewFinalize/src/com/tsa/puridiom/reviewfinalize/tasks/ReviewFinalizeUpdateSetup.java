
 package com.tsa.puridiom.reviewfinalize.tasks;

import java.util.*;

import org.hibernate.exception.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator
 */
public class ReviewFinalizeUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{

			String		icHeader = (String) incomingRequest.get("ReviewFinalize_icHeader") ;
			if (Utility.isEmpty(icHeader))
			{
					this.setStatus(Status.FAILED);
					throw new TsaException("IC_HEADER was not found!");
			}
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("ReviewFinalize_icReview", ukg.getUniqueKey().toString());
			incomingRequest.put("ReviewFinalize_icHeader", icHeader) ;

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ReviewFinalize Failed. " + e.getMessage(), e);
		}

		return null  ;
	}

}
