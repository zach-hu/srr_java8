package com.tsa.puridiom.handlers;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;


public class InvItemAddHandler implements IHandler
{
	public Map handleRequest(Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String) incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("invitem-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				process = processLoader.loadProcess("invitem-retrieve-by-id.xml");
				process.executeProcess(incomingRequest);
				if (process.getStatus() == Status.SUCCEEDED)
				{

					String errorMsg = (String) incomingRequest.get("errorMsg");

					if (!HiltonUtility.isEmpty(errorMsg))
					{
						incomingRequest.put("viewPage", incomingRequest.get("currentPage"));
					} else
					{
						incomingRequest.put("viewPage", incomingRequest.get("successPage"));
					}
				}
			} else
			{
				incomingRequest.put("viewPage", incomingRequest.get("InvItem_failurePage"));
			}
		} catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		} finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}
