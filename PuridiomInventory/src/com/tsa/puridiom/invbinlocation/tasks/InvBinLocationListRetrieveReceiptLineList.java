package com.tsa.puridiom.invbinlocation.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

@SuppressWarnings(value = { "unchecked" })
public class InvBinLocationListRetrieveReceiptLineList extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List invBinLocationList = (List)incomingRequest.get("invBinLocationList");
			List receiptLineList = new ArrayList();
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("receiptline-retrieve-by-id.xml");
			
			for (int i = 0; i < invBinLocationList.size(); i++) {
				InvBinLocation invBinLocation = (InvBinLocation)invBinLocationList.get(i);
				
				if(invBinLocation == null) invBinLocation = new InvBinLocation(); 
				
				incomingRequest.put("ReceiptLine_icRecLine", invBinLocation.getIcRecLine().toString());
				
				process.executeProcess(incomingRequest);
				
				ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
				
				receiptLineList.add(receiptLine);
			}
			
			result = receiptLineList;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}