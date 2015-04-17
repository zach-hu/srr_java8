package com.tsa.puridiom.handlers;

import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;


public class UserChangePasswordDirectlyHandler implements IHandler
{

	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("user-change-password-directly.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");
				String	reviewProfile = (String) incomingRequest.get("reviewProfile");
				
				if (!Utility.isEmpty(errorMsg))
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
		catch (PasswordException exception)
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
