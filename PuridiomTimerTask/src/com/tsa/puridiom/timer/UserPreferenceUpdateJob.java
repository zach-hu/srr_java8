package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class UserPreferenceUpdateJob extends ScheduledJob
{
	public void execute()
	{
		this.getUserPreferenceUpdate();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getUserPreferenceUpdate()
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("userpreference-job.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());

			process.executeProcess(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(this, "getUserPreferenceUpdate failed: " + e.getMessage());
		}
	}
}
