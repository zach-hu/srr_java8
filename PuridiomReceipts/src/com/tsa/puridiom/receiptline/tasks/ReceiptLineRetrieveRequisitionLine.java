package com.tsa.puridiom.receiptline.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class ReceiptLineRetrieveRequisitionLine extends Task {

	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve.xml");

			List receiptLineList = (List) incomingRequest.get("receiptLineList");

			for(int i = 0; i < receiptLineList.size(); i++)
			{
				ReceiptLine rcvLine = (ReceiptLine)receiptLineList.get(i);
				Map requestParameters = new HashMap();
	        	requestParameters.put("userId", incomingRequest.get("userId"));
	        	requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
	        	requestParameters.put("organizationId", incomingRequest.get("organizationId"));
	        	requestParameters.put("dbsession", incomingRequest.get("dbsession"));
	        	requestParameters.put("RequisitionLine_icReqLine", rcvLine.getIcPoLine().toString());
	        	
	        	requestParameters.put("RequisitionHeader_icReqHeader", receiptHeader.getIcReqHeader().toString());
	        	String id = rcvLine.getIcPoLine().toString();
	        	
				process.executeProcess(requestParameters);
				if(process.getStatus() == Status.SUCCEEDED)
				{
					RequisitionLine receiptRequisitionLine = (RequisitionLine)requestParameters.get("requisitionLine");
					rcvLine.setRequisitionLine(receiptRequisitionLine);
				}
				receiptLineList.set(i, rcvLine);
			}


	        result = receiptLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
