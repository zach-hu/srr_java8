/**
 * Created on March 24, 2004
 * @author Kelli
 * project: HiltonRequestForQuotes
 * com.tsa.puridiom.rfqline.tasks.RfqLineSaveasFromReqSetup.java
 * 
 */
package com.tsa.puridiom.rfqline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class RfqLineSaveasFromReqSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		
		try
		{
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			if (reqLine == null) 
			{
				throw new Exception("RequisitionLine entity was not found.");
			}
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine") ;
			if (rfqLine == null) 
			{
				throw new TsaException("RfqLine entity was not found.");
			}
			String	icHeader = reqLine.getIcReqHeader().toString();
			String	icLine = reqLine.getIcReqLine().toString() ;
			String	newIcHeader = rfqLine.getIcRfqHeader().toString() ;
			String	newIcLine = rfqLine.getIcRfqLine().toString() ;
			
			if(Utility.isEmpty(icHeader)) 
			{
				throw new Exception("The value for RequisitionLine_icReqHeader on the RequisitionLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for RfqLine_icRfqHeader on the RfqLine entity cannot be empty.");
			}
			
			if(Utility.isEmpty(icLine)) 
			{
				throw new Exception("The value for RequisitionLine_icReqLine on the RequisitionLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcLine))
			{
				throw new Exception("The value for RfqLinee_icRfqLine on the RfqLine entity cannot be empty.");
			}
			
			
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icLine",icLine) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
			incomingRequest.put("ShipTo_icHeader", icHeader);
			incomingRequest.put("ShipTo_icLine", icLine);
			
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocComment_referenceType", "RFL") ;
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newShipTo_icHeader", newIcHeader);
			incomingRequest.put("newShipTo_icLine", newIcLine);
			
			incomingRequest.put("retrieveForSave", "Y");
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
