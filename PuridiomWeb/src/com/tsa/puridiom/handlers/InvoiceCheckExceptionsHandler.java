package com.tsa.puridiom.handlers;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

public class InvoiceCheckExceptionsHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invoice-check-exceptions.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");
				if (!HiltonUtility.isEmpty(errorMsg))
				{
					incomingRequest.put("viewPage", incomingRequest.get("invoiceExceptionFailurePage"));
					incomingRequest.put("continueHandlers", "N");
				}
				else
				{
					incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				}
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
				incomingRequest.put("continueHandlers", "N");
			}
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
				incomingRequest.put("continueHandlers", "N");
			}
		}
		return incomingRequest;
	}
}