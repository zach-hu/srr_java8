package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.*;
import com.tsagate.properties.DictionaryManager;

import java.util.*;

public class BudgetServiceCheckHandler implements IHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String) incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("budgetservice-check.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", incomingRequest.get("successPage"));
			} else
			{
				if (process.getStatus() == Status.DUPLICATED_REQUEST)
				{
					String organizationId = (String)incomingRequest.get("organizationId");
					String language = (String)incomingRequest.get("language");
					String message = DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, "requestNotValidToken", "This request is in progress. Please do not refresh the page!");
					incomingRequest.put("errorMsg", message);
				}
				
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		} catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		} finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}
}