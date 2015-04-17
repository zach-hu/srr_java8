/**
 * Created on March 24, 2004
 * @author Kelli
 * project: HiltonRequestForQuotes
 * com.tsa.puridiom.rfqheader.tasks.RfqHeaderSaveasFromReqSetup.java
 * 
 */
package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

public class RfqHeaderSaveasFromReqSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		
		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (reqHeader == null) 
			{
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfqHeader == null) 
			{
				throw new Exception("The RfqHeader entity was not found.");
			}
			String	icHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader") ;
			String	icLine = "0" ;
			String	newIcHeader = rfqHeader.getIcRfqHeader().toString() ;
			String	newIcLine = "0" ;
			
			if(Utility.isEmpty(icHeader)) 
			{
				throw new Exception("The value for RequisitionHeader_icReqHeader on the RequisitionHeader entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for RfqHeader_icRfqHeader on the RfqHeader entity cannot be empty.");
			}

			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocComment_referenceType", "RFH") ;
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_icHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_documentType", "RFH") ;
						
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
