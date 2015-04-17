package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class SubCommodityAddHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("subcommodity-add.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				String	errorMsg = (String) incomingRequest.get("duplicateCommodityErrorMsg");
				if (errorMsg != null && errorMsg.trim().length() > 0)
				{
					incomingRequest.put("viewPage", incomingRequest.get("duplicateCommodityFailurePage"));
				}
				else
				{
				    incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				    /*
				     *  after a subcommodity record is successfully added
				     *  the user gets returned back to the subcommodity browse
				     *  (browseName is declared on the jsp)
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