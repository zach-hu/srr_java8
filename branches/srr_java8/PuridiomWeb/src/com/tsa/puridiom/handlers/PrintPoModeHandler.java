package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class PrintPoModeHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("po-update-mode.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
                String viewNow = (String)incomingRequest.get("viewNow");
                if(viewNow == null) {   viewNow = "N";  }
                if(viewNow.equalsIgnoreCase("Y"))
                {
                    incomingRequest.put("viewPage", incomingRequest.get("report"));                    
                }
                else
                {
                    incomingRequest.put("viewPage", (String) incomingRequest.get("successPage"));
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