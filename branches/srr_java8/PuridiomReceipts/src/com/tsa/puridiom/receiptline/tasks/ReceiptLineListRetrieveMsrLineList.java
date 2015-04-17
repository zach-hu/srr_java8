package com.tsa.puridiom.receiptline.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineListRetrieveMsrLineList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve-by-msr.xml");

			List receiptLineList = (List) incomingRequest.get("receiptLineList");
			List msrLineList = new ArrayList();

			for(int i=0; i<receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine)receiptLineList.get(i);
				
				if(receiptLine == null) receiptLine = new ReceiptLine();

				Map requestParameters = new HashMap();
				requestParameters.put("userId", incomingRequest.get("userId"));
				requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				requestParameters.put("organizationId", incomingRequest.get("organizationId"));
				requestParameters.put("dbsession", incomingRequest.get("dbsession"));
				requestParameters.put("RequisitionLine_icLineHistory", receiptLine.getIcLineHistory().toString()) ;
				
				process.executeProcess(requestParameters);
				
				RequisitionLine msrLine = (RequisitionLine)requestParameters.get("requisitionLine");
				
				msrLineList.add(msrLine);
			}
			
			result = msrLineList;
			
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
