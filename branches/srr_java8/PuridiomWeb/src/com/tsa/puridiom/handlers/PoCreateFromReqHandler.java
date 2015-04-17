package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;

public class PoCreateFromReqHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			
			PuridiomProcess process = null;
			String type = HiltonUtility.ckNull((String)incomingRequest.get("RequisitionHeader_requisitionType"));
			if (type.equals("C")) {
				process = processLoader.loadProcess("po-create-revision-from-req.xml");
				
			} else if (type.equals("E")) {
				incomingRequest.put("createReleaseFromReqAssignedToMe", "true");
				process = processLoader.loadProcess("po-create-release-from-req-assigned-to-me.xml");
				
			} else { // Requisition Type = 'P'
				process = processLoader.loadProcess("po-create-from-req.xml");
			}
			
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