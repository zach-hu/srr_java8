package com.tsa.puridiom.timer;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class AutoAwardRequisitionJob extends ScheduledJob
{

	public void execute()
	{
		this.getReqLinesToExecute();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getReqLinesToExecute()
	{
		Log.debug(this, "retrieve list of requisition with status approved and valid supplier.");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("requisitionheader-retrieve-for-autoawardrequisition.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("isAutoAwardRequisition", "Y");
			incomingRequest.put("userId", "PS-AAR");
			
			process.executeProcess(incomingRequest);

		}
		catch (Exception e)
		{
			Log.error(this, "getRequisitionsToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}

	}
}
