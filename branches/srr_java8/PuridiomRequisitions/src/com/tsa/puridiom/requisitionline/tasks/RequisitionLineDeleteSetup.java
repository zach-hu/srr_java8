/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.*;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class RequisitionLineDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icReqHeader = (String) incomingRequest.get("RequisitionLine_icReqHeader");
		String icReqLine = (String) incomingRequest.get("RequisitionLine_icReqLine");
		
		if (Utility.isEmpty(icReqHeader) || Utility.isEmpty(icReqLine)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("BillTo_icHeader",icReqHeader) ;
			incomingRequest.put("BillTo_icLine",icReqLine) ;
			incomingRequest.put("ShipTo_icHeader",icReqHeader) ;
			incomingRequest.put("ShipTo_icLine",icReqLine) ;
			incomingRequest.put("Account_icHeader",icReqHeader) ;
			incomingRequest.put("Account_icLine",icReqLine) ;
			incomingRequest.put("DocComment_icHeader",icReqHeader) ;
			incomingRequest.put("DocComment_icLine",icReqLine) ;
			this.setStatus(dbs.getStatus());
		}
		
		return null ;
	}
}
