/*
 * Created on Dec 9, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqHeaderSaveasSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqHeader rfh = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfh == null) {
				throw new Exception("The RfqHeader entity was not found.");
			}
			String	icHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader") ;
			String	icLine = "0" ;
			String	newIcHeader = rfh.getIcRfqHeader().toString() ;
			String	newIcLine = "0" ;
			
			if (Utility.isEmpty(icHeader) || Utility.isEmpty(newIcHeader)) {
				throw new Exception("The value for RfqHeader_icRfqHeader and icRfqHeader on the RfqHeader entity cannot be empty.");
			}
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("RfqLine_icRfqHeader",icHeader) ;
			incomingRequest.put("RfqVendor_icRfqHeader",icHeader) ;
			incomingRequest.put("RfqQuestion_icRfqHeader",icHeader) ;
			incomingRequest.put("RfqNote_icHeader",icHeader) ;
			incomingRequest.put("RfqNote_icLine","0") ;
			incomingRequest.put("VendorResponse_icRfqHeader",icHeader) ;
			
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_docSource", "RFH");
			incomingRequest.put("newRfqLine_icRfqHeader", newIcHeader) ;
			incomingRequest.put("newRfqVendor_icRfqHeader", newIcHeader) ;
			incomingRequest.put("newRfqQuestion_icRfqHeader", newIcHeader) ;
			incomingRequest.put("newRfqNote_icHeader", newIcHeader) ;
			incomingRequest.put("newRfqNote_icLine", newIcLine) ;
			incomingRequest.put("newSchedule_icHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_documentType", "RFH") ;
			incomingRequest.put("newVendorResponse_icRfqHeader", newIcHeader) ;
			
			incomingRequest.put("RfqHeader_rfqType", rfh.getRfqType()) ;
			incomingRequest.put("RfqLine_rfqNumber", rfh.getRfqNumber()) ;
			
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
