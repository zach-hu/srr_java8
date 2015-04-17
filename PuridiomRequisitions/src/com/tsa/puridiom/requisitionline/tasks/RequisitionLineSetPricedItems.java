package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineSetPricedItems extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			List	rfqBidList = (List) incomingRequest.get("rfqBidList");
			List	updatedReqLineList = new ArrayList();
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			String	vendorCode = (String) incomingRequest.get("vendorCode");
			int	iReqLines = 1;


			if (rfqLineList != null) {
				for (int i = 0; i < rfqLineList.size(); i++) {
					RfqLine rfqLine = (RfqLine) rfqLineList.get(i);


					PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
					PuridiomProcess process = processLoader.loadProcess("requisitionline-set-priced-item.xml");
					HashMap taskParameters = new HashMap();

					taskParameters.put("userId", incomingRequest.get("userId"));
					taskParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
					taskParameters.put("organizationId", incomingRequest.get("organizationId"));
					taskParameters.put("dbsession", incomingRequest.get("dbsession"));

					taskParameters.put("rfqLine", rfqLine);
					taskParameters.put("icRfqHeader", String.valueOf(rfqLine.getIcRfqHeader()));
					taskParameters.put("icRfqLine", String.valueOf(rfqLine.getIcRfqLine()));
					taskParameters.put("icReqLine", String.valueOf(rfqLine.getIcSource()));
					taskParameters.put("requisitionLineNumber", String.valueOf(iReqLines));
					taskParameters.put("vendorCode", vendorCode);
					taskParameters.put("rfqBidList", rfqBidList);
					taskParameters.put("updatedReqLineList", updatedReqLineList);
					taskParameters.put("requisitionLineList", requisitionLineList);
					taskParameters.put("requisitionHeader", reqHeader);

					process.executeProcess(taskParameters);

					RequisitionLine reqLine = (RequisitionLine) taskParameters.get("requisitionLine");

					updatedReqLineList.add(reqLine);
					iReqLines++;
				}
			}

			result = updatedReqLineList;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}