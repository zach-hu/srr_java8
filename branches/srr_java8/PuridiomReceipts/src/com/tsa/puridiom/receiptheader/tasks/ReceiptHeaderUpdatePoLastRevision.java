package com.tsa.puridiom.receiptheader.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptHeaderUpdatePoLastRevision extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			
			List receiptHeaderList = (List)incomingRequest.get("receiptHeaderList");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			
			if(poHeader != null && receiptHeaderList != null && receiptHeaderList.size() > 0)
			{
				PuridiomProcess process = processLoader.loadProcess("receiptline-update-po-lastrevision.xml");
				
				for (int i = 0; i < receiptHeaderList.size(); i++) {
					ReceiptHeader receiptHeader = (ReceiptHeader)receiptHeaderList.get(i);
					receiptHeader.setIcPoHeader(poHeader.getIcPoHeader());
					
					ReceiptHeaderUpdate update = new ReceiptHeaderUpdate();
					incomingRequest.put("receiptHeader", receiptHeader);
					update.executeTask(incomingRequest);
					
					incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
					incomingRequest.put("ReceiptLine_icRecHeader", receiptHeader.getIcRecHeader().toString());
					
					process.executeProcess(incomingRequest);
				}
			} else {
				Log.error(this, "receiptHeaderList or poHeader is empty! in ReceiptHeaderUpdatePoLastRevision");
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