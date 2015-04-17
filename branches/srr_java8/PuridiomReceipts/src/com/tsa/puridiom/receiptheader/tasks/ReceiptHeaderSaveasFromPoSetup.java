package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

public class ReceiptHeaderSaveasFromPoSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");

			if (receiptHeader == null)
			{
				throw new Exception("The ReceiptHeader entity was not found.");
			}

			String	icHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
			String	icLine = "0";
			String	newIcHeader = receiptHeader.getIcRecHeader().toString();
			String	newIcLine = "0";

			if(Utility.isEmpty(icHeader))
			{
				throw new Exception("The value for PoHeader_icPoHeader on the PoHeader entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for ReceiptHeader_icRecHeader on the ReceiptHeader entity cannot be empty.");
			}

			incomingRequest.put("formtype", "REC");

			incomingRequest.put("DocComment_icHeader",icHeader);
			incomingRequest.put("DocComment_icLine",icLine);
			incomingRequest.put("DocAttachment_icHeader",icHeader);
			incomingRequest.put("DocAttachment_icLine",icLine);

			incomingRequest.put("newDocComment_icHeader", newIcHeader);
			incomingRequest.put("newDocComment_icLine", newIcLine);
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader);
			incomingRequest.put("newDocAttachment_icLine", newIcLine);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}
}
