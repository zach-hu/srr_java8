package com.tsa.puridiom.workorder.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SrrWorkorderRetrieveByWorkorderNo extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String wo = (String ) incomingRequest.get("srrWorkorderNo");

			String queryString = "from SrrWorkorderView as w where w.workorderNo = ?  ";
			List resultList = dbs.query(queryString, new Object[] {wo} , new Type[] { Hibernate.STRING}) ;

			result = resultList ;
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