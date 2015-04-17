/**
 * Created on Feb 17, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCheckBlanketLimits.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoCheckBlanketLimits extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			BigDecimal zero = new BigDecimal(0);
			UserErrors errors = (UserErrors)incomingRequest.get("userErrors");
			if(poHeader.getBlanketLimit().compareTo(zero) == 0 || poHeader.getReleaseLimit().compareTo(zero) == 0)
			{
				if(errors == null){	errors = new UserErrors();	}
				errors.addError(PoErrors.NORELEASELIMIT, PoErrors.CRITICAL);
				incomingRequest.put("userErrors", errors);
				this.setPostAction("exitProcess");
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
