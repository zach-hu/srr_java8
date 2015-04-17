package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineSetRfqNumberFromHeader extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try {
			List	rfqLineList = (List) incomingRequest.get("rfqLineList");
			String	rfqNumber = (String) incomingRequest.get("RfqHeader_rfqNumber");
			
			for (int i=0; i < rfqLineList.size(); i++) {
				RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
				rfqLine.setRfqNumber(rfqNumber);
				rfqLineList.set(i, rfqLine);
			}
			
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