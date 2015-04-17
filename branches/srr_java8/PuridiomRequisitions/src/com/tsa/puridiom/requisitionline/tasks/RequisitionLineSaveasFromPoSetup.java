/**
 * Created on Jan. 31, 2005
 * @author Kelli
 * project: HiltonRequisitions
 * com.tsa.puridiom.requisitionline.tasks.RequisitionLineSaveasFromPoSetup.java
 *
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

public class RequisitionLineSaveasFromPoSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine") ;
			if (poLine == null)
			{
				throw new Exception("PoLine entity was not found.");
			}
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine") ;
			if (requisitionLine == null)
			{
				throw new TsaException("RequisitionLine entity was not found.");
			}
			String	icHeader = poLine.getIcPoHeader().toString();
			String	icLine = poLine.getIcPoLine().toString() ;
			String	icLineAccount = poLine.getIcAccount().toString() ;
			String	newIcHeader = requisitionLine.getIcReqHeader().toString() ;
			String	newIcLine = requisitionLine.getIcReqLine().toString() ;
			String	newIcLineAccount = requisitionLine.getIcAccount().toString() ;

			if(Utility.isEmpty(icHeader))
			{
				throw new Exception("The value for icPoHeader on the PoLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for icReqHeader on the RequisitionLine entity cannot be empty.");
			}

			if(Utility.isEmpty(icLine))
			{
				throw new Exception("The value for icPoLine on the PoLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcLine))
			{
				throw new Exception("The value for icReqLine on the RequisitionLine entity cannot be empty.");
			}

			if(Utility.isEmpty(icLineAccount))
			{
				throw new Exception("The value for icAccount on the PoLine entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcLineAccount))
			{
				throw new Exception("The value for icAccount on the RequisitionLine entity cannot be empty.");
			}

			incomingRequest.put("Account_icHeader",icHeader) ;
			incomingRequest.put("Account_icLine",icLine) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("DocAttachment_icLine",icLine) ;
			incomingRequest.put("PoLine_icPoHeader",icHeader) ;
			incomingRequest.put("PoLine_icPoLine",icLine) ;
			incomingRequest.put("Schedule_icHeader",icHeader) ;
			incomingRequest.put("ShipTo_icHeader", icHeader);
			incomingRequest.put("ShipTo_icLine", icLine);
			incomingRequest.put("BillTo_icHeader", icHeader);
			incomingRequest.put("BillTo_icLine", icLine);

			incomingRequest.put("newAccount_icHeader", newIcHeader) ;
			incomingRequest.put("newAccount_icLine", newIcLine) ;
			incomingRequest.put("newAccount_accountType", "RQL") ;
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocComment_referenceType", "RQL") ;
			incomingRequest.put("newDocText_referenceType", "RQL");
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_docSource", "RQL") ;
			incomingRequest.put("newRequisitionLine_icReqHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_icHeader", newIcHeader) ;
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
