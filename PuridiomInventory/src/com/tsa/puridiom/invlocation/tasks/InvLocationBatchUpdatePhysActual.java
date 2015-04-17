package com.tsa.puridiom.invlocation.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvLocationBatchUpdatePhysActual extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String[] InvLocation_physActual_Array = (String[]) incomingRequest.get("InvLocation_physActual_Array");
			String[] InvLocation_itemNumber_Array = (String[]) incomingRequest.get("InvLocation_itemNumber_Array");

			for (int i=0; i < InvLocation_itemNumber_Array.length; i++)
			{
				String	changed = (String) incomingRequest.get("changed_" + i);
				if (changed.equalsIgnoreCase("Y")) {
					incomingRequest.put("InvLocation_physActual",InvLocation_physActual_Array[i]);
					incomingRequest.put("InvLocation_qtyOnHand",InvLocation_physActual_Array[i]);
					incomingRequest.put("InvLocation_itemNumber", InvLocation_itemNumber_Array[i]) ;

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invlocation-update.xml");
					process.executeProcess(incomingRequest);

					this.setStatus(process.getStatus());
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}