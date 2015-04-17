package com.tsa.puridiom.rfqheader.tasks;

import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqHeaderSetApprovalInformation extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			String	userId = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("RfqHeader_appBy", userId);
			incomingRequest.put("RfqHeader_appDate", Dates.today("", userTimeZone));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}