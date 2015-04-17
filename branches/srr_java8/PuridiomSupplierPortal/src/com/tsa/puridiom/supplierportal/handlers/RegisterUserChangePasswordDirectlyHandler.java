package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsa.puridiom.exceptions.PasswordException;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

public class RegisterUserChangePasswordDirectlyHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("bidboarduser-change-password-directly.xml");
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");

				if (!Utility.isEmpty(errorMsg))
				{
					incomingRequest.put("viewPage", incomingRequest.get("resetFailurePage"));
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
			incomingRequest.put("continueHandlers", "N");
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			incomingRequest.put("continueHandlers", "N");
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