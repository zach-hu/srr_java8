/*
 * Created on May 5, 2005
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqHeaderExtendAuctionSetup extends Task {
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
			String	dateString = rfqHeader.getDueDate() + " " + rfqHeader.getBidDueTime();
			Date	date = Dates.getDate(dateString);
			long	dueDateTime = date.getTime();
			long	currentDateTime = (new Date()).getTime();
			long	secondsRemaining = 0;
			int	extendSeconds = rfqHeader.getExtendMinutes().intValue() * 60; 
			Date	dateCheck = Dates.addSeconds(date, (extendSeconds*-1));
			long	dateTimeCheck = dateCheck.getTime();
			
			if (dueDateTime >= currentDateTime) {
			    secondsRemaining = (dueDateTime - currentDateTime)/100000;
			}
			
			if (secondsRemaining <= extendSeconds && extendSeconds > 0) {
			    // Extend Auction
			    Date newDateTime = Dates.addSeconds(new Date(), extendSeconds);
			    String	newDueDate = HiltonUtility.getFormattedDate(newDateTime, organizationId, "yyyy/MM/dd");
			    String	newBidDueTime = HiltonUtility.getFormattedDate(newDateTime, organizationId, "HH:mm:ss");
			    
			    incomingRequest.put("extendAuctionRequired", "true");
			    incomingRequest.put("RfqHeader_dueDate", newDueDate);
			    incomingRequest.put("RfqHeader_bidDueTime", newBidDueTime);
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
