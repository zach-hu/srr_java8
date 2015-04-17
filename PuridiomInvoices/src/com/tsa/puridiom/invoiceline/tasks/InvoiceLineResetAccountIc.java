/*
 * Created on October 26, 2005
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class InvoiceLineResetAccountIc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String originalAccount_icLine = (String) incomingRequest.get("originalAccount_icLine");
			incomingRequest.put("Account_icLine", originalAccount_icLine);
			incomingRequest.put("formType", "IVL");
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
