package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli Knisely
 */
public class UserLogoffHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{	
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("user-logoff.xml");
			
			process.executeProcess(incomingRequest);

			incomingRequest.put("userId", "");
			incomingRequest.put("organizationId", "");
			
			String errorMsg = (String) incomingRequest.get("errorMsg");
			if (errorMsg != null && errorMsg.trim().length() > 0)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
			else
			{
				incomingRequest.put("logOff", "true");
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
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
			return incomingRequest;
		}
	}
}
