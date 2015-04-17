package com.tsa.puridiom.disbline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Task;

public class DisbLineIcAccountSetup extends Task {

	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		DisbLine dsl = (DisbLine) incomingRequest.get("disbLine");

		String	delAll = (String) incomingRequest.get("deleteall") ;
		if (delAll == null) delAll = "" ;
		if (delAll.compareTo("TRUE") != 0) {
			incomingRequest.put("DisbLine_icAccount", dsl.getIcDsbLine().toString());
		} else {
			incomingRequest.put("DisbLine_icAccount", "0");
		}

		return null;
	}
}