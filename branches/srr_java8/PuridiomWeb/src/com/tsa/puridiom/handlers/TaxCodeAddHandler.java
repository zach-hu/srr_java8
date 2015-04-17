package com.tsa.puridiom.handlers;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class TaxCodeAddHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("taxcode-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("errorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				    /*
				     *  kathleen added the following on 06-13-05
				     *  so that after a tax code is successfully added
				     *  the user gets returned back to the tax code browse
				     */
				    process = processLoader.loadProcess("browse-retrieve.xml");
					process.executeProcess(incomingRequest);
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