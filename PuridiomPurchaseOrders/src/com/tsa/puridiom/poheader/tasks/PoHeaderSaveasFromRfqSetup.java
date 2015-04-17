/**
 * 
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoLineSaveasFromReqSetup.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;


public class PoHeaderSaveasFromRfqSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader") ;
			if (rfqHeader == null) 
			{
				throw new Exception("The RfqHeader entity was not found.");
			}
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader") ;
			if (poHeader == null) 
			{
				throw new Exception("The PoHeader entity was not found.");
			}
			String	icHeader = (String) incomingRequest.get("RfqHeader_icRfqHeader") ;
			String	icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader") ;
			String	icLine = "0" ;
			String	newIcHeader = poHeader.getIcPoHeader().toString() ;
			String	newIcLine = "0" ;
			
			if(Utility.isEmpty(icHeader)) 
			{
				throw new Exception("The value for RfqHeader_icRfqHeader on the RfqHeader entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for PoHeader_icPoHeader on the PoHeader entity cannot be empty.");
			}

			incomingRequest.put("Account_icHeader",icReqHeader) ;
			incomingRequest.put("Account_icLine",icLine) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icLine",icLine) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			
			incomingRequest.put("newAccount_icHeader", newIcHeader) ;
			incomingRequest.put("newAccount_icLine", newIcLine) ;
			incomingRequest.put("newAccount_accountType", "POH") ;
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_icHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_documentType", "POH") ;
						
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
