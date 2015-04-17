/*
 * Created on April 19, 2005
 */
package com.tsa.puridiom.receiptline.tasks;
import com.tsa.puridiom.historylog.tasks.HistoryStatus;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;
/**
 * @author Kelli
 */
public class ReceiptLineAssignEngineerSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		try {
			String	assignTo =(String) incomingRequest.get("assignToEngine");
            String userTimeZone =(String) incomingRequest.get("userTimeZone");

			incomingRequest.put("ReceiptLine_engineerAssigned", assignTo);
			incomingRequest.put("ReceiptLine_receiptDate", Dates.today("", userTimeZone));//check
			incomingRequest.put("historyStatus", HistoryStatus.ENGINEER_ASSIGNMENT);
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			if (receiptLine != null) {
			    incomingRequest.put("ReceiptHeader_icRecHeader", String.valueOf(receiptLine.getIcRecHeader()));
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
