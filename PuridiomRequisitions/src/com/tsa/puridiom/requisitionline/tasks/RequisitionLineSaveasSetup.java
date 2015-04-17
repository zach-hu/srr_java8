/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class RequisitionLineSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		try {
			RequisitionLine rql = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			if (rql == null) {
				this.setStatus(Status.FAILED) ;
			}
			else {
				String	icHeader = (String) incomingRequest.get("RequisitionLine_icReqHeader") ;
				String	icLine = (String) incomingRequest.get("RequisitionLine_icReqLine") ;
				String	newIcHeader = rql.getIcReqHeader().toString() ;
				String	newIcLine = rql.getIcReqLine().toString() ;

				if (Utility.isEmpty(icHeader)) {
					this.setStatus(Status.FAILED);
				}
				else {
					incomingRequest.put("BillTo_icHeader",icHeader) ;
					incomingRequest.put("BillTo_icLine",icLine) ;
					incomingRequest.put("ShipTo_icHeader",icHeader) ;
					incomingRequest.put("ShipTo_icLine",icLine) ;
					incomingRequest.put("Account_icHeader",icHeader) ;
					incomingRequest.put("Account_icLine",icLine) ;
					incomingRequest.put("DocComment_icHeader",icHeader) ;
					incomingRequest.put("DocComment_icLine",icLine) ;
					incomingRequest.put("DocAttachment_icHeader",icHeader) ;
					incomingRequest.put("DocAttachment_icLine",icLine) ;

					incomingRequest.put("newBillTo_icHeader", newIcHeader) ;
					incomingRequest.put("newBillTo_icLine", newIcLine) ;
					incomingRequest.put("newShipTo_icHeader", newIcHeader) ;
					incomingRequest.put("newShipTo_icLine", newIcLine) ;
					incomingRequest.put("newAccount_icHeader", newIcHeader) ;
					incomingRequest.put("newAccount_icLine", newIcLine) ;
					incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
					incomingRequest.put("newDocComment_icLine", newIcLine) ;
					incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
					incomingRequest.put("newDocAttachment_icLine",newIcLine) ;
					incomingRequest.put("newDocAttachment_docSource", "RQL") ;

					incomingRequest.put("retrieveForSave", "Y");
				}
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null ;
	}
}
