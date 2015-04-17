package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqLineUpdateStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();

		try
		{
			List icRfqLineList = (List) incomingRequest.get("icRfqLineList");
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
            PuridiomProcess process = processLoader.loadProcess("rfqline-retrieve-by-id.xml");
			for (int i=0; i<icRfqLineList.size(); i++) {

				String icRfqLine = (String) icRfqLineList.get(i);

                Map newIncomingRequest = new HashMap();

				newIncomingRequest.put("RfqLine_icRfqLine", icRfqLine);
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));

                process.executeProcess(newIncomingRequest);

                RfqLine rfqLine = (RfqLine) newIncomingRequest.get("rfqLine");
                rfqLine.setStatus(DocumentStatus.PO_INPROGRESS);

                result.add(rfqLine);

                this.setStatus(process.getStatus());
                if (this.getStatus() != Status.SUCCEEDED)
                {
                    Log.error(this, "Error Retrieving RFQ Line items " + (String) incomingRequest.get("RfqHeader_icRfqHeader") + " failed with status: " + process.getStatus());
                    //throw new Exception("Error Canceling RfqLine: " + rfqLine.getRfqLine());
                }
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}