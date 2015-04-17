package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class LabelsAddRefreshHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("labels-add-refresh.xml");
			process.executeProcess(incomingRequest);
			
			String	errorMsg = (String) incomingRequest.get("errorMsg");
			if (errorMsg != null && errorMsg.trim().length() > 0)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
			else
			{
				if (process.getStatus() == Status.SUCCEEDED)
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				}
				else
				{
					incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				}
			}
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