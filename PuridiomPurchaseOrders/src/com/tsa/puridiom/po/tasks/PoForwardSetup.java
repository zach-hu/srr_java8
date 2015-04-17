/**
 * Created on Feb 18, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoForwardSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			incomingRequest.put("xmlOrder","N") ;
			String forwardTo = (String)incomingRequest.get("forwardTo");
			String userId = (String)incomingRequest.get("userId");
			if(Utility.isEmpty(forwardTo)) {
				List routingList = (List) incomingRequest.get("routingList");
				if (routingList != null && routingList.size() > 0) {
					for (int ir = 0; ir < routingList.size(); ir++) {
						ApprovalLog approvalLog = (ApprovalLog) routingList.get(ir);
						forwardTo = approvalLog.getCallForward();
						if (!forwardTo.equals(userId)) {
							break;
						}
					}
				}
			}
			if (Utility.isEmpty(forwardTo))
			{
				incomingRequest.put("awarded", new Boolean(true));
			}
			else
			{
				incomingRequest.put("awarded", new Boolean(false));
				incomingRequest.put("forwardTo", forwardTo);
			}
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if(Utility.isEmpty(Utility.tsaToString(poHeader.getRevisionNumber())))
			{
				incomingRequest.put("revision", new Boolean(true));
			}
			else
			{
				if(poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) < 1)
				{
					incomingRequest.put("revision", new Boolean(false));
				}
				else
				{
					incomingRequest.put("revision", new Boolean(true));
				}
			}
			incomingRequest.put("oldStatus", poHeader.getStatus());
			if(poHeader.getIcReqHeader() != null)
			{
				incomingRequest.put("fromReq", new Boolean(true));
				incomingRequest.put("fromRfq", new Boolean(false));
			}

			if(poHeader.getIcRfqHeader() != null)
			{
				incomingRequest.put("fromReq", new Boolean(false));
				incomingRequest.put("fromRfq", new Boolean(true));
			}
			incomingRequest.put("PoLine_icPoHeader", poHeader.getIcPoHeader().toString());
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
