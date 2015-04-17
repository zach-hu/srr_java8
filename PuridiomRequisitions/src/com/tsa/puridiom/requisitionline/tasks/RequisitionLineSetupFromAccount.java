package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class RequisitionLineSetupFromAccount extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try
		{
			String lineIc = (String) incomingRequest.get("Account_icLine");

			if (!incomingRequest.containsKey("RequisitionLine_icReqLine")) {
				incomingRequest.put("RequisitionLine_icReqLine", lineIc);
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			e.printStackTrace();
			Log.error(this, "Error on RequisitionLineSetupFromAccount");
			throw e;
		}
		return null ;
	}
}