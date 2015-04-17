package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineSetupIcRevisedPoLine extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		incomingRequest.put("RequisitionLine_icRevisedPoLine", "0");
		return null ;
	}
}