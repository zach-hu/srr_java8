package com.tsa.puridiom.handlers;

import java.util.*;

public class PrintAssignedPdfRequisitionsHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
				incomingRequest.put("viewPage", incomingRequest.get("report"));
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		}

		return incomingRequest;
	}

}
