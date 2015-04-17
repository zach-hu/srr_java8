package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoLineCheckInspection extends Task
{
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = Boolean.FALSE;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icLineHistoryString = (String) incomingRequest.get("poLine_icLineHistory");

			if (Utility.isEmpty(icLineHistoryString) && incomingRequest.get("poLine") != null)
			{
				PoLine poLine = (PoLine)incomingRequest.get("poLine");
				icLineHistoryString = poLine.getIcLineHistory().toString();

			}
			if (Utility.isEmpty(icLineHistoryString))
			{
				icLineHistoryString = "0";
			}

			BigDecimal icLineHistory = new BigDecimal (icLineHistoryString);

			String queryString = "from InspectionHeader inspectionHeader where inspectionHeader.id.icMsrLine = ? ";

			List resultList = dbs.query(queryString, new Object[] {icLineHistory, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = Boolean.TRUE;
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
