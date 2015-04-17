package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoLineCancelFromReqLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List poLineList = (List) incomingRequest.get("poLineList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");

			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("organizationId",organizationId);
			newIncomingRequest.put("userId",userId);
			newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("poline-cancel-from-reqline.xml");

			for (int x = 0; x < poLineList.size(); x++) {
				PoLine poLine = (PoLine) poLineList.get(x);
				if (poLine.getPoNumber().compareTo("N/A") != 0) {
					newIncomingRequest.put("poLine",poLine);
					newIncomingRequest.put("PoLine_status",DocumentStatus.CANCELLED);
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