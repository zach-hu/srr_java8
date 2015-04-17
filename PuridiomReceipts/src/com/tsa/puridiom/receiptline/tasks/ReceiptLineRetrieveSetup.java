/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Kelli
 */
public class ReceiptLineRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		this.setStatus(Status.SUCCEEDED) ;

		ReceiptLine line = (ReceiptLine) incomingRequest.get("receiptLine") ;
		if (line == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			String icHeader = line.getIcRecHeader().toString() ;
			String icLine = line.getIcRecLine().toString() ;
			String icPoLine = line.getIcPoLine().toString();
			String icReqLine = line.getIcReqLine().toString();
			String icMsrLine = line.getIcLineHistory().toString() ;

			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("DocComment_icHeader",icHeader) ;
				incomingRequest.put("DocComment_icLine",icLine) ;
				incomingRequest.put("PoLine_icPoLine",icPoLine) ;
				incomingRequest.put("RequisitionLine_icReqLine",icReqLine) ;
				incomingRequest.put("RequisitionLine_icLineHistory",icMsrLine) ;
				incomingRequest.put("InspectionHeader_icMsrLine", icMsrLine) ;
				incomingRequest.put("InvBinLocation_tempIc",line.getIcRecLine().toString() ) ;
				if (!incomingRequest.containsKey("ReceiptHeader_icRecHeader")) {
					incomingRequest.put("ReceiptHeader_icRecHeader", icHeader) ;
				}
			}
		}

		return null ;
	}
}
