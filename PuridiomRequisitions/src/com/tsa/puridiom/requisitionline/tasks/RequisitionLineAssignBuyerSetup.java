/*
 * Created on April 19, 2005
 */
package com.tsa.puridiom.requisitionline.tasks;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;
/**
 * @author Kelli
 */
public class RequisitionLineAssignBuyerSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		try {
			String	assignTo =(String) incomingRequest.get("assignTo");
            String userTimeZone =(String) incomingRequest.get("userTimeZone");

			incomingRequest.put("RequisitionLine_assignedBuyer", assignTo);
			incomingRequest.put("RequisitionLine_assignedDate", Dates.today("", userTimeZone));
			incomingRequest.put("historyStatus", HistoryStatus.BUYER_ASSIGNMENT);

			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine != null) {
			    incomingRequest.put("RequisitionHeader_icReqHeader", String.valueOf(requisitionLine.getIcReqHeader()));
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
		    this.setStatus(Status.FAILED) ;
		    throw e;
		}
		return null ;
	}
}
