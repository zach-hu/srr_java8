package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.property.PropertiesManager;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class AutoAwardChangeRequest extends ScheduledJob
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
			PropertiesManager propertiesManager = PropertiesManager.getInstance(this.getOrganizationId());
			String autoAwardEnabled = propertiesManager.getProperty("AUTOCREATE", "AUTOAWARDCHANGEREQUEST", "N");
			if (autoAwardEnabled.equalsIgnoreCase("Y"))
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
				PuridiomProcess process = processLoader.loadProcess("changerequest-create-porevision.xml");
				
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", this.getOrganizationId());
				incomingRequest.put("userId", "PS-AACR");

				process.executeProcess(incomingRequest);
			}
		}
		catch (Exception e)
		{
			Log.error(this, "getRequisitionsToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}

	}
}
