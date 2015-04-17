package com.tsa.puridiom.rfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqCreateLinesFromRequisition extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			List	reqLineList = (List) incomingRequest.get("requisitionLineList");
			List	rfqLineList = new ArrayList();
			String	icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("rfqline-create-from-requisition-line.xml");
			HashMap updateParameters = new HashMap();

			updateParameters.put("userId", incomingRequest.get("userId"));
			updateParameters.put("organizationId", incomingRequest.get("organizationId"));
			updateParameters.put("dbsession", incomingRequest.get("dbsession"));
			updateParameters.put("RfqHeader_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
			updateParameters.put("RfqHeader_icReqHeader", incomingRequest.get("RfqHeader_icReqHeader"));
			updateParameters.put("RfqHeader_rfqNumber", incomingRequest.get("RfqHeader_rfqNumber"));

			updateParameters.put("rfqHeader", incomingRequest.get("rfqHeader"));

			for (int i = 0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

				updateParameters.put("requisitionLine", reqLine);

				process.executeProcess(updateParameters);

				if (process.getStatus() == Status.SUCCEEDED)
				{
					rfqLineList.add(updateParameters.get("rfqLine"));

					updateParameters.remove("rfqLine");
					updateParameters.remove("requisitionLine");
				}
				else
				{
					this.setStatus(process.getStatus());
					throw new Exception("Process to create rfq lines from requisition lines failed.");
				}
			}

			incomingRequest.put("RequisitionLine_icReqHeader", icReqHeader);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}