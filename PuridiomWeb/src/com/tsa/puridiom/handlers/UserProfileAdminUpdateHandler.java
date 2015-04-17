package com.tsa.puridiom.handlers;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.exceptions.InvalidEmailAddressException;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class UserProfileAdminUpdateHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
	    try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("userprofile-admin-update.xml");
			process.executeProcess(incomingRequest);

		    String	errorMsg = (String) incomingRequest.get("errorMsg");

			if (process.getStatus() == Status.SUCCEEDED)
			{
				UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
		    	incomingRequest.put("language", userProfile.getLocale());
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				}
			}
			else
			{
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				}
			}
		}
		catch (PasswordException exception)
		{
		    incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
			incomingRequest.put("continueHandlers", "N");
		}
        catch (InvalidEmailAddressException exception)
        {
            incomingRequest.put("errorMsg", exception.getMessage());
            incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
            incomingRequest.put("continueHandlers", "N");
        }
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			incomingRequest.put("continueHandlers", "N");
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