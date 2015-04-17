package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RequisitionLineUpdateStatusAndType extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	reqLineList = (List) incomingRequest.get("requisitionLineList");

			for (int i=0; i < reqLineList.size(); i++) {
				RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i);

				reqLine.setReqType(RequisitionType.PURCHASE_REQUEST);
				reqLine.setStatus(DocumentStatus.REQ_INPROGRESS);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("requisitionline-update-norecalc.xml");

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