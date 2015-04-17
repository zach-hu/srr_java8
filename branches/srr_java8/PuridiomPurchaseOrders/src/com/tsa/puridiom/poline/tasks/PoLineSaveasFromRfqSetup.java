/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoLineSaveasFromReqSetup.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;
import java.util.Map;


public class PoLineSaveasFromRfqSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine") ;
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			if (rfqLine == null)
			{
				throw new Exception("RfqLine entity was not found.");
			}
			PoLine poLine = (PoLine) incomingRequest.get("poLine") ;
			if (poLine == null)
			{
				throw new TsaException("PoLine entity was not found.");
			}
			String	icHeader = rfqLine.getIcRfqHeader().toString();
			String	icLine = rfqLine.getIcRfqLine().toString() ;
			BigDecimal   icAccount = rfqLine.getIcAccount() ;

			String	newIcHeader = poLine.getIcPoHeader().toString() ;
			String	newIcLine = poLine.getIcPoLine().toString() ;
			String	icReqHeader = icHeader;
			String	icReqLine = icLine;

			if (requisitionLine != null) {
				icReqHeader = requisitionLine.getIcReqHeader().toString();
				icReqLine = requisitionLine.getIcReqLine().toString();
			}

			if (icAccount == null) icAccount = new BigDecimal("0") ;
			if (icAccount.compareTo(new BigDecimal(0)) > 0) {
				poLine.setIcAccount(poLine.getIcPoLine()) ;
			} else {
				poLine.setIcAccount(new BigDecimal(0)) ;
			}

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


			incomingRequest.put("Account_icHeader",icReqHeader) ;
			incomingRequest.put("Account_icLine",icReqLine) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icLine",icLine) ;
			incomingRequest.put("RequisitionLine_icReqHeader",icHeader) ;
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
