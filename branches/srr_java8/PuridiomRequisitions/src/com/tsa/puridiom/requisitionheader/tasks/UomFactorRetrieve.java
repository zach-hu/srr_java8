package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UomFactorRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String proccesFactor = (String) incomingRequest.get("proccesFactor");
			if (Utility.isEmpty(proccesFactor))
			{
				throw new Exception("UmCode cannot be empty.  Requisition header could not be retrieved.");
			}
			//BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );

			String queryString = "Select UnitOfMeasure.factor from UnitOfMeasure as UnitOfMeasure where UnitOfMeasure.umCode = ? ";
			List resultList = dbs.query(queryString, new Object[] {proccesFactor, } , new Type[] { Hibernate.STRING}) ;

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

		if(result==null){result="1";}
		return result.toString();
	}

}