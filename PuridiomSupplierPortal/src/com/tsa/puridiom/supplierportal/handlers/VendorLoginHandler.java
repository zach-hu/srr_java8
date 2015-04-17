package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.*;

public class VendorLoginHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("vendor-login.xml");
			process.executeProcess(incomingRequest);

			String	errorMsg = Utility.ckNull((String) incomingRequest.get("errorMsg"));
			String	warningPassword = Utility.ckNull((String) incomingRequest.get("warningPassword"));
			String	resetPassword = Utility.ckNull((String) incomingRequest.get("resetPassword"));
			//String	finalLoginAttempt = Utility.ckNull((String) incomingRequest.get("finalLoginAttempt"));
			String	lockedOut = Utility.ckNull((String) incomingRequest.get("lockedOut"));

			if (resetPassword.equals("true"))
			{
			    String	passwordResetPage = (String) incomingRequest.get("passwordResetPage");
				incomingRequest.put("viewPage", passwordResetPage);
			}
			else if (warningPassword.equals("true"))
			{
			    String	warningPage = (String) incomingRequest.get("warningPage");
			    if (warningPage == null)
			    	warningPage = "menu/main_menu.jsp";
				incomingRequest.put("viewPage", warningPage);
			}
			/*else if (finalLoginAttempt.equals("true"))
			{
				incomingRequest.put("viewPage", incomingRequest.get("lockedPage"));
			}*/
			else if (lockedOut.equals("true"))
			{
				incomingRequest.put("viewPage", incomingRequest.get("lockedPage"));
			}
			else if (!Utility.isEmpty(errorMsg))
			{
				incomingRequest.put("viewPage", incomingRequest.get("loginFailurePage"));
			}
			else if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (RegistrationException exception)
		{
			incomingRequest.put("registrationErrorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("registrationPage"));
			throw exception;
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
//			throw exception;
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