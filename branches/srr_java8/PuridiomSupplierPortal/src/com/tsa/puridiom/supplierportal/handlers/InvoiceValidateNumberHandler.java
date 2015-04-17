package com.tsa.puridiom.supplierportal.handlers;
import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public class InvoiceValidateNumberHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invoice-validate-number.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("duplicateNumberErrorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("duplicateNumberFailurePage"));
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
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
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