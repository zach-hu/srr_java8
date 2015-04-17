package com.tsa.puridiom.receiptline.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineUpdatePoLastRevision extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			List receiptLineList = (List)incomingRequest.get("receiptLineList");
			List poLineList = (List)incomingRequest.get("poLineList");
			
			if(receiptLineList != null && receiptLineList.size() > 0)
			{
				for (int i = 0; i < receiptLineList.size(); i++) {
					ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
					PoLine poLine = (PoLine)poLineList.get(i);
					
					if(receiptLine.getIcLineHistory().compareTo(poLine.getIcLineHistory()) == 0){
						receiptLine.setIcPoLine(poLine.getIcPoLine());
						receiptLine.setIcPoHeader(poLine.getIcPoHeader());
					} else {
						for (int j = 0; j < poLineList.size(); j++) {
							poLine = (PoLine)poLineList.get(j);
							
							if(receiptLine.getIcPoLine().compareTo(poLine.getIcLineKey()) == 0){
								receiptLine.setIcPoLine(poLine.getIcPoLine());
								receiptLine.setIcPoHeader(poLine.getIcPoHeader());
							} else {
								Log.info(this, "ReceiptLine has not been updated, there is not PoLine relationated");
							}
						}
					}
					
				}
			} else {
				Log.error(this, "receiptHeaderList or poHeader is empty! in ReceiptHeaderUpdatePoLastRevision");
			}
			
			result = receiptLineList;
			
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}

		return result ;
	}
}