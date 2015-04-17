/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		try
		{
			incomingRequest.put("PoLine_icPoHeader", incomingRequest.get("PoHeader_icPoHeader")) ;
			incomingRequest.put("Account_icHeader", incomingRequest.get("PoHeader_icPoHeader"));
			incomingRequest.put("Account_icLine", "0");
			incomingRequest.put("Default_referenceType", "POH");

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED) ;
			throw new TsaException(this.getName(), e);
		}
		return null ;
	}
}
