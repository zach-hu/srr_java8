package com.tsa.puridiom.emails.req;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionApprovalTextVersion extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			String organizationId = (String)incomingRequest.get("organizationId");
			StringBuffer txt = new StringBuffer();
			txt.append("Requisition Number:" + reqHeader.getRequisitionNumber());
			txt.append("\r\n");
			txt.append("Buyer: " + UserManager.getInstance().getUser(organizationId, reqHeader.getAssignedBuyer()).getDisplayName());
			txt.append("\r\n");
			txt.append("Requisition Date: " + HiltonUtility.getFormattedDate(reqHeader.getRequiredDate(), organizationId));
			txt.append("\r\n");

			List reqLineList = reqHeader.getRequisitionLineList();
			for(int i = 0; i < reqLineList.size(); i++)
			{
				RequisitionLine reqLine = (RequisitionLine)reqLineList.get(i);
				String temp = reqLine.getDescription();
				if(temp.length() > 20)
				{
					temp = reqLine.getDescription().substring(0, 20);
				}
				txt.append(reqLine.getLineNumber() + ". " + reqLine.getItemNumber() + " " + temp +  " Qty: " + HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), organizationId));
				txt.append("\r\n");
			}
			txt.append("Total: " + HiltonUtility.getFormattedDollar(reqHeader.getTotal(), organizationId));
			txt.append("\r\n");
			//txt.append("<--" + reqHeader.getIcReqHeader().toString());
			ret = txt.toString();


			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Error creating text version of Req Html Version.", e);
		}
		return ret;
	}


}
