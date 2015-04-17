package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineIcAccountCopy extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String icReqLineString = (String)incomingRequest.get("RequisitionLine_icReqLine");

			if (Utility.isEmpty(icReqLineString))
			{
				icReqLineString = "0";
			}
			BigDecimal icReqLine = new BigDecimal(icReqLineString);
			String queryString = "Select rl.icAccount from RequisitionLine as rl where rl.id.icReqLine = ?";

			List resultList = dbs.query(queryString, new Object[] {icReqLine}, new Type[]{Hibernate.BIG_DECIMAL});
			if (resultList != null && resultList.size() > 0)
			{
				if (((BigDecimal)resultList.get(0)).compareTo(new BigDecimal(0)) > 0) {
					incomingRequest.put("copyAccount", "Y");
				}
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}

		return result;
	}
}
