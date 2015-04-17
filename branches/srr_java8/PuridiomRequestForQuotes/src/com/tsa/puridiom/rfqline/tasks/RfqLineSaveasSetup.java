/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqLineSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		
		try {
			RfqLine rfl = (RfqLine) incomingRequest.get("rfqLine") ;
			if (rfl == null) {
				this.setStatus(Status.FAILED) ;
			}
			else {
				String	icHeader = (String) incomingRequest.get("RfqLine_icRfqHeader") ;
				String	icLine = (String) incomingRequest.get("RfqLine_icRfqLine") ;
				String	newIcHeader = rfl.getIcRfqHeader().toString() ;
				String	newIcLine = rfl.getIcRfqLine().toString() ;
				
				if (Utility.isEmpty(icHeader)) {
					this.setStatus(Status.FAILED);
				}
				else {
					incomingRequest.put("BillTo_icHeader",icHeader) ;
					incomingRequest.put("BillTo_icLine",icLine) ;
					incomingRequest.put("ShipTo_icHeader",icHeader) ;
					incomingRequest.put("ShipTo_icLine",icLine) ;
					incomingRequest.put("DocComment_icHeader",icHeader) ;
					incomingRequest.put("DocComment_icLine",icLine) ;
					incomingRequest.put("RfqBid_icRfqHeader", icHeader);
					incomingRequest.put("RfqBid_icRfqLine", icLine);
					incomingRequest.put("RfqNote_icHeader", icHeader);
					incomingRequest.put("RfqNote_icLine", icLine);
					
					incomingRequest.put("newBillTo_icHeader", newIcHeader) ;
					incomingRequest.put("newBillTo_icLine", newIcLine) ;
					incomingRequest.put("newShipTo_icHeader", newIcHeader) ;
					incomingRequest.put("newShipTo_icLine", newIcLine) ;
					incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
					incomingRequest.put("newDocComment_icLine", newIcLine) ;
					incomingRequest.put("newRfqBid_icRfqHeader", newIcHeader);
					incomingRequest.put("newRfqBid_icRfqLine", newIcLine);
					incomingRequest.put("newRfqNote_icHeader", newIcHeader);
					incomingRequest.put("newRfqNote_icLine", newIcLine);
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
