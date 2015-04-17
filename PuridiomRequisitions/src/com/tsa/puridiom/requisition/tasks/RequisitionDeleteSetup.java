/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.*;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator 
 */
public class RequisitionDeleteSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
		
		if (Utility.isEmpty(icReqHeader)) {
			this.setStatus(Status.FAILED);
		} else {
			incomingRequest.put("Schedule_icHeader",icReqHeader) ;
			incomingRequest.put("BillTo_icHeader",icReqHeader) ;
			incomingRequest.put("ShipTo_icHeader",icReqHeader) ;
			incomingRequest.put("Account_icHeader",icReqHeader) ;
			incomingRequest.put("DocComment_icHeader",icReqHeader) ;
			incomingRequest.put("DocAttachment_icHeader",icReqHeader) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icReqHeader) ;
			RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (rqh != null) {
				incomingRequest.put("RequisitionHeader_requisitionType",rqh.getRequisitionType()) ;
			}
			this.setStatus(dbs.getStatus());
		}
		
		return null ;
	}
}
