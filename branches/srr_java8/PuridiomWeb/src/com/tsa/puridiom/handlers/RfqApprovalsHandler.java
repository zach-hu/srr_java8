package com.tsa.puridiom.handlers;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class RfqApprovalsHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			String orgId = (String)incomingRequest.get("organizationId");
			if(orgId == null)
			{
				orgId = "PURIDIOM";
			}
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("rfq-approvals.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				Boolean approversEnoughWarrant = (Boolean)incomingRequest.get("approversEnoughWarrant");
				if(approversEnoughWarrant == null){		approversEnoughWarrant = Boolean.TRUE;		}
				if(!approversEnoughWarrant.booleanValue())
				{
					PuridiomProcessLoader processLoader1 = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process1 = processLoader1.loadProcess("po-pre-forward.xml");
					process1.executeProcess(incomingRequest);
					if (process.getStatus() == Status.SUCCEEDED)
					{
						incomingRequest.put("viewPage", incomingRequest.get("notApproversEnoughWarrantPage"));
					}
					else
					{
						incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
					}
				}
				else
				{
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