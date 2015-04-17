package com.tsa.puridiom.recentrequisition.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RecentRequisitionRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String requisitionerCode = (String ) incomingRequest.get("RecentRequisition_requisitionerCode");
			String icReqHeaderString = (String ) incomingRequest.get("RecentRequisition_icReqHeader");

			BigDecimal icReqHeader = new BigDecimal(icReqHeaderString);
			
			String queryString = "from RecentRequisition as RecentRequisition where RecentRequisition.id.requisitionerCode = ? and RecentRequisition.id.icReqHeader = ? ";
			List resultList = dbs.query(queryString, new Object[] {requisitionerCode, icReqHeader, } , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL}) ;
					
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