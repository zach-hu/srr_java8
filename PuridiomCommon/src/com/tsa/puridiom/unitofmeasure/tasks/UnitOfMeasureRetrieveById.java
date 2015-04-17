package com.tsa.puridiom.unitofmeasure.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;


public class UnitOfMeasureRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String umCode = (String ) incomingRequest.get("UnitOfMeasure_umCode");

			String queryString = "from UnitOfMeasure as UnitOfMeasure where UnitOfMeasure.umCode = ? ";
			List resultList = dbs.query(queryString, new Object[] {umCode } , new Type[] { Hibernate.STRING}) ;
			
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