package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.exceptions.PasswordException;
import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class VendorRegistrationHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("vendor-registration.xml");
			process.executeProcess(incomingRequest);

			String	errorMsg = "";
			if (incomingRequest.containsKey("errorMsg")) {
				errorMsg = (String) incomingRequest.get("errorMsg");
			}

			if (process.getStatus() == Status.COMPLETED || (process.getStatus() == Status.SUCCEEDED && errorMsg.length() > 0))
			{
				incomingRequest.put("viewPage", incomingRequest.get("registrationStatusPage"));
			}
			else if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			}
			else if (errorMsg.length() > 0)
            {
                incomingRequest.put("registrationErrorMsg", errorMsg);
                incomingRequest.put("viewPage", incomingRequest.get("registrationPage"));
				incomingRequest.put("continueHandlers", "N");
            }
            else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				incomingRequest.put("continueHandlers", "N");
			}
		}
		catch (PasswordException exception)
		{
		    incomingRequest.put("passwordErrorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("registrationPage"));
			incomingRequest.put("continueHandlers", "N");
		}
		catch (RegistrationException exception)
		{
			incomingRequest.put("registrationErrorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("registrationPage"));
			incomingRequest.put("continueHandlers", "N");
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			incomingRequest.put("continueHandlers", "N");
			//throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				incomingRequest.put("continueHandlers", "N");
			}
		}
		return incomingRequest;
	}

}