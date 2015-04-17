/**
 * Created on Feb 18, 2004
 * @author renzo / Kelli
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCheckReqLineOnOrderStatus.java
 *
 */
package com.tsa.puridiom.po.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.poheader.tasks.UserErrors;
import com.tsa.puridiom.property.*;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import java.util.List;
import java.util.Map;

public class PoCheckReqLineOnOrderStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
		    List reqLines = (List) incomingRequest.get("requisitionLineList");
			if(reqLines != null)
			{
			    String	organizationId = (String) incomingRequest.get("organizationId");
			    String	assignByLine = PropertiesManager.getInstance(organizationId).getProperty("MISC", "ASSIGNBYLINE", "Y");
			    PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			    String	owner = poHeader.getOwner();
			    UserErrors userErrors = (UserErrors)incomingRequest.get("userErrors");
			     
				for (int i=0; i < reqLines.size(); i++)
				{
					RequisitionLine reqLine = (RequisitionLine) reqLines.get(i);
					String	assignedBuyer = "";
					
					// Check if requisition was assigned by line item if so, check assigned buyer
					//	if assigned buyer is different then don't do anything with that line
					if (assignByLine.equalsIgnoreCase("Y"))
					{
					    assignedBuyer = reqLine.getAssignedBuyer();
					    if (assignedBuyer.equals(owner) || assignedBuyer.equals("PURCHASING"))
					    {
					        if (reqLine.getStatus().compareTo(DocumentStatus.REQ_APPROVED) >= 0 && reqLine.getStatus().compareTo(DocumentStatus.PO_INPROGRESS) < 0)
					        {
					            if(userErrors == null){	userErrors = new UserErrors();	}
					            userErrors.addError("Do you wish to change the status of open Requisition Line Items to Closed?", PoErrors.USERINPUT);
					            this.setPostAction("exitProcess");
								break;
					        }
					    }
					}
					else
					{
					    if (reqLine.getStatus().compareTo(DocumentStatus.REQ_APPROVED) >= 0 && reqLine.getStatus().compareTo(DocumentStatus.PO_INPROGRESS) < 0)
					    {
				            //if(userErrors == null){	userErrors = new UserErrors();	}
				            //userErrors.addError("Do you wish to change the status of open Requisition Line Items to Closed?", PoErrors.USERINPUT);
							//this.setStatus(Status.COMPLETED);
							break;
					    }
					}
				}
				if (userErrors != null)
				{
				    incomingRequest.put("userErrors", userErrors);
				}
			}
			
		    if (this.getStatus() != Status.COMPLETED)
		    {
		        this.setStatus(Status.SUCCEEDED);
		    }
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		
		return null;
	}

}
