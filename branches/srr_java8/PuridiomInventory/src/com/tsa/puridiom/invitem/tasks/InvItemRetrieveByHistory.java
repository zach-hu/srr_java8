package com.tsa.puridiom.invitem.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvItemRetrieveByHistory extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderHistoryString = (String ) incomingRequest.get("InvItem_icHeaderHistory");
			BigDecimal icHeaderHistory = new BigDecimal(0);
			if(!HiltonUtility.isEmpty(icHeaderHistoryString))
			{
				icHeaderHistory = new BigDecimal(icHeaderHistoryString);
			}

			String queryString = "from InvItem as InvItem where InvItem.icHeaderHistory = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeaderHistory } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
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