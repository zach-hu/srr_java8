package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;

import java.util.Date;
import java.util.Map;

public class RfqHeaderSetDueDateFromEventDates extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			Date dueDate = rfqHeader.getDueDate();
			String	dueTime = rfqHeader.getBidDueTime();

			if (rfqHeader.getAuctionEvent().equalsIgnoreCase("Y")) {
			    dueDate = rfqHeader.getAuctionEndDate();
			    dueTime = rfqHeader.getAuctionEndTime();
			}
			else if (rfqHeader.getBidEvent().equalsIgnoreCase("Y")) {
			    dueDate = rfqHeader.getBidEndDate();
			    dueTime = rfqHeader.getBidEndTime();
			}
			else if (rfqHeader.getQaEvent().equalsIgnoreCase("Y")) {
			    dueDate = rfqHeader.getQaEndDate();
			    dueTime = rfqHeader.getQaEndTime();
			}

            String dueDateString = HiltonUtility.getFormattedDate(dueDate, (String) incomingRequest.get("organizationId"));

		    incomingRequest.put("RfqHeader_dueDate", dueDateString);
		    incomingRequest.put("RfqHeader_bidDueTime", String.valueOf(dueTime));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}