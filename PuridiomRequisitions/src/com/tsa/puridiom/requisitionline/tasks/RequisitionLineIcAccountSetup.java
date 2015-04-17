package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;

public class RequisitionLineIcAccountSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		RequisitionLine rql =
			(RequisitionLine) incomingRequest.get("requisitionLine");

		String	delAll = (String) incomingRequest.get("deleteall") ;

		if (delAll == null) delAll = "" ;

		if (delAll.compareTo("TRUE") != 0) {
			incomingRequest.put("RequisitionLine_icAccount", rql.getIcReqLine().toString());
		} else {
			incomingRequest.put("RequisitionLine_icAccount", "0");
		}

		return null;
	}
}