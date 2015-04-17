package com.tsa.puridiom.po.approvals.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoCheckWarrantAmounts extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Boolean enoughWarrant = Boolean.FALSE;
		try
		{
			String poApprovers = (String)incomingRequest.get("ApprovalLog_approvers");
			String poApproversArray[] = poApprovers.split(";");
			String organizationId = (String)incomingRequest.get("organizationId");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String buyer = poHeader.getBuyerCode();
			BigDecimal buyerWarrantAmt = UserManager.getInstance().getUser(organizationId, buyer).getWarrantAmount();
			if(buyerWarrantAmt.compareTo(poHeader.getTotal()) > 0)
			{
				enoughWarrant = Boolean.TRUE;
			}
			else
			{
				for (int i = 0; i < poApproversArray.length; i++)
				{
					UserProfile userP = UserManager.getInstance().getUser(organizationId, poApproversArray[i]);
					BigDecimal warrantAmount = userP.getWarrantAmount();
					if(warrantAmount.compareTo(poHeader.getTotal()) > -1)
					{
						enoughWarrant = Boolean.TRUE;
						i = poApproversArray.length;
					}
				}
			}
			if(!enoughWarrant.booleanValue())
			{
				incomingRequest.put("errorMsg", "Please Select a user with enough warrant amount($" + poHeader.getTotal().toString() +
						") to approve this Order!");
				this.setPostAction("exitProcess");
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Please Select a user with enough warrant amount to approve this requisition!", e);
		}

		return enoughWarrant;
	}
}
