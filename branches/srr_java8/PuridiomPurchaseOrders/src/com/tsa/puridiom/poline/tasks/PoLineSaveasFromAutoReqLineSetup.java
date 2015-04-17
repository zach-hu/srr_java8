/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoLineSaveasFromReqSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineSaveasFromAutoReqLineSetup extends Task
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
			String	newIcHeader = poLine.getIcPoHeader().toString() ;
			String	newIcLine = poLine.getIcPoLine().toString() ;

			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for PoLine_icPoHeader on the PoLine entity cannot be empty.");
			}

			if(Utility.isEmpty(newIcLine))
			{
				throw new Exception("The value for PoLine_icPoLine on the PoLine entity cannot be empty.");
			}


			incomingRequest.put("accountList", reqLine.getAccountList());
			incomingRequest.put("docCommentList", reqLine.getDocCommentList()) ;
			incomingRequest.put("billToList", reqLine.getBillToList());
			incomingRequest.put("shipToList", reqLine.getShipToList());

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
