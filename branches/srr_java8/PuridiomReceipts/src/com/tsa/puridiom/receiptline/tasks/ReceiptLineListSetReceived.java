package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptLineListSetReceived extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List receiptLineList = (List) incomingRequest.get("receiptLineList");

			if (receiptLineList != null && receiptLineList.size() > 0)
			{
				for (int i=0; i < receiptLineList.size(); i++)
				{
					ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);

					receiptLine.setStatus(DocumentStatus.RCV_RECEIVED);
					
					receiptLineList.set(i, receiptLine);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
