/**
 * Created on Mar 2, 2004
 * @author renzo
 * project: HiltonRequestForQuotes
 * com.tsa.puridiom.rfqheader.tasks.RfqHeaderUpdateAwardDateSetup.java
 *
 */
package com.tsa.puridiom.rfqheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqHeaderUpdateAwardDateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			RfqLine rfqLine = (RfqLine)incomingRequest.get("rfqLine");
			ret = rfqLine.getIcRfqHeader().toString();
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		
		return ret;
	}

}
