package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.processengine.Status;
import java.util.Map;

public class RequisitionLineUpdateSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		RequisitionLine rql =
			(RequisitionLine) incomingRequest.get("requisitionLine");

		incomingRequest.put("RequisitionHeader_icReqHeader",rql.getIcReqHeader().toString());
		
		incomingRequest.put("Account_icHeader",rql.getIcReqHeader().toString());
		incomingRequest.put("Account_icLine", rql.getIcReqLine().toString());
		
		this.status = Status.SUCCEEDED;

		return null;
	}
}