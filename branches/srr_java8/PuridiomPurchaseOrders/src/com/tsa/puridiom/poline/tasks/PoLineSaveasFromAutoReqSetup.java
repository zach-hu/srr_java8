/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoLineSaveasFromReqSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineSaveasFromAutoReqSetup extends Task
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
			PoLine poLine = (PoLine) incomingRequest.get("poLine") ;
			if (poLine == null)
			{
				throw new TsaException("PoLine entity was not found.");
			}
			String	icHeader = reqLine.getIcReqHeader().toString();
			String	icLine = reqLine.getIcReqLine().toString() ;
			String	newIcHeader = poLine.getIcPoHeader().toString() ;
			String	newIcLine = poLine.getIcPoLine().toString() ;

			if(Utility.isEmpty(icHeader))
			{
				throw new Exception("The value for RequisitionLine_icReqHeader on the RequisitionLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for PoLine_icPoHeader on the PoLine entity cannot be empty.");
			}

			if(Utility.isEmpty(icLine))
			{
				throw new Exception("The value for RequisitionLine_icReqLine on the RequisitionLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcLine))
			{
				throw new Exception("The value for PoLine_icPoLine on the PoLine entity cannot be empty.");
			}


			incomingRequest.put("Account_icHeader",icHeader) ;
			incomingRequest.put("Account_icLine",icLine) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icLine",icLine) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
			incomingRequest.put("BillTo_icHeader", icHeader);
			incomingRequest.put("BillTo_icLine", icLine);
			incomingRequest.put("ShipTo_icHeader", icHeader);
			incomingRequest.put("ShipTo_icLine", icLine);

			incomingRequest.put("newAccount_icHeader", newIcHeader) ;
			incomingRequest.put("newAccount_icLine", newIcLine) ;
			incomingRequest.put("newAccount_accountType", "POL") ;
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newBillTo_icHeader", newIcHeader);
			incomingRequest.put("newBillTo_icLine", newIcLine);
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
