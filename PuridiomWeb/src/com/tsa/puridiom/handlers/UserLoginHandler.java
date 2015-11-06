package com.tsa.puridiom.handlers;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

/**
 * @author Kelli Knisely
 */
public class UserLoginHandler implements IHandler
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.handlers.IHandler#handleRequest(java.util.Map)
	 */
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			String password = (String) incomingRequest.get("password");
			if (Utility.isEmpty(password)) {
				Exception ex = new Exception();
				throw ex;
			}
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("user-login.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");
				String	resetPassword = Utility.ckNull((String) incomingRequest.get("resetPassword"));
				String	setSecurityQuestion = Utility.ckNull((String) incomingRequest.get("setSecurityQuestion"));
				String	reviewProfile = (String) incomingRequest.get("reviewProfile");
				String	finalLoginAttempt = Utility.ckNull((String) incomingRequest.get("finalLoginAttempt"));
				UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");

				if (userProfile != null)
				{
					String	userId = userProfile.getUserId();
					String	organizationId = userProfile.getOrganizationId();

					incomingRequest.put("userId", userId);
					incomingRequest.put("organizationId", organizationId);
					incomingRequest.put("language", userProfile.getLocale());
				}

				if (resetPassword.equals("true") && setSecurityQuestion.equals("true"))
				{
					incomingRequest.put("viewPage", incomingRequest.get("passwordSecurityResetPage"));
				}
				else if (setSecurityQuestion.equals("true"))
				{
					incomingRequest.put("viewPage", incomingRequest.get("securityResetPage"));
				}
				else if (resetPassword.equals("true"))
				{
					incomingRequest.put("viewPage", incomingRequest.get("passwordResetPage"));
				}
				else if (finalLoginAttempt.equals("true"))
				{
					incomingRequest.put("viewPage", incomingRequest.get("finalAttemptPage"));
				}
				else if (errorMsg != null && errorMsg.trim().length() > 0)
				{
				    if (Utility.ckNull(reviewProfile).equals("true")) {
				        incomingRequest.put("viewPage", incomingRequest.get("reviewProfilePage"));
				    } else {
				        incomingRequest.put("viewPage", incomingRequest.get("loginFailurePage"));
						incomingRequest.put("userId", "");
						incomingRequest.put("organizationId", "");
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
			if (!incomingRequest.get("viewPage").equals(incomingRequest.get("successPage"))) {
				incomingRequest.put("continueHandlers", "N");
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			incomingRequest.put("continueHandlers", "N");
			throw exception;
		}
		finally
		{
			return incomingRequest;
		}
	}
}
