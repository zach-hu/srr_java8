/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.*;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator
 */
public class RequisitionHeaderRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		this.setStatus(Status.SUCCEEDED) ;

		RequisitionHeader rqh = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
		if (rqh == null) {
			this.setStatus(Status.FAILED);
			throw new Exception("Requisition could not be found.");
		} else {
			String icHeader = rqh.getIcReqHeader().toString() ;
			String icLine = "0" ;

			if (Utility.isEmpty(icHeader)) {
				this.setStatus(Status.FAILED);
			} else {
				incomingRequest.put("Schedule_icHeader",icHeader) ;
				incomingRequest.put("Account_icHeader",icHeader) ;
				incomingRequest.put("Account_icLine",icLine) ;
				incomingRequest.put("DocComment_icHeader",icHeader) ;
				incomingRequest.put("DocComment_icLine",icLine) ;
				incomingRequest.put("DocAttachment_icHeader",icHeader) ;
				incomingRequest.put("DocAttachment_icLine",icLine) ;
				incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;

				incomingRequest.put("RequisitionHeader_requisitionType",rqh.getRequisitionType()) ;
			}
		}

		return null ;
	}
}
