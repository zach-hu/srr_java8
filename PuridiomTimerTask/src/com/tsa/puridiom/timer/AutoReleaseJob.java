package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class AutoReleaseJob extends ScheduledJob
{

	public void execute()
	{
		this.getReqLinesToExecute();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getReqLinesToExecute()
	{
		Log.debug(this, "retrieve list for alert type : " + SendQueue.REPORT_ACTION);
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve-for-autorelease.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("userId", "AUTORELEASE");

			process.executeProcess(incomingRequest);
			if(process.getStatus() == Status.SUCCEEDED)
			{
				Map groupByOrder = (Map)incomingRequest.get("groupByOrder");
				System.out.println("groupByOrder: " + groupByOrder);
			}

		}
		catch (Exception e)
		{
			Log.error(this, "getReqLinesToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}

	}
}
