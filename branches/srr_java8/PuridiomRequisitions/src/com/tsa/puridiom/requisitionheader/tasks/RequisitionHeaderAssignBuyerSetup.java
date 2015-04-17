/*
 * Created on April 19, 2005
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.Map;

import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
/**
 * @author Kelli
 */
public class RequisitionHeaderAssignBuyerSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		try {
			String	assignTo =(String) incomingRequest.get("assignTo");
            String  userTimeZone =(String) incomingRequest.get("userTimeZone");

			incomingRequest.put("RequisitionHeader_assignedBuyer", assignTo);
			incomingRequest.put("RequisitionHeader_assignedDate", Dates.today("", userTimeZone));

			incomingRequest.put("RequisitionLine_icReqHeader",(String)incomingRequest.get("RequisitionHeader_icReqHeader")) ;
			
			incomingRequest.put("historyStatus", HistoryStatus.BUYER_ASSIGNMENT);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
		    this.setStatus(Status.FAILED) ;
		    throw e;
		}
		return null ;
	}
}
