package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineListRetrieveIcReqHeaderList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			List	icReqHeaderList = new ArrayList();
			List	reqNumberList = new ArrayList();
			RfqLineRetrieveIcReqHeader task = new RfqLineRetrieveIcReqHeader();

			for (int i = 0; i < rfqLineList.size(); i++) {
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("RfqLine_icReqLine", rfqLine.getIcReqLine().toString());

				Object reqInfoObj = (Object) task.executeTask(updateParameters);
				if (reqInfoObj instanceof String[])
				{
					String reqInfo[] = (String[]) reqInfoObj;
					icReqHeaderList.add(i, reqInfo[0]);
					reqNumberList.add(i, reqInfo[1]);
				}
				else
				{
					reqNumberList.add(i, "0");
				}

				if (task.getStatus() < Status.SUCCEEDED)
				{
					throw new Exception("RfqLineRetrieveIcReqHeader failed.");
				}

			}

			incomingRequest.put("reqNumberList", reqNumberList);
			result = icReqHeaderList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}