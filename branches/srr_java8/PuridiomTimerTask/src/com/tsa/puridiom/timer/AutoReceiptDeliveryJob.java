package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class AutoReceiptDeliveryJob extends ScheduledJob
{

	public void execute()
	{
		this.getReceiptsToExecute();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getReceiptsToExecute()
	{
		Log.debug(this, "retrieve list for alert type : " + SendQueue.REPORT_ACTION);
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("receipt-process-auto-delivery.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("userId", "AUTORECEIPT");

			process.executeProcess(incomingRequest);

			if(process.getStatus() == Status.SUCCEEDED)
			{
				Map groupByOrder = (Map)incomingRequest.get("groupByOrder");
				System.out.println("groupByOrder: " + groupByOrder);
			}

		}
		catch (Exception e)
		{
			Log.error(this, "getReceiptHeadersToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}

	}
}
