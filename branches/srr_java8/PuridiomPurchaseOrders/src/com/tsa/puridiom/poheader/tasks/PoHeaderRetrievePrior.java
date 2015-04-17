package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderRetrievePrior extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String poNumber = poHeader.getPoNumber();

			String queryString = "from PoHeader as poHeader where poHeader.poNumber = ? AND poHeader.releaseNumber = 0 ";
			result = dbs.query(queryString, new Object[] {poNumber } , new Type[] { Hibernate.STRING}) ;
					
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