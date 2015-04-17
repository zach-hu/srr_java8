package com.tsa.puridiom.receipt.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class QuickReceive extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			Object icPoHeaderObject = incomingRequest.get("icPoHeader") ;
			List icPoHeaderList = null;

			if(icPoHeaderObject instanceof List){
				icPoHeaderList = (ArrayList)icPoHeaderObject;
			} else if(icPoHeaderObject instanceof String[]) {
				String[] tempPoArray = (String[])icPoHeaderObject;
				icPoHeaderList = new ArrayList();
				for (int i = 0; i < tempPoArray.length; i++) {
					icPoHeaderList.add(tempPoArray[i]);
				}
			} else if(icPoHeaderObject != null){
				icPoHeaderList = new ArrayList();
				icPoHeaderList.add(icPoHeaderObject);
			} else {
				icPoHeaderList = new ArrayList();
			}

			String icPoHeaderString = "";

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("quick-receipt-from-order.xml");

			for (int i = 0; i < icPoHeaderList.size(); i++)
			{
				icPoHeaderString = (String)icPoHeaderList.get(i);

				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("userId", userId);
				newIncomingRequest.put("ReceiptHeader_icPoHeader", icPoHeaderString);
				newIncomingRequest.put("PoHeader_icPoHeader", icPoHeaderString);
				newIncomingRequest.put("PoLine_icPoHeader", icPoHeaderString);
				newIncomingRequest.put("receiptMethod", "ReceiveByOrder");

				try {
					process.executeProcess(newIncomingRequest);
				} catch (Exception e) {
					Log.error(this, e);
					continue;
				}
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			throw e;
		}
		return result;
	}

}