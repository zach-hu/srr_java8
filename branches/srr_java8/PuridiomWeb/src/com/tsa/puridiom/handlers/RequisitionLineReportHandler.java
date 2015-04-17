package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
import com.tsagate.properties.DictionaryManager;

import java.util.*;

public class RequisitionLineReportHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
		    String oid = (String)incomingRequest.get("organizationId");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
			PuridiomProcess process = processLoader.loadProcess("requisitionline-report.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
			    String nameIt = DictionaryManager.getInstance("host", oid).getProperty("reportsDisplay", "reports/out/");
				incomingRequest.put("viewPage", nameIt + (String) incomingRequest.get("report"));
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