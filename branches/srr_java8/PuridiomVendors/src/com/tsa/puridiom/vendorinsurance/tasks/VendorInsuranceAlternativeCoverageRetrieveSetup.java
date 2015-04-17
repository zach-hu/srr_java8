package com.tsa.puridiom.vendorinsurance.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class VendorInsuranceAlternativeCoverageRetrieveSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String organizationId = (String) incomingRequest.get("organizationId");
			String[] tableTypes = { "SLC1", "SLC2", "SLC3" };
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("stdtable-retrieve-by.xml");
			Map newIncomingRequest = new HashMap();

			for (int i = 0; i < tableTypes.length; i++)
			{
				newIncomingRequest.put("organizationId", organizationId);
				newIncomingRequest.put("StdTable_tableType", tableTypes[i]);

				process.executeProcess(newIncomingRequest);

				incomingRequest.put("certStatusList_" + (i + 1), newIncomingRequest.get("stdTableList"));
			}

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
