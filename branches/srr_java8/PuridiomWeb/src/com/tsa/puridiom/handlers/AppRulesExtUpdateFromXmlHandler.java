package com.tsa.puridiom.handlers;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public class AppRulesExtUpdateFromXmlHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("apprulesext-update-from-xml.xml");

            String ruleType = (String) incomingRequest.get("AppRulesExt_ruleType");

            if (Utility.isEmpty(ruleType)) {
                incomingRequest.put("AppRulesExt_ruleType", "REQ");
            }

			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				incomingRequest.put("viewPage", (String) incomingRequest.get("successPage"));
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