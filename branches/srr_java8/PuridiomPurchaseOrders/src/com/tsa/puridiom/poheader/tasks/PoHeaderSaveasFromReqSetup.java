/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoLineSaveasFromReqSetup.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;


public class PoHeaderSaveasFromReqSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			/*RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (reqHeader == null)
			{
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			*/
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader") ;
			if (poHeader == null)
			{
				throw new Exception("The PoHeader entity was not found.");
			}
			String	icHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader") ;
			String	icLine = "0" ;
			String	newIcHeader = poHeader.getIcPoHeader().toString() ;
			String	newIcLine = "0" ;

			if(Utility.isEmpty(icHeader))
			{
				throw new Exception("The value for RequisitionHeader_icReqHeader on the RequisitionHeader entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for PoHeader_icPoHeader on the PoHeader entity cannot be empty.");
			}

			incomingRequest.put("formtype", "REQ");

			incomingRequest.put("Account_icHeader",icHeader) ;
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
			incomingRequest.put("newDocComment_referenceType", "POH") ;
			incomingRequest.put("newDocText_referenceType", "POH");
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_docSource", "POH") ;
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
