package com.tsa.puridiom.receiptline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReceiptLineUpdatePoIc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			List<PoLine> poLineList = (List<PoLine>)incomingRequest.get("poLineList");
			List<ReceiptHeader> receiptHeaderList = (List<ReceiptHeader>) incomingRequest.get("receiptList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");

			for (ReceiptHeader receiptHeader : receiptHeaderList)
			{
				ReceiptLineRetrieveByHeader receiptLineRetrieve = new ReceiptLineRetrieveByHeader();
				List<ReceiptLine> receiptLineList = new ArrayList<ReceiptLine>();
				Map lineRetrieveMap = new HashMap();

				lineRetrieveMap.put("dbsession", dbs);
				lineRetrieveMap.put("ReceiptLine_icRecHeader", receiptHeader.getIcRecHeader().toString());
				receiptLineList = (List<ReceiptLine>)receiptLineRetrieve.executeTask(lineRetrieveMap);

				for (ReceiptLine receiptLine : receiptLineList)
				{
					for (PoLine poLine : poLineList)
					{
						if (receiptLine.getReceiptLine().compareTo(poLine.getLineNumber()) == 0)
						{
							receiptLine.setIcPoLine(poLine.getIcPoLine());
							receiptLine.setIcPoHeader(poLine.getIcPoHeader());
							dbs.update(receiptLine);

							if (dbs.getStatus() != Status.SUCCEEDED) {
								throw new Exception("Could not update receiptLine");
							}
							break;
						}
					}
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
