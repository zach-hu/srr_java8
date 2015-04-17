/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.PuridiomProcess;
import java.util.Map;

/**
 * @author Kelli
 */
public class RfqSaveasHandler extends Handler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
	    try
	    {
			PuridiomProcess process = super.executeProcess(incomingRequest, "rfq-saveas.xml");
			
			super.setViewPage(process, incomingRequest);
	    }
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}

		return incomingRequest;
	}
}
