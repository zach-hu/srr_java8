package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class InvoiceLineRetrievePoLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("poline-retrieve.xml");

			//List invoicePoLineList = (List) incomingRequest.get("receiptLineList");
			List invoicePoLineList = (List) incomingRequest.get("invoiceLineList");

			for(int i = 0; i < invoicePoLineList.size(); i++)
			{
				InvoiceLine invLine = (InvoiceLine)invoicePoLineList.get(i);
				Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
						  requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("PoLine_icPoLine", invLine.getIcPoLine().toString());
	        	requestParameters.put("PoHeader_icPoHeader", incomingRequest.get("PoLine_icPoHeader"));
				process.executeProcess(requestParameters);
				if(process.getStatus() == Status.SUCCEEDED)
				{
					PoLine invoicePoLine = (PoLine)requestParameters.get("poLine");
					invLine.setPoLine(invoicePoLine);
				}
				invoicePoLineList.set(i, invLine);
			}


	        result = invoicePoLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
