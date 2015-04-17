package com.tsa.puridiom.receiptheader.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReceiptHeaderUpdateIcPoHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List<ReceiptHeader> receiptList = (List<ReceiptHeader>)incomingRequest.get("receiptList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			BigDecimal icPoHeader = poHeader.getIcPoHeader();

			for (ReceiptHeader receiptHeader : receiptList)
			{
				receiptHeader.setIcPoHeader(icPoHeader);
				dbs.update(receiptHeader);

				if (dbs.getStatus() != Status.SUCCEEDED) {
					throw new Exception("Failed to update receiptHeader");
				}
			}

			result = receiptList;
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
