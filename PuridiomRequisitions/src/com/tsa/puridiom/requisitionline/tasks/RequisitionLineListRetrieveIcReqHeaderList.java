package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RequisitionLineListRetrieveIcReqHeaderList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List	icReqHeaderList = new ArrayList();
			RequisitionLineRetrieveIcReqHeader task = new RequisitionLineRetrieveIcReqHeader();
			if (incomingRequest.containsKey("RequisitionLine_icReqLine"))
			{
				Object icReqLineObj = incomingRequest.get("RequisitionLine_icReqLine");
				if (icReqLineObj instanceof String[])
				{
					String[] icReqLineArray = (String[]) icReqLineObj;
					int	arraySize = icReqLineArray.length;

					for (int i = 1; i < arraySize; i++)
					{
						Map updateParameters = new HashMap();
						updateParameters.put("userId", incomingRequest.get("userId"));
						updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
						updateParameters.put("organizationId", incomingRequest.get("organizationId"));
						updateParameters.put("dbsession", incomingRequest.get("dbsession"));
						updateParameters.put("RequisitionLine_icReqLine", icReqLineArray[i]);

						String icReqHeader = (String) task.executeTask(updateParameters);

						if (task.getStatus() < Status.SUCCEEDED)
						{
							throw new Exception("RequisitionLineRetrieveIcReqHeader failed.");
						}

						icReqHeaderList.add(i-1, icReqHeader);
					}
				}
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