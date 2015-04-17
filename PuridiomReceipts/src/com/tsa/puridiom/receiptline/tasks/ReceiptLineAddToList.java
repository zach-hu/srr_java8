/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineAddToList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		List   receiptLineList = new ArrayList() ;
		this.setStatus(Status.SUCCEEDED) ;

		ReceiptLine line = (ReceiptLine) incomingRequest.get("receiptLine") ;
		if (line == null) {
			this.setStatus(Status.FAILED) ;
		} else {
			receiptLineList.add(line) ;
		}

		return receiptLineList ;
	}
}
