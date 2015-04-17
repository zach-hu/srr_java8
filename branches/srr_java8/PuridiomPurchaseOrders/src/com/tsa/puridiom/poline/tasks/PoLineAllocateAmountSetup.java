/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.poline.tasks;

import java.util.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Administrator 
 */
public class PoLineAllocateAmountSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			String icHeader = (String) incomingRequest.get("Account_icHeader");
			String icLine = (String) incomingRequest.get("Account_icLine");
			incomingRequest.put("PoHeader_icPoHeader", icHeader);
			incomingRequest.put("PoHeader_icPoLine", icLine);
			incomingRequest.put("PoLine_icPoLine", icLine);
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
