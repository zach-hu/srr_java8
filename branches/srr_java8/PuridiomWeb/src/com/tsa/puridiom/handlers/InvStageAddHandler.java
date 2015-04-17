package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvStageAddHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invstage-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("duplicateInvStageErrorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("duplicateInvStageFailurePage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				    /* Return back to browse page */
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