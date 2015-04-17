package com.tsa.puridiom.poheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoHeaderGetTotal extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
			BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
			BigDecimal total = new BigDecimal("0");

			String queryString = "select r.total from PoHeader as r where r.icPoHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {icPoHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				total = (BigDecimal) resultList.get(0);
				if (total == null) {	total = new BigDecimal(0) ;	}
			}
			result = total.toString();
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}