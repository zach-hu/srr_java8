package com.tsa.puridiom.receiptline.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.messaging.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * 
 * @author Alexander
 *
 */
public class ReceiptLineUpdateListByInspection extends Task 
{
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception {
		
		Map incomingRequest = (Map)object;
		
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("receiptline-update.xml");
		
		List receiptList = (List) incomingRequest.get("receiptLineListByInspection");		
		Iterator iterator = receiptList.iterator(); 
		while (iterator.hasNext()) 
		{
			ReceiptLine receiptLine = (ReceiptLine) iterator.next(); 
			
			Map updateParameters = new HashMap();
			updateParameters.put("userId", incomingRequest.get("userId"));
			updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
			updateParameters.put("organizationId", incomingRequest.get("organizationId"));
			updateParameters.put("dbsession", incomingRequest.get("dbsession"));
			updateParameters.put("ReceiptLine_icRecLine", receiptLine.getIcRecLine().toString());
			updateParameters.put("ReceiptLine_status", DocumentStatus.RCV_STEP_1);
			process.executeProcess(updateParameters);
		}
		
		this.setStatus(Status.SUCCEEDED) ;
		
		return receiptList;
	}

}
