package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class AutoAwardFailedRequisitionJob extends ScheduledJob
{

	public void execute()
	{
		this.getReqLinesToExecute();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getReqLinesToExecute()
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("requisitionheader-retrieve-for-requisitionfailedautoawardspecial.xml");
			
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("userId", "PS-AAFRJ");

			process.executeProcess(incomingRequest);

		}
		catch (Exception e)
		{
			Log.error(this, "getRequisitionsToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}

	}
}
