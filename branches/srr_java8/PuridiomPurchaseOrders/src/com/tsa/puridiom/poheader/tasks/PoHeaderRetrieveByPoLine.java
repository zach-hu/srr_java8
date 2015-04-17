package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class PoHeaderRetrieveByPoLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			if(poLine != null && !HiltonUtility.isEmpty(poLine.getIcPoHeader().toString()))
			{
				String queryString = "from PoHeader as PoHeader where PoHeader.icPoHeader = ? ";
				List resultList = dbs.query(queryString, new Object[] {poLine.getIcPoHeader() } , new Type[] { Hibernate.BIG_DECIMAL}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			
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