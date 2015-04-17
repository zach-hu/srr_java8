/*
 * Created on May 10, 2005
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqHeaderGetAuctionStatus extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfqHeader == null) {
				throw new Exception("The RfqHeader entity was not found.");
			}
			
			String	organizationId = (String) incomingRequest.get("organizationId");
			String	dateString = HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), organizationId, "yyyy/MM/dd") + " " + rfqHeader.getBidDueTime();
			long	dueDateTime = Dates.getDate(dateString).getTime();
			long	currentDateTime = (new Date()).getTime();
			String	auctionStatus = "CLOSED";
			
			if (dueDateTime > currentDateTime) {
			    auctionStatus = "OPEN";
			}

			result = auctionStatus;
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
