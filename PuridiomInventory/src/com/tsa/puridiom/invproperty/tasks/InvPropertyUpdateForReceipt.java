package com.tsa.puridiom.invproperty.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.*;

public class InvPropertyUpdateForReceipt extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;

		try
		{
			Map incomingRequest = (Map)object;

			Object serialNumberObj = incomingRequest.get("InvProperty_serialNumber");
			Object dateInObj = incomingRequest.get("InvProperty_dateIn");
			Object ownerObj = incomingRequest.get("InvProperty_owner");

			if (serialNumberObj instanceof String[])
			{
				String serialNumber[] = (String[]) serialNumberObj;
				String dateIn[] = (String[]) dateInObj;
				String owner[] = (String[]) ownerObj;

				for (int i = 0; i < serialNumber.length; i++)
				{
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invproperty-add-by-id.xml");

					incomingRequest.put("InvProperty_serialNumber", serialNumber[i]);
					incomingRequest.put("InvProperty_dateIn", dateIn[i]);
					incomingRequest.put("InvProperty_owner", owner[i]);

					process.executeProcess(incomingRequest);

					if (process.getStatus() < Status.SUCCEEDED) {
						throw new Exception("Serial Number Add Failed - " + serialNumber[i]);
					}
				}
			}
			else if (serialNumberObj instanceof String)
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("invproperty-add-by-id.xml");
				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("SerialNumber Add failed - " + (String)serialNumberObj);
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}