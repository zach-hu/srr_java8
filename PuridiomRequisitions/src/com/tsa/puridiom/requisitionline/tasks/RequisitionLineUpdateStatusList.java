package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;
import com.tsa.puridiom.common.documents.*;

public class RequisitionLineUpdateStatusList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	reqLineList = (List) incomingRequest.get("requisitionLineList");


			for (int i=0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("requisitionline-update-status.xml");

				Map newIncomingRequest = new HashMap();

				newIncomingRequest.put("requisitionLine", reqLine);
				newIncomingRequest.put("organizationId",incomingRequest.get("organizationId"));
				newIncomingRequest.put("userId",incomingRequest.get("userId"));
				newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
				newIncomingRequest.put("RequisitionLine_status", incomingRequest.get("newStatus"));

				////////////////////////// CODE ADDED //////////////////////////
				if (incomingRequest.containsKey("RequisitionHeader_noteCancel")) {
					newIncomingRequest.put("RequisitionHeader_noteCancel", incomingRequest.get("RequisitionHeader_noteCancel"));
				}

				process.executeProcess(newIncomingRequest);

				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Requisition Line save as process failed.");
				}
			}

			result = reqLineList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}