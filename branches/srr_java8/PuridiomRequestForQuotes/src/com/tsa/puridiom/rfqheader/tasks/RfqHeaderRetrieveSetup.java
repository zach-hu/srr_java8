/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class RfqHeaderRetrieveSetup extends Task {
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
			String icHeader = rfh.getIcRfqHeader().toString() ;
			String icLine = "0" ;
			
			if (Utility.isEmpty(icHeader)) {
				throw new Exception("The value for RfqHeader.icRfqHeader cannot be empty.");
			}
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("RfqLine_icRfqHeader",icHeader) ;
			incomingRequest.put("RfqBid_icRfqHeader",icHeader) ;
			incomingRequest.put("RfqVendor_icRfqHeader",icHeader) ;
			incomingRequest.put("RfqNote_icRfqHeader",icHeader) ;			
			incomingRequest.put("RfqQuestion_icRfqHeader",icHeader) ;
			incomingRequest.put("VendorResponse_icRfqHeader",icHeader) ;
			incomingRequest.put("VendorQuestion_icRfqHeader",icHeader) ;
			
			incomingRequest.put("RfqHeader_rfqType", rfh.getRfqType()) ;
			
			if (rfh.getIcReqHeader() != null) {
			    incomingRequest.put("RfqHeader_icReqHeader", String.valueOf(rfh.getIcReqHeader())) ;
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		
		return null ;
	}
}

