package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PoHeaderCancelFromReqLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List poHeaderIdList = (List) incomingRequest.get("poHeaderIdList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");

			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("organizationId",organizationId);
			newIncomingRequest.put("userId",userId);
			newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("poheader-cancel-from-reqline.xml");

			for (int x = 0; x < poHeaderIdList.size(); x++) {
				BigDecimal poHeaderId = (BigDecimal) poHeaderIdList.get(x);
				newIncomingRequest.put("PoHeader_icPoHeader",poHeaderId.toString());
				newIncomingRequest.put("PoLine_icPoHeader",poHeaderId.toString());
				newIncomingRequest.put("SendQueue_messagetext",incomingRequest.get("SendQueue_messagetext"));
				process.executeProcess(newIncomingRequest);
				incomingRequest.put("SendQueue_messagetext",newIncomingRequest.get("SendQueue_messagetext"));
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