package com.tsa.puridiom.handlers;

import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli Knisely
 */
public class UserProfilePasswordHelpRetrieveHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("userprofile-passwordhelp-retrieve.xml");
			process.executeProcess(incomingRequest);
			
			String	errorMsg = (String) incomingRequest.get("errorMsg");

			if (errorMsg != null && errorMsg.trim().length() > 0)
			{
			    String	returnToLogin = (String) incomingRequest.get("returnToLogin");
			    if (returnToLogin != null && returnToLogin.equals("true"))
				{
					incomingRequest.put("viewPage", incomingRequest.get("loginPage"));
				}
			    else
			    {
			        incomingRequest.put("viewPage", incomingRequest.get("resetFailurePage"));
			    }
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

			exception.printStackTrace();
			throw exception;
		}
		finally
		{
			return incomingRequest;
		}
	}
}
