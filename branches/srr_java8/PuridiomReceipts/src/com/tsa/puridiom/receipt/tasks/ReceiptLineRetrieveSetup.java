/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

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
			
			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("DocComment_icHeader",icHeader) ;
				incomingRequest.put("DocComment_icLine",icLine) ;
			}
		}
		
		return null ;
	}
}
