package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.supplierportal.*;
import com.tsa.puridiom.vendorregistration.exception.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class InvoiceLookupPoNumberHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("invoice-lookup-po-number.xml");
			process.executeProcess(incomingRequest);

			String	errorMsg = "";
			if (incomingRequest.containsKey("invalidPoNumberErrorMsg")) {
				errorMsg = (String) incomingRequest.get("invalidPoNumberErrorMsg");
			}

			if (process.getStatus() == Status.COMPLETED || (process.getStatus() == Status.SUCCEEDED && errorMsg.length() > 0))
			{
				incomingRequest.put("viewPage", incomingRequest.get("invalidPoNumberFailurePage"));
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
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			//throw exception;
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