package com.tsa.puridiom.handlers;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class PoImportFromReqHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("po-import-from-req.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String needBidWaiver = HiltonUtility.ckNull((String)incomingRequest.get("needBidWaiver"));
				if(!needBidWaiver.equalsIgnoreCase("true"))
				{
					processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					process = processLoader.loadProcess("po-retrieve.xml");
					process.executeProcess(incomingRequest);
					if (process.getStatus() == Status.SUCCEEDED)
					{
						incomingRequest.put("viewPage", incomingRequest.get("successPage"));
					}
					else
					{
						incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
					}
				}
				else
				{
					incomingRequest.put("viewPage", incomingRequest.get("needBidWaiverPage"));
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
			incomingRequest.put("exception", exception);
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