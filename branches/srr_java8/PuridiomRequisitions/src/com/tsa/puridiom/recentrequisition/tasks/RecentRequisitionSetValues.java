package com.tsa.puridiom.recentrequisition.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentRequisitionSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentRequisitionPK comp_id = new RecentRequisitionPK();
			RecentRequisition recentRequisition = (RecentRequisition) incomingRequest.get("recentRequisition");
			if (recentRequisition == null)
			{
				recentRequisition = new RecentRequisition();
			}

			if (incomingRequest.containsKey("RecentRequisition_requisitionerCode"))
			{
				String requisitionerCode = (String ) incomingRequest.get("RecentRequisition_requisitionerCode");
				comp_id.setRequisitionerCode(requisitionerCode);
			}
			if (incomingRequest.containsKey("RecentRequisition_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				comp_id.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("RecentRequisition_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("RecentRequisition_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				recentRequisition.setDateEntered(dateEntered);
			}
			recentRequisition.setComp_id(comp_id);

			result = recentRequisition;
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