package com.tsa.puridiom.receiptline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		try {
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

			String icReceiptHeader = (String) incomingRequest.get("ReceiptLine_icRecHeader");
			String icReceiptLine = (String) incomingRequest.get("ReceiptLine_icRecLine");

			if (Utility.isEmpty(icReceiptHeader) || Utility.isEmpty(icReceiptLine)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("DocComment_icHeader",icReceiptHeader) ;
				incomingRequest.put("DocComment_icLine",icReceiptLine) ;
				this.setStatus(dbs.getStatus());
			}
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
