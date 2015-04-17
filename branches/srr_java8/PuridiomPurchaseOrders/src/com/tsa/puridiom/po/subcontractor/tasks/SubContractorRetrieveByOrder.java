package com.tsa.puridiom.po.subcontractor.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SubContractorRetrieveByOrder extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poNumber = (String ) incomingRequest.get("SubContractor_poNumber");
			String releaseNumberString = (String ) incomingRequest.get("SubContractor_releaseNumber");
			BigDecimal releaseNumber = new BigDecimal ( releaseNumberString );

			String queryString = "from SubContractor as SubContractor where SubContractor.id.poNumber = ? and SubContractor.id.releaseNumber = ? ";
			result = dbs.query(queryString, new Object[] {poNumber, releaseNumber} , new Type[] {Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;

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