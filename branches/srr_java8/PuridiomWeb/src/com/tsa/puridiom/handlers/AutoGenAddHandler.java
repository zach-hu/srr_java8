package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class AutoGenAddHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("autogen-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("duplicateAutoGenErrorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("duplicateAutoGenFailurePage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				    /*
				     *  added the following
				     *  so that after a fiscal year record is successfully added
				     *  the user gets returned back to the fiscal year browse
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