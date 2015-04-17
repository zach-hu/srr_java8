package com.tsa.puridiom.receiptheader.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptHeaderUpdateStatusByReceiptLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try{
			Map incomingRequest = (Map)object;
			String oid = (String)incomingRequest.get("organizationId");
			String userId = (String)incomingRequest.get("userId");
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);

			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			StringBuffer status = new StringBuffer();
			int lowestStatus = 99999;
			if(receiptLineList != null && receiptLineList.size() > 0)
			{
				for (int i = 0; i < receiptLineList.size(); i++) {
					ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
					String receiptLineStatus = receiptLine.getStatus();
					if(lowestStatus > Integer.parseInt(receiptLineStatus)){
						lowestStatus = Integer.parseInt(receiptLineStatus);
					}
				}

				status.append(lowestStatus);

				if(!receiptHeader.getReceiptStatus().equalsIgnoreCase(status.toString())){
					PuridiomProcess process = processLoader.loadProcess("receipt-history.xml");
					//PuridiomProcess update = processLoader.loadProcess("receiptheader-update.xml");
					
					receiptHeader.setReceiptStatus(status.toString());
	
					ReceiptHeaderUpdate update = new ReceiptHeaderUpdate();
					Map updateIncomingRequest = new HashMap();
					updateIncomingRequest.put("receiptHeader", receiptHeader);
					//updateIncomingRequest.put("ReceiptHeader_icRecHeader", receiptHeader.getIcRecHeader().toString());
					//updateIncomingRequest.put("ReceiptHeader_receiptStatus", status.toString());
					updateIncomingRequest.put("organizationId", oid);
					updateIncomingRequest.put("userId", userId);
					updateIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
					update.executeTask(updateIncomingRequest);
					
					updateIncomingRequest.put("newHistoryReceiptHeader", receiptHeader);
					process.executeProcess(updateIncomingRequest);
				}
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}

}
