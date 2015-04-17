/*
 * Created on August 5, 2004
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLinePopulateData extends Task
{
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("receiptline-populate-data.xml");

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
	        	requestParameters.put("PoHeader_icPoHeader", rcvLine.getIcPoHeader().toString());
	        	requestParameters.put("InvBinLocation_icRecLine", rcvLine.getIcRecLine().toString());
	        	requestParameters.put("DocComment_icLine", rcvLine.getIcRecLine().toString());
	        	requestParameters.put("DocComment_icHeader", rcvLine.getIcRecHeader().toString());
	        	requestParameters.put("ReceiptLine_icRecLine", rcvLine.getIcRecLine().toString()) ;

	        	process.executeProcess(requestParameters);
				if(process.getStatus() == Status.SUCCEEDED)
				{
					PoLine receiptPoLine = (PoLine)requestParameters.get("poLine");
					rcvLine.setPoLine(receiptPoLine);

					PoHeader receiptPoHeader = (PoHeader)requestParameters.get("poHeader") ;
					rcvLine.setPoHeader(receiptPoHeader) ;

					InvBinLocation invBinLocation = (InvBinLocation) requestParameters.get("invBinLocation");
					rcvLine.setInvBinLocation(invBinLocation) ;

					List invPropertyList = (List) requestParameters.get("invPropertyList") ;
					rcvLine.setInvPropertyList(invPropertyList) ;
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
