package com.tsa.puridiom.handlers;

import com.tsa.puridiom.exceptions.PasswordSecurityException;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author Kelli Knisely
 */
public class UserProfilePasswordHelpHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("userprofile-passwordhelp.xml");
			process.executeProcess(incomingRequest);
			
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");
				String	verificationRequired = (String) incomingRequest.get("verificationRequired");
				String	returnToLogin = (String) incomingRequest.get("returnToLogin");
			    
				if (returnToLogin != null && returnToLogin.equals("true"))
				{
				    incomingRequest.put("viewPage", incomingRequest.get("loginPage"));
				}
			    else if (verificationRequired != null && verificationRequired.equals("true"))
			    {
			        incomingRequest.put("viewPage", incomingRequest.get("userVerificationPage"));
			    }
			    else
			    {
					if (errorMsg != null && errorMsg.trim().length() > 0)
					{
						incomingRequest.put("viewPage", incomingRequest.get("resetFailurePage"));
					}
					else
					{
						incomingRequest.put("viewPage", incomingRequest.get("successPage"));
					}
			    }
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (PasswordSecurityException exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("resetFailurePage"));
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
