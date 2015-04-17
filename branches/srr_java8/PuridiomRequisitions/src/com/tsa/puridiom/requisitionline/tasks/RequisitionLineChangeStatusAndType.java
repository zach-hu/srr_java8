package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

public class RequisitionLineChangeStatusAndType extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List reqLineList = (List) incomingRequest.get("requisitionLineList");
			String newReqType = (String) incomingRequest.get("newReqType");

			for (int i=0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("requisitionline-update-norecalc.xml");

				if (reqLine.getReqType().equals("E") && (newReqType.equals("P")||newReqType.equals("N")))
				{
					reqLine.setAutoRelease(new BigDecimal(0));
					reqLine.setBlanketOrder("");
				}
				reqLine.setReqType(newReqType);
				reqLine.setStatus(DocumentStatus.REQ_INPROGRESS);

				incomingRequest.put("requisitionLine", reqLine);

				process.executeProcess(incomingRequest);

				if (process.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("Requisition Line update status and type failed.");
				}
			}

			result = reqLineList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}