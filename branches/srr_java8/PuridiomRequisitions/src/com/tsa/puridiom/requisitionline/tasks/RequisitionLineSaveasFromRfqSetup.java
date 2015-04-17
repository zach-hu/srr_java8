/**
 * Created on Feb. 14, 2005
 * @author Kelli
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineSaveasFromRfqSetup.java
 * 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class RequisitionLineSaveasFromRfqSetup extends Task 
{
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		
		try
		{
		    RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine") ;
			if (rfqLine == null) 
			{
				throw new Exception("RfqLine entity was not found.");
			}
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			if (requisitionLine == null) 
			{
				throw new TsaException("RequisitionLine entity was not found.");
			}
			String	icHeader = rfqLine.getIcRfqHeader().toString();
			String	icLine = rfqLine.getIcRfqLine().toString() ;
			String	newIcHeader = requisitionLine.getIcReqHeader().toString() ;
			String	newIcLine = requisitionLine.getIcReqLine().toString() ;
			
			if(Utility.isEmpty(icHeader)) 
			{
				throw new Exception("The value for icRfqHeader on the RfqLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for icReqHeader on the RequisitionLine entity cannot be empty.");
			}
			
			if(Utility.isEmpty(icLine)) 
			{
				throw new Exception("The value for icRfqLine on the RfqLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcLine))
			{
				throw new Exception("The value for icReqLine on the RequisitionLine entity cannot be empty.");
			}
			
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("RfqLine_icRfqHeader",icHeader) ;
			incomingRequest.put("ShipTo_icHeader", icHeader);
			incomingRequest.put("ShipTo_icLine", icLine);
			
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocComment_referenceType", "RQL") ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newShipTo_icHeader", newIcHeader);
			incomingRequest.put("newShipTo_icLine", newIcLine);
			
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
