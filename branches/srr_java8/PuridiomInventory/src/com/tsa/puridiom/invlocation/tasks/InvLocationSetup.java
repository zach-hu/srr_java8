package com.tsa.puridiom.invlocation.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvLocationSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvLocation_itemNumber", "");
			incomingRequest.put("InvLocation_itemLocation", "");
			incomingRequest.put("InvLocation_qtyOnHand", "0");
			incomingRequest.put("InvLocation_qtyOnOrder", "0");
			incomingRequest.put("InvLocation_minOnHand", "0");
			incomingRequest.put("InvLocation_maxOnHand", "0");
			incomingRequest.put("InvLocation_qtyEoq", "0");
			incomingRequest.put("InvLocation_qtyEsq", "0");
			incomingRequest.put("InvLocation_averageCost", "0");
			incomingRequest.put("InvLocation_udf1Code", "");
			incomingRequest.put("InvLocation_udf2Code", "");
			incomingRequest.put("InvLocation_udf3Code", "");
			incomingRequest.put("InvLocation_udf4Code", "");
			incomingRequest.put("InvLocation_udf5Code", "");
			incomingRequest.put("InvLocation_qtyAlloc", "0");
			incomingRequest.put("InvLocation_icInvAccount", "0");
			incomingRequest.put("InvLocation_icInvHeader", "0");
			incomingRequest.put("InvLocation_qtyRequested", "0");
			incomingRequest.put("InvLocation_autoReplenish", "");
			incomingRequest.put("InvLocation_physActual", "0");
			incomingRequest.put("InvLocation_physOriginal", "0");
			incomingRequest.put("InvLocation_primeLocation", "");
			incomingRequest.put("InvLocation_physAlloc", "0");
			incomingRequest.put("InvLocation_originalAlloc", "0");
			incomingRequest.put("InvLocation_qtyPendOrder", "0");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}