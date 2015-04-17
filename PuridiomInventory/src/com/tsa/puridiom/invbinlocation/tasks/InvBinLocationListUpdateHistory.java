/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class InvBinLocationListUpdateHistory extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			List binList = (List) incomingRequest.get("invBinLocationList") ;
			ReceiptLine  recLine = (ReceiptLine) incomingRequest.get("receiptLine") ;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			for (int  i = 0; i < binList.size(); i++) {
				InvBinLocation binLoc = (InvBinLocation) binList.get(i) ;
				if  (binLoc !=  null) {
					incomingRequest.put("newInvBinLocation", binLoc) ;
					incomingRequest.put("newReceiptLine", recLine) ;
					PuridiomProcess process = processLoader.loadProcess("inventory-receipt-history.xml");
					process.executeProcess(incomingRequest);
				}
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
