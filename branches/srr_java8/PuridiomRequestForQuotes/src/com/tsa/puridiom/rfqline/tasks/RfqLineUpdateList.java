package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqline.exceptions.RfqLineNotFoundException;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.*;

public class RfqLineUpdateList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List	rfqLines = (List)incomingRequest.get("rfqLines");
			if(rfqLines == null)
			{
			    rfqLines = (List)incomingRequest.get("rfqLineList");
			}
			if(rfqLines == null)
			{
			    this.setStatus(Status.FAILED);
			    throw new RfqLineNotFoundException(this.getName() + "- List of Rfq Lines was not found");
			}
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("rfqline-update-norecalc.xml");

			for (int i=0; i < rfqLines.size(); i++)
			{
				RfqLine rfqLine = (RfqLine) rfqLines.get(i);

				incomingRequest.put("rfqLine", rfqLine);

				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("Rfq Line save as process failed.");
				}
			}

			result = rfqLines;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}