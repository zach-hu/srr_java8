package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class AutoCleanCallForwardJob extends ScheduledJob
{
	public void execute()
	{
		this.cleanCallForward();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void cleanCallForward()
	{
		Log.debug(this, "Retrieve list of POs with status Order Pending from Change Request.");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("verify-date-callforward.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());

			process.executeProcess(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(this, "getPosToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}
	}
}