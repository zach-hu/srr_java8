package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
//import com.tsagate.foundation.utility.Dictionary;

import java.util.*;

public class DisbForwardHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			//PuridiomProcess process = processLoader.loadProcess(Dictionary.getProperty("process", "disbursementforward"));
			PuridiomProcess process = processLoader.loadProcess("disbursement-forward.xml");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
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