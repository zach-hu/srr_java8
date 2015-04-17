package com.tsa.puridiom.recentrfq.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentRfqSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentRfqPK comp_id = new RecentRfqPK();
			RecentRfq recentRfq = (RecentRfq) incomingRequest.get("recentRfq");
			if (recentRfq == null)
			{
				recentRfq = new RecentRfq();
			}

			if (incomingRequest.containsKey("RecentRfq_buyerCode"))
			{
				String buyerCode = (String) incomingRequest.get("RecentRfq_buyerCode");
				comp_id.setBuyerCode(buyerCode);
			}
			if (incomingRequest.containsKey("RecentRfq_icRfqHeader"))
			{
				String icRfqHeaderString = (String) incomingRequest.get("RecentRfq_icRfqHeader");
				if (Utility.isEmpty(icRfqHeaderString))
				{
					icRfqHeaderString = "0";
				}
				BigDecimal icRfqHeader = new BigDecimal ( icRfqHeaderString );
				comp_id.setIcRfqHeader(icRfqHeader);
			}
			if (incomingRequest.containsKey("RecentRfq_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentRfq_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentRfq.setDateEntered(dateEntered);
			}
			recentRfq.setComp_id(comp_id);

			result = recentRfq;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}