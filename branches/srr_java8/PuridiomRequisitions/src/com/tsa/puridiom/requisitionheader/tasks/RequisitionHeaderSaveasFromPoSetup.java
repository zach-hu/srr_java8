/**
 * Created on March 24, 2004
 * @author Kelli
 * project: HiltonRequestForQuotes
 * com.tsa.puridiom.rfqheader.tasks.RfqHeaderSaveasFromReqSetup.java
 *
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

public class RequisitionHeaderSaveasFromPoSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader") ;
			if (requisitionHeader == null)
			{
				throw new Exception("The RequisitionHeader entity was not found.");
			}
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader") ;
			if (poHeader == null)
			{
				throw new Exception("The PoHeader entity was not found.");
			}
			String	icHeader = (String) incomingRequest.get("PoHeader_icPoHeader") ;
			String	icLine = "0" ;
			String	newIcHeader = requisitionHeader.getIcReqHeader().toString() ;
			String	newIcLine = "0" ;

			if(Utility.isEmpty(icHeader))
			{
				throw new Exception("The value for icPoHeader on the PoHeader entity cannot be empty.");
			}
			if(Utility.isEmpty(newIcHeader))
			{
				throw new Exception("The value for icReqHeader on the RequisitionHeader entity cannot be empty.");
			}

			incomingRequest.put("Account_icHeader",icHeader) ;
			incomingRequest.put("Account_icLine",icLine) ;
			incomingRequest.put("DocComment_icHeader",icHeader) ;
			incomingRequest.put("DocComment_icLine",icLine) ;
			incomingRequest.put("DocAttachment_icHeader",icHeader) ;
			incomingRequest.put("PoLine_icPoHeader",icHeader) ;
			incomingRequest.put("Schedule_icHeader",icHeader) ;

			incomingRequest.put("newAccount_icHeader", newIcHeader) ;
			incomingRequest.put("newAccount_icLine", newIcLine) ;
			incomingRequest.put("newAccount_accountType", "RQH") ;
			incomingRequest.put("newDocComment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocComment_icLine", newIcLine) ;
			incomingRequest.put("newDocComment_referenceType", "RQH") ;
			incomingRequest.put("newDocText_referenceType", "RQH");
			incomingRequest.put("newDocAttachment_icHeader", newIcHeader) ;
			incomingRequest.put("newDocAttachment_icLine", newIcLine) ;
			incomingRequest.put("newDocAttachment_docSource", "RQH") ;
			incomingRequest.put("newPoLine_icPoHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_icHeader", newIcHeader) ;
			incomingRequest.put("newSchedule_documentType", "RQH") ;

			if (requisitionHeader.getRequisitionType().equalsIgnoreCase("V"))
			{
				incomingRequest.put("saveAccountTotals", "N");
			}

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
