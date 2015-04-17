package com.tsa.puridiom.asset.serv.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
//import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoLineRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		/*
		 * @author EDSAC
		 * return a PoLine according with the icPoLine
		 */
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoLine = (String) incomingRequest.get("icPoLine");
			String queryString = "from PoLine PoLine where PoLine.icPoLine = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoLine} , new Type[] {Hibernate.STRING}) ;
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