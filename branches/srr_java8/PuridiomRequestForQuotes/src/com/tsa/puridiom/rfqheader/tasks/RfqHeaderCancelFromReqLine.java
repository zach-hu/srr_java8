package com.tsa.puridiom.rfqheader.tasks;

import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqHeaderCancelFromReqLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqHeaderIdList = (List) incomingRequest.get("rfqHeaderIdList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");

			Map newIncomingRequest = new HashMap();
			newIncomingRequest.put("organizationId",organizationId);
			newIncomingRequest.put("userId",userId);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("rfqheader-cancel-from-reqline.xml");

			for (int x = 0; x < rfqHeaderIdList.size(); x++) {
				BigDecimal rfqHeaderId = (BigDecimal) rfqHeaderIdList.get(x);
				newIncomingRequest.put("RfqHeader_icRfqHeader",rfqHeaderId.toString());
				newIncomingRequest.put("RfqLine_icRfqHeader",rfqHeaderId.toString());
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