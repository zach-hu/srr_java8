/**
 * Created on Feb 17, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCheckReleaseLimit.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class PoCheckReleaseLimit extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
		    UserErrors errors = (UserErrors)incomingRequest.get("userErrors");
			String	organizationId = (String) incomingRequest.get("organizationId");
			BigDecimal releaseAmount = (BigDecimal)incomingRequest.get("releaseAmount");
			Log.debug(this, "Release amount: " + releaseAmount.toString());
			BigDecimal releaseLimit = (BigDecimal)incomingRequest.get("releaseLimit");
			Log.debug(this, "Release limit: " + releaseLimit.toString());
			if(releaseAmount.compareTo(releaseLimit) == 1)
			{
				if(errors == null){	errors = new UserErrors();	}
				errors.addError(PoErrors.getAmtOverRelease(releaseAmount, releaseLimit, organizationId), PoErrors.AMTOVERRELEASECODE);
				incomingRequest.put("userErrors", errors);	
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
