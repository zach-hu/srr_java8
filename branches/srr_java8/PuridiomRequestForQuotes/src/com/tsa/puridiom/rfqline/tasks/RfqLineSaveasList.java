package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineSaveasList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("rfqline-saveas.xml");
			List	originalRfqLineList = (List) incomingRequest.get("originalRfqLineList");
			List	rfqLineList = new ArrayList();
				
			for (int i=0; i < originalRfqLineList.size(); i++) {
				RfqLine originalRfqLine = (RfqLine) originalRfqLineList.get(i);

				incomingRequest.put("RfqLine_icRfqLine", originalRfqLine.getIcRfqLine().toString());
				incomingRequest.put("RfqLine_icRfqHeader", originalRfqLine.getIcRfqHeader().toString());
				incomingRequest.put("RfqNote_icLine", originalRfqLine.getIcRfqLine().toString());
				incomingRequest.put("RfqNote_icHeader", originalRfqLine.getIcRfqHeader().toString());
				incomingRequest.put("originalRfqLine", originalRfqLine);
				
				process.executeProcess(incomingRequest);
				
				if (process.getStatus() < Status.SUCCEEDED) {
					throw new Exception("Rfq Line save as process failed.");
				}
				
				RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
				rfqLineList.add(rfqLine);
			}

			incomingRequest.put("rfqLineList", rfqLineList);
			
			result = rfqLineList;			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}