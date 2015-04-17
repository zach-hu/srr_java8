package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class PoLineListRetrieveIcReqHeaderList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List	poLineList = (List) incomingRequest.get("poLineList");
			List	icReqHeaderList = new ArrayList();
			PoLineRetrieveIcReqHeader task = new PoLineRetrieveIcReqHeader();

			for (int i = 0; i < poLineList.size(); i++)
			{
				PoLine poLine = (PoLine) poLineList.get(i);

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("PoLine_icReqLine", poLine.getIcReqLine().toString());

				String icReqHeader = (String) task.executeTask(updateParameters);

				if (task.getStatus() < Status.SUCCEEDED) {
					throw new Exception("PoLineRetrieveIcReqHeader failed.");
				}

				icReqHeaderList.add(i, icReqHeader);
			}

			result = icReqHeaderList;
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}