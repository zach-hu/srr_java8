package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class AutoAwardRevisionJob extends ScheduledJob
{
	public void execute()
	{
		this.getReqLinesToExecute();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getReqLinesToExecute()
	{
		Log.debug(this, "Retrieve list of POs with status Order Pending from Change Request.");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-for-autoaward-revision.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("autoAwardRevision", "true");

			process.executeProcess(incomingRequest);
		}
		catch (Exception e)
		{
			Log.error(this, "getPosToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}
	}
}