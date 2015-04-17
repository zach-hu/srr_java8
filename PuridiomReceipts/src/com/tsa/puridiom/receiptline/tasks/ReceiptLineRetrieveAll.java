package com.tsa.puridiom.receiptline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptLineRetrieveAll extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;

		try
		{
			Map incomingRequest = (Map)object;
			List receiptLineList = (List)incomingRequest.get("receiptLineList");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("receiptlinedata-retrieve.xml");

			for(int i = 0; i < receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				Map updateParameters = new HashMap();
				updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("receiptLine", receiptLine);

				process.executeProcess(updateParameters);
				this.setStatus(process.getStatus());
				if (process.getStatus() != Status.SUCCEEDED)
				{
					throw new TsaException("Receipt Line save as process failed.");
				}
				else
				{
					receiptLine = (ReceiptLine)updateParameters.get("receiptLine");
					receiptLineList.set(i, receiptLine);
				}
			}
			result = receiptLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
