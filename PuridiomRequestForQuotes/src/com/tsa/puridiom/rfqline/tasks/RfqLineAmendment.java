package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqLineAmendment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain originalRfqLine & rfqLine */
			RfqLine	originalRfqLine = (RfqLine) incomingRequest.get("originalRfqLine");
			RfqLine	rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			
			rfqLine.setIcLineHistory(originalRfqLine.getIcLineHistory());
			rfqLine.setIcReqLine(originalRfqLine.getIcReqLine());
			rfqLine.setStatus(DocumentStatus.RFQ_OPENAMENDMENT);

			incomingRequest.put("rfqLine", rfqLine);

			result = rfqLine;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
