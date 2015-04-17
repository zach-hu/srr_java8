/*
 * Created on August 5, 2004
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineRetrievePoLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("poline-retrieve.xml");

			List receiptLineList = (List) incomingRequest.get("receiptLineList");

			for(int i = 0; i < receiptLineList.size(); i++)
			{
				ReceiptLine rcvLine = (ReceiptLine)receiptLineList.get(i);
				Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
	        	requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("PoLine_icPoLine", rcvLine.getIcPoLine().toString());
				process.executeProcess(requestParameters);
				if(process.getStatus() == Status.SUCCEEDED)
				{
					PoLine receiptPoLine = (PoLine)requestParameters.get("poLine");
					rcvLine.setPoLine(receiptPoLine);
				}
				receiptLineList.set(i, rcvLine);
			}


	        result = receiptLineList;
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
