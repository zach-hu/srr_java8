package com.tsa.puridiom.receiptheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptHeaderRetrieveTempReturnSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("originalReceiptHeader");
			if (receiptHeader == null) {
				throw new Exception("The Original ReceiptHeader entity was not found.");
			}

			incomingRequest.put("ReceiptHeader_tempIc", String.valueOf(receiptHeader.getIcRecHeader()));
			incomingRequest.put("ReceiptHeader_receiptType", "R");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return result ;
	}
}
