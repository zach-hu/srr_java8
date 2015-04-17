package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqLineCancelFromReqLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqLineList = (List) incomingRequest.get("rfqLineList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");

			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("organiztionId",organizationId);
			newIncomingRequest.put("userId",userId);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("rfqline-cancel-from-reqline.xml");

			for (int x = 0; x < rfqLineList.size(); x++) {
				RfqLine rfqLine = (RfqLine) rfqLineList.get(x);
				if (rfqLine.getRfqNumber().compareTo("N/A")!=0) {
					newIncomingRequest.put("rfqLine",rfqLine);
					newIncomingRequest.put("RfqLine_status",DocumentStatus.CANCELLED);
					process.executeProcess(newIncomingRequest);
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}