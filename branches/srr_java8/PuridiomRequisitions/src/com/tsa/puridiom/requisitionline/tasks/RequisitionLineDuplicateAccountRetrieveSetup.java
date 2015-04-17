/*
 * Created on October 20, 2004
 */
package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

import java.util.*;

/**
 * @author Administrator
 */
public class RequisitionLineDuplicateAccountRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
			String icHeader = "";
			String icLine = "";

			if (incomingRequest.containsKey("requisitionLine")) {
				RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine");
			    if (rql != null) {
			        icHeader = rql.getIcReqHeader().toString();
			        icLine = rql.getIcReqLine().toString();
			    }
			}
			if (Utility.isEmpty(icHeader) && Utility.isEmpty(icLine)) {
			    icHeader = (String) incomingRequest.get("RequisitionLine_icReqHeader");
			    icLine = (String) incomingRequest.get("RequisitionLine_icReqLine");
			}

			incomingRequest.put("newAccount_icHeader",icHeader);
	        incomingRequest.put("newAccount_icLine",icLine);

	        this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}
