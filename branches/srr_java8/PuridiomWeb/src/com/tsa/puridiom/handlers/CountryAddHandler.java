package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class CountryAddHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("country-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("duplicateCountryErrorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("duplicateCountryFailurePage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				    /*
				     *  added the following
				     *  so that after a country record is successfully added
				     *  the user gets returned back to the country browse
				     *  (browseName is declared on the jsp)
				     */
				    process = processLoader.loadProcess("browse-retrieve.xml");
					process.executeProcess(incomingRequest);
				}
			}
			else
			{
				incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
			throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", (String) incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}