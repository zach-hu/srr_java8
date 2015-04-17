package com.tsa.puridiom.handlers;

import com.tsa.puridiom.exceptions.PasswordSecurityException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

/**
 * @author Kelli Knisely
 */
public class UserChangeSecurityProfileHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("user-change-security-profile.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");
				String	reviewProfile = (String) incomingRequest.get("reviewProfile");
				
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
				    if (Utility.ckNull(reviewProfile).equals("true")) {
				        incomingRequest.put("viewPage", incomingRequest.get("reviewProfilePage"));
				    } else {
				        incomingRequest.put("viewPage", incomingRequest.get("resetFailurePage"));
				    }
				}
				else
				{
					incomingRequest.put("viewPage", incomingRequest.get("successPage"));
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
