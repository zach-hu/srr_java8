package com.tsa.puridiom.handlers;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class BrowseRetrieveHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("browse-retrieve.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() != Status.FAILED)
			{
			    String	errorMsg = (String) incomingRequest.get("errorMsg");
			    if (!HiltonUtility.isEmpty(errorMsg)) {
			        incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
			    } else {
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




