package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class ReceiptFinalizeTempList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List receiptReturnList = (List) incomingRequest.get("receiptHeaderList");

			if (receiptReturnList != null && receiptReturnList.size() > 0)
			{
				for (int i=0; i < receiptReturnList.size(); i++)
				{
					ReceiptHeader receiptHeader = (ReceiptHeader) receiptReturnList.get(i);

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("receipt-finalize-temp-return-by-id.xml");

					Map updateParameters = this.getDefaultUpdateParameters(incomingRequest);
					updateParameters.put("ReceiptHeader_icRecHeader", String.valueOf(receiptHeader.getIcRecHeader()));
					updateParameters.put("ReceiptLine_icRecHeader", String.valueOf(receiptHeader.getIcRecHeader()));

					process.executeProcess(updateParameters);
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
