/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionHeaderSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (reqHeader == null) {
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			String	icHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader") ;
			String	icLine = "0" ;
			String	newIcHeader = reqHeader.getIcReqHeader().toString() ;
			String	newIcLine = "0" ;
			
			if (Utility.isEmpty(icHeader) || Utility.isEmpty(newIcHeader)) {
				throw new Exception("The value for RequisitionHeader_icReqHeader and icReqHeader on the RequisitionHeader entity cannot be empty.");
			}

			incomingRequest.put("Account_icHeader",icHeader) ;
			incomingRequest.put("Account_icLine",icLine) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icLine",icLine) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			
			incomingRequest.put("newAccount_icHeader", newIcHeader) ;
			incomingRequest.put("newAccount_icLine", newIcLine) ;
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_docSource", "RQH") ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_icHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_documentType", "RQH") ;
						
			incomingRequest.put("RequisitionHeader_requisitionType", reqHeader.getRequisitionType()) ;
			incomingRequest.put("RequisitionLine_requisitionNumber", reqHeader.getRequisitionNumber()) ;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		
		return null ;
	}
}
