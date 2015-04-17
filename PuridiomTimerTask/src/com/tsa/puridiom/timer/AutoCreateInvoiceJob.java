package com.tsa.puridiom.timer;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;

public class AutoCreateInvoiceJob extends ScheduledJob
{

	public void execute()
	{
		this.getInvoiceToExecute();
		System.out.println("job done");
		System.out.println(this.toString());
	}

	public void getInvoiceToExecute()
	{
		Log.debug(this, "retrieve list of invoices with status received.");
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("autocreateinvoice.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("userId", "PS-ACI");
			
			process.executeProcess(incomingRequest);

		}
		catch (Exception e)
		{
			Log.error(this, "getRequisitionsToExecute failed: " + e.getMessage());
			//TODO NOTIFY SOMEBODY
		}

	}
}
